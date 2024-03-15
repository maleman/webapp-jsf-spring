package sv.bandesal.app.bean;

import java.util.List;
import org.primefaces.PrimeFaces;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import sv.bandesal.app.entity.Blog;
import sv.bandesal.app.service.BlogService;

@Named
@SessionScoped
@Data
public class BlogBean {
	
	private BlogService blogService;
	
	private List<Blog> blogs;

	private String editId;
	private String title;
	private String description;
	
	public BlogBean(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@PostConstruct
	private void init() {
		
	}
	
	private void clean() {
		editId = "";
		title = "";
		description = "";
	}
	
	public List<Blog> getBlogs() {
		blogs = blogService.getAllBlogs();
		return blogs;
	}
	
	public String addNew() {
		try {
			if ((title != null && title.length() > 0) && (description != null && description.length() > 0)) {
				var blog = new Blog();
				blog.setTitle(title);
				blog.setDescription(description);
				blogService.saveBlog(blog);
				clean();
				return "blogs.xhtml?faces-redirect=true";
			}
		} catch (Exception ex) {
			String msg="";
			if(ex instanceof DataIntegrityViolationException) {
				msg = "Ocurrio un error de integridad de datos!";
			}else {
				msg = ex.getLocalizedMessage();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}

	public String deleteByID(int id) {
		blogService.deleteBlog(id);
		return "blogs.xhtml?faces-redirect=true";
	}

	public void displayEditModal() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("selectedreaderId");
		var blog = blogService.getBlogById(Integer.valueOf(id));
		var pfcurrent = PrimeFaces.current();
		
		this.setEditId(id);
		this.setTitle(blog.getTitle());
		this.setDescription(blog.getDescription());
		pfcurrent.ajax().update(":blogEditForm");
		pfcurrent.executeScript("PF('dlgUpdate').show();");
	}

	public String saveEdit() {
		try {
			String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("editedId");
			int id = Integer.valueOf(ids);
			var blog = new Blog();
			blog.setId(id);
			blog.setTitle(title);
			blog.setDescription(description);
			blogService.saveBlog(blog);
			clean();
			return "blogs.xhtml?faces-redirect=true";
			
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "";
	}
	
	public String cancelOnNew() {
		return "blogs.xhtml?faces-redirect=true";
	}

}
