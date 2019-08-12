package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Cicatriz;
import entities.Corpo;
import entities.LogEntity;
import entities.Tatuagem;
import service.JPAUtil;

public class CorpoDAO {
	
	public void adiciona(Corpo corpo) throws Exception {
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
	
			//persiste o objeto
			manager.persist(corpo);
			
			manager.getTransaction().commit();
			manager.close();
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public void adicionaTatuagem(Tatuagem tatuagem) throws Exception {
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
	
			//persiste o objeto
			manager.persist(tatuagem);
			
			manager.getTransaction().commit();
			manager.close();
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public void adicionaCicatriz(Cicatriz cicatriz) throws Exception {
		try {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
	
			//persiste o objeto
			manager.persist(cicatriz);
			
			manager.getTransaction().commit();
			manager.close();
		}catch (Exception e) {
			throw e;
		}
		
	}
	public LogEntity buscarByLogId(int codigoCorpo) {
		EntityManager manager = new JPAUtil().getEntityManager();
		LogEntity corpo = null;		 
		try {
			
		corpo = manager.find(LogEntity.class, codigoCorpo);
		} finally {
			manager.close();
	    }
		
		return corpo;
	}
	public Corpo buscarById(int codigoCorpo) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Corpo corpo = null;		 
		try {
			
		corpo = manager.find(Corpo.class, codigoCorpo);
		} finally {
			manager.close();
	    }
		
		return corpo;
	}
	
	public Tatuagem buscarTatuagemById(int codigoCorpo) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Tatuagem tatuagem = null;		 
		try {
			
		tatuagem = manager.find(Tatuagem.class, codigoCorpo);
		} finally {
			manager.close();
	    }
		
		return tatuagem;
	}
	
	public Cicatriz buscarCicatrizById(int codigoCorpo) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Cicatriz cicatriz = null;		 
		try {
			
			cicatriz = manager.find(Cicatriz.class, codigoCorpo);
		} finally {
			manager.close();
	    }
		
		return cicatriz;
	}
	
public ArrayList<LogEntity> buscarByIdentificados() {

	EntityManager manager = new JPAUtil().getEntityManager();

	String jpql = "select u from LogEntity u ";
	
	ArrayList<LogEntity> corpos =  (ArrayList<LogEntity>) manager.createQuery(jpql, LogEntity.class).getResultList();

	manager.close();
	
	return corpos; 
}
		
	public Integer buscarUltimoRegistro() {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select max(u.codigoCorpo) from Corpo u order by u.codigoCorpo DESC )";
		
		Integer ultimoId = manager.createQuery(jpql, Integer.class)
				.getSingleResult();

		manager.close();
		
		return ultimoId; 
	}
	
	public ArrayList<Corpo> buscarCorposPorCorDePele(String corPele) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where corPele like '" + corPele + "'";
		
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
	
	public ArrayList<Corpo> buscarCorposPorFaixaEtaria(String faixaEtaria) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where faixaEtaria like '" + faixaEtaria + "'";
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorTipoCabelo(String tipoCabelo) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where tipoCabelo like '" + tipoCabelo + "'";
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	
	public ArrayList<Corpo> buscarCorposPorSexo(String sexo) {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u where sexo like '" + sexo + "'";
		
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
		
	public ArrayList<Corpo> buscarTodos() {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<Corpo> corpos = new ArrayList<Corpo>();
		
		String jpql = "select u from Corpo u" ;
		
		corpos =  (ArrayList<Corpo>) manager.createQuery(jpql, Corpo.class).getResultList();

		manager.close();
		
		return corpos; 
	}
	public ArrayList<LogEntity> buscarTodosLog() {

		EntityManager manager = new JPAUtil().getEntityManager();
		ArrayList<LogEntity> corpos = new ArrayList<LogEntity>();
		
		String jpql = "select u from LogEntity u" ;
		
		corpos =  (ArrayList<LogEntity>) manager.createQuery(jpql, LogEntity.class).getResultList();

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
	public List<Corpo> buscaPorParam(String param,String val) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select u from Corpo u where "
				+ " (u."+param+") = :login order by u."+param;
		
		List<Corpo> encontrado = manager.createQuery(jpql, Corpo.class)
				.setParameter("login",Integer.parseInt(val)).getResultList();

		manager.close();
		
		return encontrado; 
	}
	public List<LogEntity> buscaPorParamLog(String param,String val) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select u from LogEntity u where "
				+ " (u."+param+") = :login order by u."+param;
		
		List<LogEntity> encontrado = manager.createQuery(jpql, LogEntity.class)
				.setParameter("login",Integer.parseInt(val)).getResultList();

		manager.close();
		
		return encontrado; 
	}

	public LogEntity buscarLogPorCorpo(int codigoCorpo) {
		List<LogEntity> listaLog = buscaPorParamLog("corpo.codigoCorpo",String.valueOf(codigoCorpo));
		if(listaLog.isEmpty()) {
			return null;
		}else {
			return listaLog.get(0);
		}
	}
	
	public LogEntity buscarIdentificado(int codigoCorpo) {
		EntityManager manager = new JPAUtil().getEntityManager();
		LogEntity log = null;		 
		try {
			
			String jpql = "select u from LogEntity u where corpo_codigoCorpo = " + codigoCorpo  ;
			
			log =  manager.createQuery(jpql, LogEntity.class).getSingleResult();
		}catch (Exception e) {
			log = null;
		} finally {
			manager.close();
	    }
		
		return log;
	}

}
