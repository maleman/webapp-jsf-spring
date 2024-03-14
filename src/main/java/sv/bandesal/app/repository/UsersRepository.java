package sv.bandesal.app.repository;

import org.springframework.data.repository.CrudRepository;

import sv.bandesal.app.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	
	//@Query("SELECT u FROM Users u WHERE u.userName = :username")
    //public Users getUserByUsername(@Param("userame") String username);

}
