package ua.ithillel.evo.questengine.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }
//}

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Value("${management.endpoints.web.cors.allowed-origins}")
//    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins(allowedOrigins)
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}