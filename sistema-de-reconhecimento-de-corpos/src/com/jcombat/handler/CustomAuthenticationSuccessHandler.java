
package com.jcombat.handler;
 
import java.io.IOException;
import java.util.Set;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
public class CustomAuthenticationSuccessHandler implements
AuthenticationSuccessHandler{
private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		HttpSession session = request.getSession();
		
		/*Set some session variables*/
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        session.setAttribute("uname", authUser.getUsername());
        for ( GrantedAuthority authority : authentication.getAuthorities()) {
			if(authority.getAuthority().equals("ROLE_ADMIN")){
				session.setAttribute("authorities","ROLE_ADMIN");
			}else if(authority.getAuthority().equals("ROLE_USER")){ 
				session.setAttribute("authorities","ROLE_USER");
			}else if(authority.getAuthority().equals("ROLE_PERITO")){ 
				session.setAttribute("authorities","ROLE_PERITO");
			}
		}
        /*Set target URL to redirect*/
		String targetUrl = determineTargetUrl(authentication); 
        redirectStrategy.sendRedirect(request, response, targetUrl);
	}
 
	protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        if (authorities.contains("ROLE_ADMIN")) {
        	return "/lista.xhtml";
        } else if (authorities.contains("ROLE_USER")) {
        	return "/pesquisaCorpos.xhtml";
        } else if (authorities.contains("ROLE_PERITO")) {
        	return "/cadastrarCorpos.xhtml";
        } else {
            throw new IllegalStateException();
        }
    }
 
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
 
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
