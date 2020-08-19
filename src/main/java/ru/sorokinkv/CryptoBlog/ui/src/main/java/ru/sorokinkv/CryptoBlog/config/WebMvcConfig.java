//package ru.sorokinkv.CryptoBlog.config;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.util.StringUtils;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.resource.ResourceResolver;
//import org.springframework.web.servlet.resource.ResourceResolverChain;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Value("${web.classpath.resource.location}")
//    private String CLASSPATH_RESOURCE_LOCATION;
//
//    @Value("${web.index.location}")
//    private String INDEX;
//
//    @Value("${web.api.path}")
//    private String API;
//
//    @Value("${web.cors.allowed.origins}")
//    private String[] CORS_ALLOWED_ORIGINS;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins(CORS_ALLOWED_ORIGINS)
//                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD");
//            }
//        };
//    }
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/**")
//                .addResourceLocations(CLASSPATH_RESOURCE_LOCATION)
//                .resourceChain(false);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.html");
//    }
//}
//
