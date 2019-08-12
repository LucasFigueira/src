/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.MyListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sun.javafx.collections.MappingChange.Map;

import controller.LoginController;




/**
 *
 * @author uc14100068
 */
@Entity
@Table(name="usuario")
public class Usuario{
  
	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
    private String senha;
    private String nome;
    private String email;
    private String cargo;
    private String orgao;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean enabled;
    private String autoridade;
    public String getAutoridade() {
		return autoridade;
	}

	public void setAutoridade(String autoridade) {
		this.autoridade = autoridade;
	}

	public String getOrgao() {
		return orgao;
	}
	

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

    public boolean isAdministrador() {
    	RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession httpSession = request.getSession();    
    	String authorities = (String) httpSession.getAttribute("authorities");
    	System.out.println(authorities);
		return authorities.equals("ROLE_ADMIN");
	}
    
    public boolean isPerito() {
    	RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession httpSession = request.getSession();    
    	String authorities = (String) httpSession.getAttribute("authorities");
    	System.out.println(authorities);
		return authorities.equals("ROLE_PERITO");
	}
    
    public boolean isUser() {
    	RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession httpSession = request.getSession();    
    	String authorities = (String) httpSession.getAttribute("authorities");
    	System.out.println(authorities);
		return authorities.equals("ROLE_USER");
	}
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }


	
}
