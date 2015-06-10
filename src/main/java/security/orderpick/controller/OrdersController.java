package security.orderpick.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.dao.impl.OrderDaoImpl;
import security.orderpick.datamodel.Bill;
import security.orderpick.datamodel.Order;
import security.orderpick.util.XmlUtil;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.OrderView;
import security.orderpick.datamodel.ProductInOrder;
import security.orderpick.datamodel.in.InOrder;
import security.orderpick.util.Converter;
import security.orderpick.validation.OrderValidator;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Resource(name = OrderDaoImpl.name)
	private OrderDaoI orderDao;
	
	private FopFactory fopFactory = FopFactory.newInstance();
	private TransformerFactory tFactory = TransformerFactory.newInstance();
	
	
	@RequestMapping(method = { RequestMethod.GET },value="/getbill", produces = "application/pdf")
	public @ResponseBody Bill getBill(@RequestParam(value = "id") int id,HttpServletResponse response) {
		
		
		Bill factura = orderDao.getBill(id);
		
		XmlUtil xmlUtil = new XmlUtil();
        String xml = xmlUtil.convertToXml(factura);
		
		//Setup a buffer to obtain the content length
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		//Setup FOP
		Fop fop;
		try {
			fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
		

			//Setup Transformer
			Source xsltSrc = new StreamSource(new File("reports/bill.xsl"));
			Transformer transformer = tFactory.newTransformer(xsltSrc);
	
			//Make sure the XSL transformation's result is piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());
	
			//Setup input
			InputStream source = new ByteArrayInputStream(xml.getBytes());
			//Start the transformation and rendering process
			transformer.transform( new StreamSource(source), res);
	
//			//Prepare response
			response.setContentType("application/pdf");
			response.setContentLength(out.size());
	
			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Bill factura = orderDao.getBill(id);
		return null;
	}
	
	@Resource(name = Converter.name)
	private Converter converter;

	@Resource(name = OrderValidator.name)
	private OrderValidator orderValidator;

	@RequestMapping(value = "/getall", produces = "application/json")
	public List<OrderView> getAll() {
		return orderDao.getAll();
	}

	@RequestMapping(value = "/insert", produces = "application/json")
	public int insertOrder(InOrder order, Errors error) throws Exception {
		int result = 0;

		validateOrder(order, error);

		Order newOrder = converter.getOrder(order);
		int idOrder = orderDao.insertOrder(newOrder);
		List<OrderType> newOrdersStatus = converter.getOrdersStatus(idOrder, order);
		List<Integer> idOrdersType = new ArrayList<Integer>();
		if (!newOrdersStatus.isEmpty()) {
			for (OrderType orderType : newOrdersStatus) {
				idOrdersType.add(orderDao.insertOrderType(orderType));
			}
			List<ProductInOrder> newProductsInOrder = converter.getProductsInOrder(idOrdersType, order);
			if (!newProductsInOrder.isEmpty()) {
				for (ProductInOrder productInOrder : newProductsInOrder) {
					orderDao.insertProductInOrder(productInOrder);
				}
				result = idOrder;
			}

		}

		return result;
	}

	@MessageMapping("/orders/getalive")
	@SendTo("/topic/orders")
	public List<OrderView> getAllAlive() {
		return orderDao.getAllAlive();
	}
	
//	@RequestMapping(method = { RequestMethod.GET }, value = "/getbill/{id}", produces = "application/json")
//	public Bill getBill(@PathVariable(value = "id") int id) {
//		return orderDao.getBill(id);
//	}
	

	private void validateOrder(InOrder order, Errors error) throws Exception {
		orderValidator.validate(order, error);
		if (error.hasErrors()) {
			List<ObjectError> errors = error.getAllErrors();
			String errosString = "";
			for (ObjectError objectError : errors) {
				if (!errosString.isEmpty()) {
					errosString += errosString;
				}
				errosString += objectError.getDefaultMessage();
			}
			throw new Exception(errosString);
		}
	}
}
