package sv.bandesal.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import sv.bandesal.app.entity.Blog;
import sv.bandesal.app.repository.BlogRepository;

@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public void saveBlog(Blog blog) {
		blogRepository.save(blog);
	}
	
	public void deleteBlog(int id) {
		Optional<Blog> blog = blogRepository.findById(id);
		if(blog.isPresent()) {
			blogRepository.delete(blog.get());
		}
	}
	
	public Blog getBlogById(int id) {
		return blogRepository.findById(id).orElse(null);
	}
	
	public List<Blog> getAllBlogs(){
		return StreamSupport.stream(blogRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
