package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class GenericController {
	    
    public boolean hasMessageError() {
		List<FacesMessage> messages = FacesContext.getCurrentInstance().getMessageList();
		for (FacesMessage facesMessage : messages) {
			if (facesMessage.getSeverity() == FacesMessage.SEVERITY_ERROR) {
				return true;
			}
		}
		return false;
	}
    
	public boolean hasMessageSuccess() {
		List<FacesMessage> messages = FacesContext.getCurrentInstance().getMessageList();
		for (FacesMessage facesMessage : messages) {
			if (facesMessage.getSeverity() == FacesMessage.SEVERITY_INFO) {
				return true;
			}
		}
		return false;
	}


}
