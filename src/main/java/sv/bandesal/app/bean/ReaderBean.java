package sv.bandesal.app.bean;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import sv.bandesal.app.entity.Reader;
import sv.bandesal.app.service.ReaderService;

@SuppressWarnings("rawtypes")
@Named
@SessionScoped
public class ReaderBean {

	@Autowired
	private ReaderService readerService;

	private List readers;

	private String editId;
	private String name;

	@PostConstruct
	private void init() {

	}

	public List getReaders() {
		readers = readerService.getReaders();
		return readers != null ? readers : new ArrayList<>();
	}

	public String deleteByID(int id) {
		readerService.deleteReader(id);
		return "readers.xhtml?faces-redirect=true";
	}

	public String displayEditModal() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("selectedreaderId");
		var pfcurrent = PrimeFaces.current();
		var reader = readerService.getReaderByID(Integer.valueOf(id));
		
		this.setEditId(Integer.valueOf(id).toString());
		this.setName(reader.getName());
		pfcurrent.ajax().update(":readerEditForm");
		pfcurrent.executeScript("PF('dlgUpdate').show();");
		return "";
	}

	public String saveEdit() {
		try {
			String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("editedId");
			int id = Integer.valueOf(ids);
			var reader = readerService.getReaderByID(Integer.valueOf(id));
			reader.setName(name);
			readerService.editReader(reader);
			return "readers.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}

	public String addNew() {
		try {
			if (name != null && name.length() > 0) {
				Reader reader = new Reader();
				reader.setName(name);
				readerService.createNewReader(reader);
				this.setName("");
				return "readers.xhtml?faces-redirect=true";
			}
		} catch (Exception ex) {
			String msg="";
			if(ex instanceof DataIntegrityViolationException) {
				msg = "El reader con el nombre "+ name + " ya existe!";
			}else {
				msg = ex.getLocalizedMessage();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReaders(List readers) {
		this.readers = readers;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

}
