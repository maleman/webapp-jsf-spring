package sv.bandesal.app.service;

import org.springframework.stereotype.Service;

import sv.bandesal.app.entity.Authorities;
import sv.bandesal.app.entity.Users;
import sv.bandesal.app.repository.AuthoritiesRepository;
import sv.bandesal.app.repository.UsersRepository;

@Service
public class UserService {
		
	UsersRepository repository ;
	AuthoritiesRepository authoritiesRepository;
	public UserService(UsersRepository repository, AuthoritiesRepository authoritiesRepository) {
		this.repository = repository;
		this.authoritiesRepository = authoritiesRepository;
	}
	
	public void save(Users user) {
		Authorities auth = new Authorities(); 
		auth.setUserName(user.getUserName());
		auth.setAuthority("USER");
		this.repository.save(user);
		authoritiesRepository.save(auth);
	}
	
	
}
