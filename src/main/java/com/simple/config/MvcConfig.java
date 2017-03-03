package com.simple.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackages = "com.simple.**.controller", useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {Controller.class, RestController.class})})
public class MvcConfig extends WebMvcConfigurerAdapter {

//	 @Autowired
//	  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	  private static final Logger LOGGER = LogManager.getLogger(MvcConfig.class.getName());

//	  @Override
//	  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//	    configurer.setTaskExecutor(threadPoolTaskExecutor);
//	  }

	  /**
	   * 配置默认的servletHandler
	   * 当DisptacherServlet接收到了他匹配的请求，但是找不到相应的Controller，就会把这个请求返回给默认的处理（比如交给tomcat处理）
	   */
	  @Override
	  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

	    configurer.enable();
	  }
	  /**
	   * 配置视图解析器
	   * @return
	   */
	  @Bean
	  public ViewResolver htmlResolver(){
		  InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		  viewResolver.setPrefix("/WEB-INF/page/");
		  viewResolver.setSuffix(".jsp");
		  return viewResolver;
	  }
	  
	  
}
