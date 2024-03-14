package sv.bandesal.app.config;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContextListener;

@Configuration
public class config {
    @Bean
    ServletContextInitializer jsfInitializer() {
		return new ServletJSFInitializer();
	}
	
	@Bean
	ServletListenerRegistrationBean<ServletContextListener> facesStartupServletContextListener() {
	    ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
	    bean.setListener(new StartupServletContextListener());
	    return bean;
	}
}
