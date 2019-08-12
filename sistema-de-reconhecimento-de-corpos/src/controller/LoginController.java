package controller;

import dao.UsuarioDAO;
import entities.Corpo;
import entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.DigestUtils;

import Util.EnviarEmail;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

	private List<Usuario> usuarios;
	private Usuario usuario;
	private boolean usuarioAlteraraSenha;

	public boolean isUsuarioAlteraraSenha() {
		return usuarioAlteraraSenha;
	}

	public void setUsuarioAlteraraSenha(boolean usuarioAlteraraSenha) {
		this.usuarioAlteraraSenha = usuarioAlteraraSenha;
	}

	private Usuario usuarioAlteracao;
	private Usuario usuarioDelecao;
	private boolean isAdministrador;
	private boolean isPesquisa;
	private boolean isPerito;
	private String parametroSearch;
	private String parametroVal;
	private String senhaAntiga;
	private String novaSenha;
	private String email;
	private String mensagemErro;
	
	

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void preparaDeletar() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String login = (String) myRequest.getParameter("usuario");
		this.usuarioDelecao = this.getUsuarioDAO().buscaPorNome(login);
		System.out.println(usuarioDelecao);

	}

	public void remover() {
		try {
			this.getUsuarioDAO().remove(usuarioDelecao);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Usuario removido com sucesso!", "Usuario removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao remover usuario! Contate administrador", "Erro ao remover usuario! Contate administrador"));
		}
		this.usuarioDelecao = null;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	private String confirmaSenha;
	private UsuarioDAO usuarioDAO;

	// Construtor

	public LoginController() {
		this.usuarioDAO = new UsuarioDAO();
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<Usuario>();
		this.usuarioAlteracao = new Usuario();

	}

	// M�todos

	public boolean hasParameter() {
		if (this.getParametroVal() == null) {
			return false;
		} else {
			if (this.getParametroVal().equals("")) {
				return false;
			}
		}

		return true;
	}
	
	public String recuperarSenha() {
		System.out.println("sisisisii");
		return "recuperarSenha.xhtml?faces-redirect=true";
	}
	
	public String enviarEmail() {
		String novaSenha;
		Usuario usuario = this.getUsuarioDAO().buscaPorEmail(this.getEmail());
		if(usuario != null) {
			EnviarEmail enviarEmail = new EnviarEmail();
			novaSenha = enviarEmail.enviarEmail(this.getEmail());
			PasswordEncoder encoder = new Md5PasswordEncoder();
			String hashedPass = DigestUtils.md5DigestAsHex(novaSenha.getBytes());
			usuario.setSenha(hashedPass);
			this.usuarioDAO.atualiza(usuario);
			
		}else {
		  	 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foi possivel enviar sua nova senha, revise o e-mail informado.", 
								 "ERRO - Nao foi possivel enviar sua nova senha, revise o e-mail informado."));
		}
		
		return null;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios.size() == 0 && !hasParameter()) {
			return this.getUsuarioDAO().listaTodos();
		}
		return this.usuarios;
	}

	public String logar() {
		if (this.usuarioDAO.existe(this.usuario)) {
			return "lista?faces-redirect=true";
		}
		return "index";
	}

	public String carregaDetalhes() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String login = (String) myRequest.getParameter("usuario");
		this.setUsuarioAlteracao(this.getUsuarioDAO().buscaPorNome(login));
		return "alterar.xhtml?faces-redirect=true";
	}

	public String carregaPerfil() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = myRequest.getSession();
		String login = (String) session.getAttribute("uname");
		this.setUsuarioAlteracao(this.getUsuarioDAO().buscaPorNome(login));
		return "perfil.xhtml?faces-redirect=true";
	}

	public void senhaSeraAlterada() {
		this.usuarioAlteraraSenha = true;

	}

	public void atualizar() {
		System.out.println(this.usuarioAlteraraSenha);
		if (this.usuarioAlteraraSenha == false || this.novaSenha.equals(this.confirmaSenha)) {
			if (this.usuarioAlteraraSenha) {
				PasswordEncoder encoder = new Md5PasswordEncoder();
				String hashedPass = DigestUtils.md5DigestAsHex(this.novaSenha.getBytes());
				this.getUsuarioAlteracao().setSenha(hashedPass);
			}
			if (this.getUsuarioAlteracao().getAutoridade().equals("ROLE_ADMIN")) {
				this.getUsuarioAlteracao().setAutoridade("ROLE_ADMIN");
			} else if (this.getUsuarioAlteracao().getAutoridade().equals("ROLE_USER")) {
				this.getUsuarioAlteracao().setAutoridade("ROLE_USER");
			} else if (this.getUsuarioAlteracao().getAutoridade().equals("ROLE_PERITO")) {
				this.getUsuarioAlteracao().setAutoridade("ROLE_PERITO");
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Usuario atualizado com sucesso!", "Usuario atualizado com sucesso!"));
			this.usuarioDAO.atualiza(usuarioAlteracao);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Novas senhas nao conferem", "Novas senhas nao conferem"));
		}
		this.usuarioAlteraraSenha = false;
	}

	public void atualizarPerfil() {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		String hashedPass = DigestUtils.md5DigestAsHex(this.senhaAntiga.getBytes());
		if (!hashedPass.equals(this.getUsuarioAlteracao().getSenha())) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha antiga incorreta", "Senha antiga incorreta"));
		} else {
			if (!this.novaSenha.equals(confirmaSenha)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Novas senhas nao conferem", "Novas senhas nao conferem"));

			} else {

				hashedPass = DigestUtils.md5DigestAsHex(this.novaSenha.getBytes());
				this.usuarioAlteracao.setSenha(hashedPass);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario atualizado com sucesso!", "Usuario atualizado com sucesso!"));
				this.usuarioDAO.atualiza(usuarioAlteracao);
			}
		}
	}

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

	public void atualizaLista() {
		this.usuarios = this.getUsuarioDAO().buscaPorParam(this.getParametroSearch(), this.getParametroVal());

	}

	// Getters and Setters

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getParametroVal() {
		return parametroVal;
	}

	public void setParametroVal(String parametroVal) {
		this.parametroVal = parametroVal;
	}

	public String getParametroSearch() {
		return parametroSearch;
	}

	public void setParametroSearch(String parametroSearch) {
		this.parametroSearch = parametroSearch;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}

	public boolean isPesquisa() {
		return isPesquisa;
	}

	public void setPesquisa(boolean isPesquisa) {
		this.isPesquisa = isPesquisa;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPerito() {
		return isPerito;
	}

	public void setPerito(boolean isPerito) {
		this.isPerito = isPerito;
	}

}
