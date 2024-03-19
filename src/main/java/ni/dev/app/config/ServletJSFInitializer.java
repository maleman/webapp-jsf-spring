package ni.dev.app.config;

import org.apache.myfaces.webapp.MyFacesContainerInitializer;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import jakarta.faces.application.ProjectStage;
import jakarta.faces.component.UIInput;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletContainerInitializer;

public class ServletJSFInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		servletContext.setInitParameter(UIInput.EMPTY_STRING_AS_NULL_PARAM_NAME, Boolean.TRUE.toString());
		servletContext.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());

		EnhancedListener cdiInitializer = new EnhancedListener();
		cdiInitializer.onStartup(null, servletContext);

		ServletContainerInitializer myFacesInitializer = new MyFacesContainerInitializer();
		myFacesInitializer.onStartup(null, servletContext);
	}

}
