package ni.dev.app.service;

import org.springframework.stereotype.Service;

import ni.dev.app.entity.Authorities;
import ni.dev.app.entity.Users;
import ni.dev.app.repository.AuthoritiesRepository;
import ni.dev.app.repository.UsersRepository;

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
