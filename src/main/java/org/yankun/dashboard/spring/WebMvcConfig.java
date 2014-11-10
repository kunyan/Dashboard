package org.yankun.dashboard.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * 
 * @author kun.yan
 *
 */
@EnableWebMvc
@EnableScheduling
@Configuration
@PropertySources({
    @PropertySource("classpath:/properties/database.properties"),
    @PropertySource("classpath:/properties/weather.properties")
})
@ComponentScan(basePackages = { "org.yankun.dashboard" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/asserts/**").addResourceLocations("/asserts/");
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		
		ContentNegotiationStrategy contentNegotiationStrategy = new PathExtensionContentNegotiationStrategy(this.getContentNegotiationStrategy());
		
		ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager(contentNegotiationStrategy);
		
		viewResolver.setContentNegotiationManager(contentNegotiationManager);
		viewResolver.setViewResolvers(this.getViewResolvers());
		viewResolver.setDefaultViews(this.getDefaultView());
		return viewResolver;
	}
	
	@Bean(name ="dataSource")
	public DataSource dataSource() {
	    System.out.println("************************userDBDatasource :: init");
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
	    dataSource.setUrl(env.getProperty("database.url"));
	    dataSource.setUsername(env.getProperty("database.username"));
	    dataSource.setPassword(env.getProperty("database.password"));
	    return dataSource;
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();
//	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//
//	    converters.add(marshallingMessageConverter());
//	    restTemplate.setMessageConverters(converters);

	    return restTemplate;
	}
	
	
	private Map<String, MediaType> getContentNegotiationStrategy() {
		Map<String, MediaType> map = new HashMap<String, MediaType>();
		map.put("html", MediaType.TEXT_HTML);
		map.put("json", MediaType.APPLICATION_JSON);
		return map;
	}
	
	private List<ViewResolver> getViewResolvers() {
		List<ViewResolver> list = new ArrayList<ViewResolver>();
		list.add(new BeanNameViewResolver());
		
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setRequestContextAttribute("rc");
		list.add(internalResourceViewResolver);
		return list;
	}
	
	private List<View> getDefaultView() {
		List<View> list = new ArrayList<View>();
		list.add(new CustomMappingJackson2JsonView());
		return list;
	}
}
