package ni.dev.app.config;

import java.util.Set;

import jakarta.faces.application.ProjectStage;
import jakarta.faces.component.UIInput;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConfigureListener implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		// TODO Auto-generated method stub
		ctx.setInitParameter(UIInput.EMPTY_STRING_AS_NULL_PARAM_NAME, Boolean.TRUE.toString());

		ctx.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
	}

}
