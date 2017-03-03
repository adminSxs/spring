package com.simple.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Configuration
@ComponentScan(basePackages = "com.simple",
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {Controller.class, RestController.class})})
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//// 开启异步线程
//@EnableAsync
//// 开始任务调度
//@EnableScheduling
public class AppConfig extends WebMvcConfigurationSupport{

	
	 private static final Logger LOGGER = LogManager.getLogger(AppConfig.class.getName());

	  /**
	   * 
	   * @title:stringHttpMessageConverter
	   * @Description:spring http内容转换器
	   */
	  @Bean
	  public StringHttpMessageConverter stringHttpMessageConverter() {
	    return new StringHttpMessageConverter();
	  }

	  /**
	   * 
	   * @title:mappingJackson2HttpMessageConverter
	   * @Description:Spring Mvc RequsetMapping jackson转换器
	   */
	  @Bean
	  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
	        new MappingJackson2HttpMessageConverter();
	    List<MediaType> mts = Lists.newArrayList();
	    Map<String, String> parameterMap = Maps.newHashMap();
	    parameterMap.put("charset", "UTF-8");
	    mts.add(new MediaType("application", "json", parameterMap));
	    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mts);
	    return mappingJackson2HttpMessageConverter;
	  }

	  /**
	   * 
	   * @title:requestMappingHandlerAdapter
	   * @Description:注册RequestMappingHandlerAdapter 并且设置内容转换器
	   */
	  @Bean
	  public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
	      StringHttpMessageConverter stringHttpMessageConverter,
	      MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
	    LOGGER.info("Init RequestMappingHandlerAdapter....");
	    RequestMappingHandlerAdapter rmha = super.requestMappingHandlerAdapter();
	    List<HttpMessageConverter<?>> messageConverters = Lists.newArrayList();
	    messageConverters.add(stringHttpMessageConverter);
	    messageConverters.add(mappingJackson2HttpMessageConverter);
	    rmha.setMessageConverters(messageConverters);
	    return rmha;

	  }
}
