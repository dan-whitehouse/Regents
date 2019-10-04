package org.neric.regents.config;

import org.neric.regents.converture.RoleToUserProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = "org.neric.regents")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;	

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }
    @Override
    public void addFormatters(FormatterRegistry registry) 
    {
        registry.addConverter(roleToUserProfileConverter);
    }


    @Bean(name="viewProject")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        // 5 MB
        int maxUploadSizeInMb = 5 * 1024 * 1024;
        cmr.setMaxUploadSize(maxUploadSizeInMb);
        cmr.setMaxUploadSizePerFile(maxUploadSizeInMb);
        cmr.setMaxInMemorySize(maxUploadSizeInMb);
        return cmr;
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
    
    /* https://smarterco.de/spring-boot-mvc-prevent-spring-mvc-modelattribute-variables-from-appearing-in-url-redirect/ */
    @Inject
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
    	requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }
}
