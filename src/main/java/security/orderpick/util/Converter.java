package security.orderpick.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.Product;

@Component(Converter.name)
public class Converter {
	
	public static final String name="conventer";
	
	public static final String url_images="/tmp/food/images";
	public static final String url_videos="/tmp/food/videos";
	
	public Converter() {
	}

	public Product converterProduct(security.orderpick.viewmodel.Product product) throws IOException {
		Product productDataModel = new Product();
		productDataModel.setId(product.getId());
		productDataModel.setDescription(product.getDescription());
		productDataModel.setName(product.getName());
		productDataModel.setEmpty(product.isEmpty());
		
		if (product.getImage()!=null){
			File newFile = new File(url_images+product.getImage().getName());
			FileUtils.copyInputStreamToFile(product.getImage().getInputStream(), newFile);
			productDataModel.setImage(url_images+product.getImage().getName());
		}
		
		if (product.getMovie()!=null){
			File newFile = new File(url_images+product.getMovie().getName());
			FileUtils.copyInputStreamToFile(product.getMovie().getInputStream(), newFile);
			productDataModel.setImage(url_videos+product.getMovie().getName());
		}
		
		return productDataModel;
	}

}
