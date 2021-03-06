package security.orderpick.util;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import security.orderpick.config.Constants;
import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.Parameter;
import security.orderpick.datamodel.Product;
import security.orderpick.datamodel.ProductInOrder;
import security.orderpick.datamodel.in.InOrder;
import security.orderpick.datamodel.in.InOrderType;
import security.orderpick.datamodel.in.InProductInOrder;
import security.orderpick.mapper.ParameterMapper;

import com.mysql.jdbc.StringUtils;

@Component(Converter.name)
public class Converter {

	public static final String name = "conventer";

	public String url_images;

	public String url_videos;

	@Resource(name = ParameterMapper.name)
	private ParameterMapper parameterMapper;

	@Resource(name = EncodeBased64Binary.name)
	private EncodeBased64Binary encodeBased64;

	public Converter() {}

	public void getValues() {
		Parameter paramImage = parameterMapper.getParameter(Constants.BASE_URL_IMAGE);
		Parameter paramVideo = parameterMapper.getParameter(Constants.BASE_URL_IMAGE);
		url_images = paramImage.getValue();
		url_videos = paramVideo.getValue();
	}

	public Product converterProduct(security.orderpick.viewmodel.Product product) throws IOException {
		getValues();
		Product productDataModel = new Product();
		productDataModel.setId(product.getId());
		productDataModel.setDescription(product.getDescription());
		productDataModel.setName(product.getName());
		productDataModel.setEmpty(product.isEmpty());
		productDataModel.setEmpty(product.isEmpty());
		productDataModel.setEmpty(product.isEmpty());

		/*
		 * if (product.getImage() != null) { File newFile = new File(url_images +
		 * product.getImage().getName()); if (!newFile.exists()) {
		 * FileUtils.copyInputStreamToFile(product.getImage().getInputStream(), newFile); }
		 * productDataModel.setImage(url_images + product.getImage().getName()); } if
		 * (product.getMovie() != null) { File newFile = new File(url_images +
		 * product.getMovie().getName()); if (!newFile.exists()) {
		 * FileUtils.copyInputStreamToFile(product.getMovie().getInputStream(), newFile); }
		 * productDataModel.setImage(url_videos + product.getMovie().getName()); }
		 */

		return productDataModel;
	}

	public Product converterProduct(String id, String name, String description, Boolean empty, String price,
			MultipartFile image, MultipartFile movie, List<Integer> types) throws IOException {
		getValues();
		Product productDataModel = new Product();
		if (!StringUtils.isNullOrEmpty(id)) {
			productDataModel.setId(Integer.parseInt(id));
		}
		productDataModel.setDescription(description);
		productDataModel.setName(name);
		productDataModel.setEmpty(empty);
		productDataModel.setPrice(Double.parseDouble(price));
		productDataModel.setTypes(types);
		if (image != null) {
			File newFile = new File(url_images + image.getOriginalFilename());
			if (!newFile.exists()) {
				FileUtils.copyInputStreamToFile(image.getInputStream(), newFile);
			}
			productDataModel.setImage(url_images + image.getOriginalFilename());
		}

		if (movie != null) {
			File newFile = new File(url_videos + movie.getOriginalFilename());
			if (!newFile.exists()) {
				FileUtils.copyInputStreamToFile(movie.getInputStream(), newFile);
			}
			productDataModel.setImage(url_videos + movie.getOriginalFilename());
		}

		return productDataModel;
	}

	public security.orderpick.viewmodel.Product convertToDataViewProduct(Product product) throws IOException {
		getValues();
		security.orderpick.viewmodel.Product result = new security.orderpick.viewmodel.Product();
		result.setId(product.getId());
		result.setDescription(product.getDescription());
		result.setName(product.getName());
		result.setEmpty(product.isEmpty());
		result.setPrice(product.getPrice());
		result.setTypes(product.getTypes());
		if (!StringUtils.isNullOrEmpty(product.getImage())) {
			File newFile = new File(product.getImage());
			if (newFile.exists()) {
				
				result.setImageName(product.getImage().replaceFirst(url_images, ""));
				result.setImageData("data:" + URLConnection.guessContentTypeFromName(product.getImage())
						+ ";base64," + encodeBased64.encodeFileToBase64Binary(newFile));
			}
		}

		if (!StringUtils.isNullOrEmpty(product.getMovie())) {
			File newFile = new File(product.getMovie());
			if (newFile.exists()) {
				result.setMovieName(product.getImage().replaceFirst(url_videos, ""));
				result.setMovieData(encodeBased64.encodeFileToBase64Binary(newFile));
			}
		}
		return result;
	}

	public Order getOrder(InOrder order) {
		Order result = new Order(order.getId(), order.getIdTable(), order.getDescription(), order.getReg_date());
		return result;
	}

	public List<OrderType> getOrdersStatus(int idOrder, InOrder order) {
		List<OrderType> result = new ArrayList<OrderType>();

		if (order.getTypes() != null && !order.getTypes().isEmpty()) {
			for (InOrderType inOrderType : order.getTypes()) {
				OrderType orderType = new OrderType(inOrderType.getId(), idOrder, inOrderType.getId(),
						inOrderType.getStatus());
				result.add(orderType);
			}
		}

		return result;
	}

	public List<ProductInOrder> getProductsInOrder(List<Integer> idOrdersType, InOrder order) {
		List<ProductInOrder> results = new ArrayList<ProductInOrder>();

		if (order.getTypes() != null && !order.getTypes().isEmpty()) {
			int index = 0;
			for (InOrderType inOrderType : order.getTypes()) {
				if (inOrderType.getProducts() != null && inOrderType.getProducts().isEmpty()) {
					Integer idOrderType = idOrdersType.get(index);
					for (InProductInOrder inProductInOrder : inOrderType.getProducts()) {
						ProductInOrder productInOrder = new ProductInOrder(inProductInOrder.getId(), idOrderType,
								inProductInOrder.getIdProduct(), inProductInOrder.getQuantity());
						results.add(productInOrder);
					}
				}

				index++;
			}
		}

		return results;
	}

	public void deleteMedia(Product product) {
		
		if (product!=null && product.getImage()!=null && 
				!product.getImage().isEmpty()){
			File newFile = new File(product.getImage());
			newFile.delete();
		}
		if (product!=null && product.getMovie()!=null && 
				!product.getMovie().isEmpty()){
			File newFile = new File(product.getMovie());
			newFile.delete();
		}
	}

}
