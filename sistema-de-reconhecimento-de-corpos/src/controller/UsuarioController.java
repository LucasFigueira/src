package controller;

import dao.UsuarioDAO;
import entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.DigestUtils;

@ManagedBean
@RequestScoped
public class UsuarioController extends GenericController{
	private List<Usuario> usuarios;
	private Usuario usuario;
	private boolean isAdministrador;
	private boolean isPesquisa;
	private UsuarioDAO usuarioDAO;
	private String authority;
	
	// Construtor

	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO();
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<Usuario>();

	}

	// Métodos
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

	public void cadastrar() {

		if (this.getUsuarioDAO().existe(this.getUsuario())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário já existe!", "Usuário já existe!"));
		} else {
			if (this.getUsuario().getCargo() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe um cargo!", "Informe um cargo!"));
			} else {
				if (this.getUsuario().getOrgao() == null) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe um orgao!", "Informe um orgao!"));
				}
				else{
					PasswordEncoder encoder = new Md5PasswordEncoder();
					String hashedPass = DigestUtils.md5DigestAsHex(this.getUsuario().getSenha().getBytes());
					this.getUsuario().setSenha(hashedPass);
					this.getUsuario().setEnabled(true);
					this.getUsuarioDAO().adiciona(this.getUsuario());
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", "Usuário cadastrado com sucesso"));
				}
			}
		}
	}
	
	public List<Usuario> getUsuarios() {
		if (this.usuarios.size() == 0) {
			return this.getUsuarioDAO().listaTodos();
		}
		return this.usuarios;
	}

	// Getters and Setters

	public boolean isPesquisa() {
		return isPesquisa;
	}

	public void setPesquisa(boolean isPesquisa) {
		this.isPesquisa = isPesquisa;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
