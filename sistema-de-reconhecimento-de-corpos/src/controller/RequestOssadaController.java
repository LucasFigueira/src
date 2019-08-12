package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.OssadaDAO;
import entities.Dentes;
import entities.Ossada;
import enums.GenericEnum;
import service.ValidationFile;

@ManagedBean
@RequestScoped
@MultipartConfig
public class RequestOssadaController extends GenericController{
	
	public static final String IMAGEM1 = "imagem1.jpg";
	public static final String IMAGEM2 = "imagem2.jpg";
	public static final String IMAGEM3 = "imagem3.jpg";
	public static final String IMAGEM4 = "imagem4.jpg";
	public static final String IMAGEM5 = "imagem5.jpg";
	public static final String IMAGEM6 = "imagem6.jpg";
	public static final String IMAGEM7 = "imagem7.jpg";
	public static final String IMAGEM8 = "imagem8.jpg";
	public static final String IMAGEM9 = "imagem9.jpg";
	private Ossada ossada;
	private Dentes dente =  new Dentes();
	public Dentes getDente() {
		return dente;
	}

	public void setDente(Dentes dente) {
		this.dente = dente;
	}
	private OssadaDAO ossadaDAO;
	public OssadaDAO getOssadaDAO() {
		return ossadaDAO;
	}

	public void setOssadaDAO(OssadaDAO ossadaDAO) {
		this.ossadaDAO = ossadaDAO;
	}
	//Booleans Dentes
		private boolean incisivos;
		public boolean isIncisivos() {
			return incisivos;
		}

		public void setIncisivos(boolean incisivos) {
			this.incisivos = incisivos;
		}

		public boolean isCanino() {
			return canino;
		}

		public void setCanino(boolean canino) {
			this.canino = canino;
		}

		public boolean isPreMolares() {
			return preMolares;
		}

		public void setPreMolares(boolean preMolares) {
			this.preMolares = preMolares;
		}

		public boolean isMolares() {
			return molares;
		}

		public void setMolares(boolean molares) {
			this.molares = molares;
		}

		public boolean isSisos() {
			return sisos;
		}

		public void setSisos(boolean sisos) {
			this.sisos = sisos;
		}
		private boolean canino;
		private boolean preMolares;
		private boolean molares;
		private boolean sisos;

	private String dataChegada;
	private String horarioChegada;
	private Part arq1;
	private Part arq2;
	private Part arq3;
	private Part arq4;
	private Part arq5;
	private Part arq6;
	private Part arq7;
	private Part arq8;
	private Part arq9;
	GenericEnum genEnum;
	public ArrayList<String> diretorio; 
	
	
	
	//Chances da Pesquisa
	static final Double CHANCE_COR_PELE = 30.00;
	static final Double CHANCE_COR_OLHOS = 5.00;
	static final Double CHANCE_COR_CABELO = 5.00;
	static final Double CHANCE_ALTURA = 10.00;
	static final Double CHANCE_IDADE = 10.00;
	static final Double CHANCE_PESO = 10.00;
	static final Double CHANCE_TATUAGEM = 2.50;
	static final Double CHANCE_CICATRIZ = 2.50;
	

	//Construtor
		
	public Part getArq1() {
		return arq1;
	}

	public void setArq1(Part arq1) {
		this.arq1 = arq1;
	}

	public Part getArq2() {
		return arq2;
	}

	public void setArq2(Part arq2) {
		this.arq2 = arq2;
	}

	public Part getArq3() {
		return arq3;
	}

	public void setArq3(Part arq3) {
		this.arq3 = arq3;
	}

	public Part getArq4() {
		return arq4;
	}

	public void setArq4(Part arq4) {
		this.arq4 = arq4;
	}

	public Part getArq5() {
		return arq5;
	}

	public void setArq5(Part arq5) {
		this.arq5 = arq5;
	}

	public Part getArq6() {
		return arq6;
	}

	public void setArq6(Part arq6) {
		this.arq6 = arq6;
	}
	
	public Part getArq7() {
		return arq7;
	}

	public void setArq7(Part arq7) {
		this.arq7 = arq7;
	}

	public Part getArq8() {
		return arq8;
	}

	public void setArq8(Part arq8) {
		this.arq8 = arq8;
	}

	public Part getArq9() {
		return arq9;
	}

	public void setArq9(Part arq9) {
		this.arq9 = arq9;
	}

	public ArrayList<String> getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(ArrayList<String> diretorio) {
		this.diretorio = diretorio;
	}

