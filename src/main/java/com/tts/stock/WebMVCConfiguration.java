package com.tts.stock;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executor;

import javax.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
@EnableWebMvc
public class WebMVCConfiguration
		implements WebMvcConfigurer, WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Autowired
	RequestServiceInterceptor requestInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/192.168.99.67").allowedOrigins("*");
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*").addResourceLocations("classpath:/static/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		registry.addResourceHandler("/manifest.json").addResourceLocations("classpath:/static/manifest.json").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/info/**").addResourceLocations("classpath:/static/info/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());;
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());//one month
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		registry.addResourceHandler("/api/v1/productphoto/**").addResourceLocations("file:" + s+ "/productphoto/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/api/v1/brandphoto/**").addResourceLocations("file:" + s+ "/brandphoto/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/api/v1/productfile/**").addResourceLocations("file:" + s+ "/productfile/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());
		registry.addResourceHandler("/api/v1/newsphoto/**").addResourceLocations("file:" + s+ "/newsphoto/").setCachePeriod(2592000).resourceChain(true).addResolver(new EncodedResourceResolver());
	}
	

//	@Bean
//	public ViewResolver beanNameViewResolver() {
//		BeanNameViewResolver resolver = new BeanNameViewResolver();
//		return resolver;
//	}

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"), new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(100));
		return factory.createMultipartConfig();
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		//templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		TemplateEngine templateEngine = new TemplateEngine();
		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		templateEngine.setTemplateResolver(templateResolver);

		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
