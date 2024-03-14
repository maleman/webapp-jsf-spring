package sv.bandesal.app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import sv.bandesal.app.entity.Blog;
import sv.bandesal.app.entity.BlogReader;
import sv.bandesal.app.entity.Reader;
import sv.bandesal.app.repository.BlogReaderRepository;
import sv.bandesal.app.repository.BlogRepository;
import sv.bandesal.app.repository.ReaderRepository;

@Service
public class BlogReaderService {
	
	private BlogReaderRepository blogReaderRepository;
	private BlogRepository blogRepository;
	private ReaderRepository readerRepository;
	
	public BlogReaderService(BlogReaderRepository blogReaderRepository, 
			BlogRepository blogRepository, 
			ReaderRepository readerRepository) {
		this.blogReaderRepository = blogReaderRepository;
		this.blogRepository = blogRepository;
		this.readerRepository = readerRepository;
	}
	
	public List<BlogReader> getBlogreaders(){
		return StreamSupport.stream(blogReaderRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public List<Reader> getReaders(){
		return StreamSupport.stream(readerRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public List<Blog> getBlogs(){
		return StreamSupport.stream(blogRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public BlogReader getBlogReaderById(int id) {
		return blogReaderRepository.findById(id).orElse(null);
	}
	
	public Blog getBlogById(int id) {
		return blogRepository.findById(id).orElse(null);
	}
	
	public Reader getReaderById(int id) {
		return readerRepository.findById(id).orElse(null);
	}
	
	public void saveBlogReader(BlogReader blogReader) {
		blogReaderRepository.save(blogReader);
	}
	
	public void deleteBlogReader(int id) {
		var blogReader = blogReaderRepository.findById(id);
		if(blogReader.isPresent()) {
			blogReaderRepository.delete(blogReader.get());
		}
	}

}