	public RequestOssadaController() {
	    this.ossadaDAO = new OssadaDAO();
	    this.ossada = new Ossada();
	    this.dente = new Dentes();
	}
		
 
	 private void preencheDentes(Integer codigoCorpo) {
		 this.getDente().setCodigoOssada(codigoCorpo);
		 this.getDente().setCanino(isCanino() ? 1 : 0);
		 this.getDente().setIncisivo( isIncisivos() ? 1 : 0);
		 this.getDente().setMolares(isMolares() ? 1 : 0);
		 this.getDente().setPremolares(isPreMolares() ? 1 : 0);
		 this.getDente().setSiso(isSisos() ? 1: 0);
	}
	 
	 private void confereVazios() {
		 
			if(this.getOssada().getLatitude().isEmpty())
				this.getOssada().setLatitude(null);
			if(this.getOssada().getLongitude().isEmpty())
				this.getOssada().setLongitude(null);
			if(this.getOssada().getDescricao().isEmpty())
				this.getOssada().setDescricao(null);
			if(this.getOssada().getDataChegada().isEmpty())
				this.getOssada().setDataChegada(null);
			if(this.getOssada().getHorarioChegada().isEmpty())
				this.getOssada().setHorarioChegada(null);
			if(this.getOssada().getFaixaEtaria().isEmpty())
				this.getOssada().setFaixaEtaria(null);
			if(this.getOssada().getTipoRacial().isEmpty())
				this.getOssada().setTipoRacial(null);
			if(this.getOssada().getPeso().isEmpty())
				this.getOssada().setPeso(null);
			
		}
	 
	 public void cadastrar() throws Exception {
		 
		 try {
			 
			 confereVazios();
			 
			 Integer codigoOssada = 0;
			 
			 Integer ultimoRegistro = ossadaDAO.buscarUltimoRegistro();
			 
			 if(ultimoRegistro != null) {
				 codigoOssada = ultimoRegistro + 1;
			 }	
			 
			 FacesContext context = FacesContext.getCurrentInstance();
			 HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
			 String path =  myRequest.getServletContext().getRealPath("pasta_ossada"+codigoOssada);
			 //File dirPriile(GenericEnum.dir_img);
			/* if(!dirPrincipal.exists()) {
				 dirPrincipal.mkdir();
			 }*/
			// String path = GenericEnum.dir_img + "\\pasta_corpo"+codigoCorpo;
			 String salvarEm = path + "\\";
			 File pasta = new File(salvarEm);
	 
			 if(!pasta.exists()) {
				 pasta.mkdir();
			 }
			 System.out.println(salvarEm);
			 ValidationFile v  = new ValidationFile();
			 //v.validarExtensaoArquivo(arq1);
			 salvarImagem(salvarEm);
			 this.getOssada().setCodigoOssada(codigoOssada);
			 this.preencheDentes(codigoOssada);
			 this.getOssadaDAO().adiciona(this.getOssada());
			 this.getOssadaDAO().adicionaDentes(this.getDente());
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO - Ossada cadastrado com sucesso. Codigo Ossada =" +codigoOssada, "SUCESSO - Corpo cadastrado com sucesso!"));
		 }catch (Exception e) {
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foi possivel cadastrar esta ossada, revise os parametros informados.", 
								"ERRO - Nao foi possivel cadastrar esta ossada, revise os parametros informados."));
		}
	        
	 }
	 
	 public Ossada getOssada() {
		return ossada;
	}

	public void setOssada(Ossada ossada) {
		this.ossada = ossada;
	}


	public boolean salvarImagem(String salvarEm) {
		 try {
			 if(arq1 != null) {
				 arq1.write(salvarEm + IMAGEM1);
			 }
			 if(arq2 != null) {
				 arq2.write(salvarEm + IMAGEM2);
			 }
			 if(arq3 != null) {
				 arq3.write(salvarEm + IMAGEM3);
			 }
			 if(arq4 != null) {
				 arq4.write(salvarEm + IMAGEM4);
			 }
			 if(arq5 != null) {
				 arq5.write(salvarEm + IMAGEM5);
			 }
			 if(arq6 != null) {
				 arq6.write(salvarEm + IMAGEM6);
			 }
			 if(arq7 != null) {
				 arq7.write(salvarEm + IMAGEM7);
			 }
			 if(arq8 != null) {
				 arq8.write(salvarEm + IMAGEM8);
			 }
			 if(arq9 != null) {
				 arq9.write(salvarEm + IMAGEM9);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return true;
	 }
	

	 
	 //Getters and Setters
	
	public String getdataChegada() {
		return dataChegada;
	}

	public void setdataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}

	public String gethorarioChegada() {
		return horarioChegada;
	}

	public void sethorarioChegada(String horarioChegada) {
		this.horarioChegada = horarioChegada;
	}
	
}
