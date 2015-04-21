package security.orderpick.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/vieworder").setViewName("forms/orders");
        registry.addViewController("/viewusers").setViewName("forms/users");
        registry.addViewController("/viewpaymentsdata").setViewName("forms/paymentsdata");
        registry.addViewController("/viewproducts").setViewName("forms/products");
        registry.addViewController("/viewtables").setViewName("forms/tables");
        registry.addViewController("/viewturns").setViewName("forms/turns");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/");
    }

}
