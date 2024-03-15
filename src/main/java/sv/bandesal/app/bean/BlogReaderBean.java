package sv.bandesal.app.bean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.PrimeFaces;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import sv.bandesal.app.entity.Blog;
import sv.bandesal.app.entity.BlogReader;
import sv.bandesal.app.entity.Reader;
import sv.bandesal.app.service.BlogReaderService;

@Named
@SessionScoped
@Data
public class BlogReaderBean {

	BlogReaderService blogReaderService;

	@SuppressWarnings("unused")
	private List<BlogReader> blogreaders;
	@SuppressWarnings("unused")
	private Map<String, Integer> readers;
	@SuppressWarnings("unused")
	private Map<String, Integer> blogs;

	private String editId;
	private Integer readerId;
	private Integer blogId;

	private void clear() {
		this.readerId = null;
		this.blogId = null;

	}

	public BlogReaderBean(BlogReaderService blogReaderService) {
		this.blogReaderService = blogReaderService;
	}

	@PostConstruct
	private void init() {

	}

	public List<BlogReader> getBlogreaders() {
		return blogReaderService.getBlogreaders();
	}

	public Map<String, Integer> getReaders() {
		List<Reader> readers = blogReaderService.getReaders();
		return readers.stream().collect(Collectors.toMap(Reader::getName, Reader::getId));
	}

	public Map<String, Integer> getBlogs() {
		var blogs = blogReaderService.getBlogs();
		return blogs.stream().collect(Collectors.toMap(Blog::getTitle, Blog::getId));
	}

	public String addNew() {
		try {

			var blogReader = new BlogReader();
			blogReader.setBlog(blogReaderService.getBlogById(blogId));
			blogReader.setReader(blogReaderService.getReaderById(readerId));
			blogReaderService.saveBlogReader(blogReader);
			clear();
			return "blogsreaders.xhtml?faces-redirect=true";

		} catch (Exception ex) {
			String msg = "";
			if (ex instanceof DataIntegrityViolationException) {
				msg = "Ocurrio un error de integridad de datos!";
			} else {
				msg = ex.getLocalizedMessage();
			}

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}

	public String deleteByID(int id) {
		try {
			blogReaderService.deleteBlogReader(id);
			return "blogsreaders.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getLocalizedMessage());
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}

	public void displayEditModal() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedId");
		var blogReader = blogReaderService.getBlogReaderById(Integer.valueOf(id));
		var pfcurrent = PrimeFaces.current();
		this.setEditId(id);
		this.setReaderId(blogReader.getReader().getId());
		this.setBlogId(blogReader.getBlog().getId());
		pfcurrent.ajax().update(":blogReaderEditForm");
		pfcurrent.executeScript("PF('dlgUpdate').show();");
	}

	public String saveEdit() {
		try {
			String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("selectedId");
			int id = Integer.valueOf(ids);
			var blogReader = new BlogReader();
			var blog = blogReaderService.getBlogById(blogId);
			var reader = blogReaderService.getReaderById(readerId);
			blogReader.setId(id);
			blogReader.setBlog(blog);
			blogReader.setReader(reader);

			blogReaderService.saveBlogReader(blogReader);
			clear();
			return "blogsreaders.xhtml?faces-redirect=true";

		} catch (Exception ex) {
			clear();
			String msg = "";
			if (ex instanceof DataIntegrityViolationException) {
				msg = "Ocurrio un error de integridad de datos!";
			} else {
				msg = ex.getLocalizedMessage();
			}

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}
}
