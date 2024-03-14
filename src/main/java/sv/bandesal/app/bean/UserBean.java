package sv.bandesal.app.bean;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import lombok.Data;
import sv.bandesal.app.entity.Users;
import sv.bandesal.app.service.UserService;
import sv.bandesal.app.utils.SecurityUtils;

@Named
@Data
@SessionScoped
public class UserBean {

	@Autowired
	private UserService userService;
	
	private String userName;
	private String password;
	
	public UserBean(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct
	private void init() {
		
	}
	
	public String createUser() {
		String msg = null;
		try {
			PasswordEncoder passwordEncoder = SecurityUtils.bCryptencoder();
			Users user = new Users();
			user.setUserName(userName);
			user.setPassword(passwordEncoder.encode(password));
			user.setEnabled(true);
			userService.save(user);
			
	        return "/login.xhtml?faces-redirect=true&includeViewParams=true&created-user=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e instanceof DataIntegrityViolationException) {
				msg = "El nombre de usuario ya existe!"; //El nombre de usuario ya existe
			}else {
				msg = "Ocurrio un error!";
			}
			
		}
		
		if(msg != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}
	
}
