package ni.dev.app.repository;

import org.springframework.data.repository.CrudRepository;

import ni.dev.app.entity.Blog;

public interface BlogRepository extends CrudRepository<Blog, Integer>{

}
