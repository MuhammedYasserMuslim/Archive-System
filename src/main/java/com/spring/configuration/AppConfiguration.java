package com.spring.configuration;

import com.spring.services.BaseDataServices;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
public class AppConfiguration implements WebMvcConfigurer {


    private final BaseDataServices baseDataServices;

    public AppConfiguration(BaseDataServices baseDataServices) {
        this.baseDataServices = baseDataServices;
    }

    /**
     * @return ConfigurableServletWebServerFactory
     * to allow special character in request
     */

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]/\\"));
        return factory;
    }


    /**
     * used to configure resource handlers for serving static resources , used to serve those images
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + baseDataServices.findImagePath());
    }

}
