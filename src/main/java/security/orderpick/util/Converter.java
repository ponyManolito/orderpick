package security.orderpick.util;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.jdbc.StringUtils;

import security.orderpick.config.Constants;
import security.orderpick.datamodel.Parameter;
import security.orderpick.datamodel.Product;
import security.orderpick.mapper.ParameterMapper;

@Component(Converter.name)
public class Converter {
	
	public static final String name="conventer";
	
	public String url_images;
	public String url_videos;
	
	@Resource(name = ParameterMapper.name)
	private ParameterMapper parameterMapper;
	
	public Converter() {
	}
	
	public void getValues(){
		Parameter paramImage = parameterMapper.getParameter(Constants.BASE_URL_IMAGE);
		Parameter paramVideo = parameterMapper.getParameter(Constants.BASE_URL_IMAGE);
		url_images = paramImage.getValue();
		url_videos  = paramVideo.getValue();
	}
	public Product converterProduct(security.orderpick.viewmodel.Product product) throws IOException {
		getValues();
		Product productDataModel = new Product();
		productDataModel.setId(product.getId());
		productDataModel.setDescription(product.getDescription());
		productDataModel.setName(product.getName());
		productDataModel.setEmpty(product.isEmpty());
		
		if (product.getImage()!=null){
			File newFile = new File(url_images+product.getImage().getName());
			if (!newFile.exists()){
				FileUtils.copyInputStreamToFile(product.getImage().getInputStream(), newFile);
			}
			productDataModel.setImage(url_images+product.getImage().getName());
		}
		
		if (product.getMovie()!=null){
			File newFile = new File(url_images+product.getMovie().getName());
			if (!newFile.exists()){
				FileUtils.copyInputStreamToFile(product.getMovie().getInputStream(), newFile);
			}
			productDataModel.setImage(url_videos+product.getMovie().getName());
		}
		
		return productDataModel;
	}

	public Product converterProduct(String id, String name, String description,
			Boolean empty, String price, MultipartFile image, MultipartFile movie) throws IOException {
		getValues();
		Product productDataModel = new Product();
		if (!StringUtils.isNullOrEmpty(id)){
			productDataModel.setId(Integer.getInteger(id));
		}
		productDataModel.setDescription(description);
		productDataModel.setName(name);
		productDataModel.setEmpty(empty);
		productDataModel.setPrice(Double.parseDouble(price));
		if (image!=null){
			File newFile = new File(url_images+image.getOriginalFilename());
			if (!newFile.exists()){
				FileUtils.copyInputStreamToFile(image.getInputStream(), newFile);
			}
			productDataModel.setImage(url_images+image.getName());
		}
		
		if (movie!=null){
			File newFile = new File(url_images+movie.getOriginalFilename());
			if (!newFile.exists()){
				FileUtils.copyInputStreamToFile(movie.getInputStream(), newFile);
			}
			productDataModel.setImage(url_videos+movie.getName());
		}
		
		return productDataModel;
	}

	public security.orderpick.viewmodel.Product convertToDataViewProduct(
			Product product) throws IOException {
		security.orderpick.viewmodel.Product result = new security.orderpick.viewmodel.Product();
		result.setId(product.getId());
		result.setDescription(product.getDescription());
		result.setName(product.getName());
		result.setEmpty(product.isEmpty());
		result.setPrice(product.getPrice());
		if (StringUtils.isNullOrEmpty(product.getImage())){
			File newFile = new File(url_images+product.getImage());
			if (!newFile.exists()){
				DiskFileItem fileItem = new DiskFileItem("image", "image/jpeg", true,newFile.getName(),
							100000000, newFile.getParentFile());
				fileItem.getOutputStream();
				result.setImage(new CommonsMultipartFile(fileItem));
			}
		}
		
		if (product.getMovie()!=null){
			File newFile = new File(url_images+product.getMovie());
			if (!newFile.exists()){
				DiskFileItem fileItem = new DiskFileItem("video", "video/mpeg", true,newFile.getName(),
						100000000, newFile.getParentFile());
				fileItem.getOutputStream();
				result.setMovie(new CommonsMultipartFile(fileItem));
			}
		}
		return result;
	}

}
