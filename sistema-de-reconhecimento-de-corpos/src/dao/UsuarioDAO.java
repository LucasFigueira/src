/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import service.JPAUtil;

/**
 *
 * @author uc14100068
 */
public class UsuarioDAO {
     
    private static final long serialVersionUID = 1L;
	
	public boolean existe(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();		Query query = manager.createQuery("select u from Usuario u where u.login = :pLogin")
						.setParameter("pLogin", usuario.getLogin());						
		boolean encontrado = !query.getResultList().isEmpty();
		return encontrado;
	}
	
	public void adiciona(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(usuario);
		
		manager.getTransaction().commit();
		manager.close();
	}


	public void remove(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.remove(manager.merge(usuario));

		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.merge(usuario);
		
		manager.getTransaction().commit();
		manager.close();
	}
	
	
	public Usuario pesquisa(Long usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		Usuario encontrado = manager.find(Usuario.class,usuario);
		manager.getTransaction().commit();
		manager.close();
		return encontrado;
	}

	public Usuario buscaPorNome(String cpf) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select u from Usuario u where "
				+ " (u.login) = :login order by u.login";

		Usuario encontrado = manager.createQuery(jpql, Usuario.class)
				.setParameter("login",cpf).getSingleResult();

		manager.close();
		
		return encontrado; 
	}
	
	public Usuario buscaPorEmail(String email) {
		Usuario encontrado = null;
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
	
			String jpql = "select u from Usuario u where "
					+ " (u.email) = :email";
	
			 encontrado = manager.createQuery(jpql, Usuario.class)
					.setParameter("email",email).getSingleResult();
	
			manager.close();
		} catch (Exception e) { 
			System.out.println("Email não encontrado");
		}
		return encontrado;
		
	}
	
	public List<Usuario> buscaPorParam(String param,String val) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select u from Usuario u where "
				+ " (u."+param+") like :login order by u."+param;

		List<Usuario> encontrado = manager.createQuery(jpql, Usuario.class)
				.setParameter("login",val+"%").getResultList();

		manager.close();
		
		return encontrado; 
	}

	public List<Usuario> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		
		CriteriaQuery<Usuario> query = manager.getCriteriaBuilder().createQuery(Usuario.class);
		query.select(query.from(Usuario.class));

		List<Usuario> lista = manager.createQuery(query).getResultList();

		manager.close();
		
		return lista; 
	}
}
