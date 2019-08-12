package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import entities.Cicatriz;
import entities.Corpo;
import entities.Dentes;
import entities.Ossada;
import entities.Tatuagem;
import entities.Usuario;
import service.JPAUtil;

public class OssadaDAO {
	
	public List<Ossada> buscaPorParam(String param,String val) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select u from Ossada u where "
				+ " (u."+param+") = :login order by u."+param;
		
		List<Ossada> encontrado = manager.createQuery(jpql, Ossada.class)
				.setParameter("login",Integer.parseInt(val)).getResultList();

		manager.close();
		
		return encontrado; 
	}
	public Dentes buscarTatuagemById(int codigoOssada) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Dentes dentes = null;		 
		try {
			
		dentes = manager.find(Dentes.class, codigoOssada);
		} finally {
			manager.close();
	    }
		
		return dentes;
	}
	public void adiciona(Ossada ossada) throws Exception {
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
	
			//persiste o objeto
			manager.persist(ossada);
			
			manager.getTransaction().commit();
			manager.close();
		}catch (Exception e) {
			throw e;
		}
		
	}
	public void remove(Ossada ossada) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(ossada));
		manager.getTransaction().commit();
		manager.close();
	}
	
	
	public Ossada buscarById(int codigoOssada) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Ossada ossada= null;		 
		try {
			
		ossada = manager.find(Ossada.class, codigoOssada);
		} finally {
			manager.close();
	    }
		
		return ossada;
	}
	
		
	public Integer buscarUltimoRegistro() {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select max(u.codigoOssada) from Ossada u order by u.codigoOssada DESC )";
		
		Integer ultimoId = manager.createQuery(jpql, Integer.class)
				.getSingleResult();

		manager.close();
		
		return ultimoId; 
	}
	
	public ArrayList<Corpo> buscarOssadaPorCorDePele(String corPele) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Ossada u where corPele like '" + corPele + "'";
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorCorOlhos(String corOlhos) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where corOlhos like '" + corOlhos + "'";
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorCorCabelo(String corCabelo) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where corCabelo like '" + corCabelo + "'";
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorAlturaAproximada(Double altura) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		Double alturaMinima = altura - 0.10;
		Double alturaMaxima = altura + 0.10;
		
		String jpql = "select u from Corpo u where altura between " + alturaMinima  + " and " + alturaMaxima;
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorIdadeAproximada(Integer idade) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		Integer idadeMinima = idade - 10;
		Integer idadeMaxima = idade + 10;
		
		String jpql = "select u from Corpo u where idade between " + idadeMinima  + " and " + idadeMaxima;
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	
	
	public ArrayList<Corpo> buscarTodos() {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u" ;
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}

	public ArrayList<Corpo> buscarCorposPorPesoAproximado(Double peso) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		Double pesoMinima = peso - 10;
		Double pesoMaxima = peso + 10;
		
		String jpql = "select u from Corpo u where peso between " + pesoMinima  + " and " + pesoMaxima;
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	public List<Ossada> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		
		CriteriaQuery<Ossada> query = manager.getCriteriaBuilder().createQuery(Ossada.class);
		query.select(query.from(Ossada.class));
		
		List<Ossada> lista = manager.createQuery(query).getResultList();
		
		manager.close();
		
		return lista; 
	}

	public void adicionaDentes(Dentes dente) throws Exception {
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
	
			//persiste o objeto
			manager.persist(dente);
			
			manager.getTransaction().commit();
			manager.close();
		}catch (Exception e) {
			throw e;
		}
		
	}
}
