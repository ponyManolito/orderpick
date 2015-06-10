package security.orderpick.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	
	@RequestMapping(value = "/getall", produces = "application/json")
	public List<Order> getAll() {
		return orderDao.getAll();
	}

	@MessageMapping("/orders/getalive")
	@SendTo("/topic/orders")
	public List<Order> getAllAlive() {
		return orderDao.getAllAlive();
	}
	
//	@RequestMapping(method = { RequestMethod.GET }, value = "/getbill/{id}", produces = "application/json")
//	public Bill getBill(@PathVariable(value = "id") int id) {
//		return orderDao.getBill(id);
//	}
	
}
