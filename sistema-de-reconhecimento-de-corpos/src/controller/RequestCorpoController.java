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

import dao.CorpoDAO;
import entities.Cicatriz;
import entities.Corpo;
import entities.LogEntity;
import entities.Tatuagem;
import enums.GenericEnum;

@ManagedBean
@RequestScoped
@MultipartConfig
public class RequestCorpoController extends GenericController{
	
	public static final String IMAGEM1 = "imagem1.jpg";
	public static final String IMAGEM2 = "imagem2.jpg";
	public static final String IMAGEM3 = "imagem3.jpg";
	public static final String IMAGEM4 = "imagem4.jpg";
	public static final String IMAGEM5 = "imagem5.jpg";
	public static final String IMAGEM6 = "imagem6.jpg";
	public static final String IMAGEM7 = "imagem7.jpg";
	public static final String IMAGEM8 = "imagem8.jpg";
	public static final String IMAGEM9 = "imagem9.jpg";

	private Corpo corpo;
	private CorpoDAO corpoDAO;
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
	private Part semImg;
	GenericEnum genEnum;
	public ArrayList<String> diretorio; 
	
	//Objetos Auxiliares
	private Tatuagem tatuagem;
	private Cicatriz cicatriz;
	
	//Booleans Tatuagem
	private boolean tattoRosto;
	private boolean tattoPeito;
	private boolean tattoCostas;
	private boolean tattoBraco;
	private boolean tattoPerna;
	private boolean tattoPe;
	
	//Booleans Cicatriz
	private boolean cicatrizRosto;
	private boolean cicatrizPeito;
	private boolean cicatrizCostas;
	private boolean cicatrizBraco;
	private boolean cicatrizPerna;
	private boolean cicatrizPe;
	
	//Chances da Pesquisa
	static final Double CHANCE_COR_PELE = 10.00;
	static final Double CHANCE_SEXO = 20.00;
	static final Double CHANCE_COR_OLHOS = 5.00;
	static final Double CHANCE_COR_CABELO = 2.50;
	static final Double CHANCE_TIPO_CABELO = 2.50;
	static final Double CHANCE_FAIXA_ETARIA = 10.00;
	static final Double CHANCE_ALTURA = 10.00;
	static final Double CHANCE_PESO = 10.00;
	static final Double CHANCE_TATUAGEM = 2.50;
	static final Double CHANCE_CICATRIZ = 2.50;
	
	private boolean img1;
	private boolean img2;
	private boolean img3;
	private boolean img4;
	private boolean img5;
	private boolean img6;
	private boolean img7;
	private boolean img8;
	private boolean img9;
	private boolean semImgPrincipal;
	
	//Construtor
	public RequestCorpoController() {
	    this.corpoDAO = new CorpoDAO();
	    this.corpo = new Corpo();
	    this.tatuagem = new Tatuagem();
	    this.cicatriz = new Cicatriz();
	}
	
	//Metodos		
 
	 public String buscarById(int codigoCorpo) {
		 int cont = 1;
		 diretorio = new ArrayList<String>();
		 FacesContext context = FacesContext.getCurrentInstance();
		 HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
		 String path =  myRequest.getServletContext().getRealPath("pasta_corpo"+codigoCorpo);

		 corpo = corpoDAO.buscarById(codigoCorpo);
		 File file = new File(path);
		 File[] arq =  file.listFiles();
		 
		 for (int i = 0; i <arq.length ; i++) {
			File nome = arq[i];
			System.out.println(nome.getName());
			
		 }
		 
		
		for (int i = 0; i < 9; i++) {
			
			if(cont <= arq.length) {
				diretorio.add(i, "pasta_corpo"+codigoCorpo+"\\imagem"+cont+".jpg	");
			}else {
				diretorio.add("-");
			}
			
			cont++;
		}
		//diretorio = "pasta_corpo10/imagem1.jpg";
		return "detalheCorpo.xhtml?faces-redirect=true";
	 }
	
 
	 public void cadastrar() throws Exception {
		 
		 try {
			 
			 confereVazios();
			 validaCampos();
			 
			 Integer codigoCorpo = 0;
			 Integer ultimoRegistro = corpoDAO.buscarUltimoRegistro();
			 
			 if(ultimoRegistro != null) {
				 codigoCorpo = ultimoRegistro + 1;
			 }	
			 
			 preencheTatto(codigoCorpo);
			 preencheCicatriz(codigoCorpo);
			 
			 FacesContext context = FacesContext.getCurrentInstance();
			 HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
			 String path =  myRequest.getServletContext().getRealPath("pasta_corpo"+codigoCorpo);
			 //File dirPrincipal = new File(GenericEnum.dir_img);
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
			 //ValidationFile v  = new ValidationFile();
			 //v.validarExtensaoArquivo(arq1);
			 salvarImagem(salvarEm);
	 		 
			 this.getCorpo().setCodigoCorpo(codigoCorpo);
			 this.getCorpo().setIdentificado(0);
			 this.getCorpoDAO().adiciona(this.getCorpo());
			 this.getCorpoDAO().adicionaTatuagem(this.getTatuagem());
			 this.getCorpoDAO().adicionaCicatriz(this.getCicatriz());
			 
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO - Corpo cadastrado com sucesso. Codigo Corpo = " +codigoCorpo, "SUCESSO - Corpo cadastrado com sucesso!"));
			 
		 }catch (Exception e) {
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foi possivel cadastrar este corpo, revise os parametros informados.", 
								"ERRO - Nao foi possivel cadastrar este corpo, revise os parametros informados."));
		}
	        
	 }
	 
	 private void validaCampos() throws Exception {
		if(this.getCorpo().getAltura() == null
				&& this.getCorpo().getTipoCabelo() == null
				&& this.getCorpo().getCorCabelo() == null
				&& this.getCorpo().getCorOlhos() == null
				&& this.getCorpo().getCorPele() == null
				&& this.getCorpo().getFaixaEtaria() == null
				&& this.getCorpo().getPeso() == null
				&& this.getCorpo().getSexo() == null) {
			throw new Exception("Campos invalidos");
		}
		
	}

	private void confereVazios() {
		if(this.getCorpo().getTipoCabelo().isEmpty())
			this.getCorpo().setTipoCabelo(null);
		if(this.getCorpo().getCorCabelo().isEmpty())
			this.getCorpo().setCorCabelo(null);
		if(this.getCorpo().getCorOlhos().isEmpty())
			this.getCorpo().setCorOlhos(null);
		if(this.getCorpo().getCorPele().isEmpty())
			this.getCorpo().setCorPele(null);
		if(this.getCorpo().getFaixaEtaria().isEmpty())
			this.getCorpo().setFaixaEtaria(null);
		if(this.getCorpo().getDescricao().isEmpty())
			this.getCorpo().setDescricao(null);
		if(this.getCorpo().getDataChegada().isEmpty())
			this.getCorpo().setDataChegada(null);
		if(this.getCorpo().getHorarioChegada().isEmpty())
			this.getCorpo().setHorarioChegada(null);
		if(this.getCorpo().getDestino().isEmpty())
			this.getCorpo().setDestino(null);
		if(this.getCorpo().getLatitude().isEmpty())
			this.getCorpo().setLatitude(null);
		if(this.getCorpo().getLongitude().isEmpty())
			this.getCorpo().setLongitude(null);
		if(this.getCorpo().getSexo().isEmpty())
			this.getCorpo().setSexo(null);
		
	}

	/* PESQUISAR
	  * 
	  *  METODO RESPONSAVEL POR BATER INFORMACAO POR INFORMACAO PARA ACHAR OS CORPOS COM
	  *  MAIOR CHANCE DE SER O CORPO BUSCADO E ENVIAR PARA TELA DE LISTAGEM
	  */
	 public String pesquisar() throws Exception {
		 
		 try {
			 
			 int contPesquisa;
			 
			 //Busca todos os arrays
			 ArrayList<Corpo> corposPorCorPele = corpoDAO.buscarCorposPorCorDePele(this.getCorpo().getCorPele());
			 ArrayList<Corpo> corposPorCorOlhos = corpoDAO.buscarCorposPorCorOlhos(this.getCorpo().getCorOlhos());
			 ArrayList<Corpo> corposPorCorCabelo = corpoDAO.buscarCorposPorCorCabelo(this.getCorpo().getCorCabelo());
			 ArrayList<Corpo> corposPorFaixaEtaria = corpoDAO.buscarCorposPorFaixaEtaria(this.getCorpo().getFaixaEtaria());
			 ArrayList<Corpo> corposPorTipoCabelo = corpoDAO.buscarCorposPorTipoCabelo(this.getCorpo().getTipoCabelo());
			 ArrayList<Corpo> corposPorSexo = corpoDAO.buscarCorposPorSexo(this.getCorpo().getSexo());
			 ArrayList<Corpo> corposPorAltura = null;
			 ArrayList<Corpo> corposPorPeso = null;
			 
			 if(this.getCorpo().getAltura() != null) {
				corposPorAltura = corpoDAO.buscarCorposPorAlturaAproximada(this.getCorpo().getAltura());
			 }
			 if(this.getCorpo().getPeso() != null) {
				 corposPorPeso = corpoDAO.buscarCorposPorPesoAproximado(this.getCorpo().getPeso());
			 }
			 
			 ArrayList<Corpo> corpos = new  ArrayList<Corpo>();
			 
			 //Realiza calculos das chances do corpo ser o que foi buscado
			 
			//Soma Chance Tipo Cabelo
			 if(corposPorTipoCabelo != null){
				 realizaSomatorioChance(corpos,corposPorTipoCabelo,CHANCE_TIPO_CABELO);
			 }
			 
			//Soma Chance Sexo
			 if(corposPorSexo != null){
				 realizaSomatorioChance(corpos,corposPorSexo,CHANCE_SEXO);
			 }
			 
			//Soma Chance Cor Pele
			 if(corposPorCorPele != null){
				 realizaSomatorioChance(corpos,corposPorCorPele,CHANCE_COR_PELE);
			 }
			 
			 				
			//Soma Chance Cor Olhos
			 if(corposPorCorOlhos != null){
				 realizaSomatorioChance(corpos,corposPorCorOlhos,CHANCE_COR_OLHOS);
			 }
			 
			//Soma Chance Cor Cabelo
			 if(corposPorCorCabelo != null){
				 realizaSomatorioChance(corpos,corposPorCorCabelo,CHANCE_COR_CABELO);
			 }
			 
			 //Soma Chance Faixa Etaria
			 if(corposPorFaixaEtaria != null) {
				 realizaSomatorioChance(corpos,corposPorFaixaEtaria,CHANCE_FAIXA_ETARIA);
			 }
			 
			 //Soma Chance Altura
			 if(corposPorAltura != null) {
				 realizaSomatorioChance(corpos,corposPorAltura,CHANCE_ALTURA);
			 }
			 
			//Soma Chance Peso
			 if(corposPorPeso != null) {
				 realizaSomatorioChance(corpos,corposPorPeso,CHANCE_PESO);
			 }
			 
			//Soma Chance Tatuagem
			 
			//Zerando Variaveis auxiliares
			 Tatuagem tatuagemAUX = new Tatuagem();
			 Tatuagem tatuagemDAO = new Tatuagem();
			 
			 
			 for(contPesquisa = 0; contPesquisa < corpos.size(); contPesquisa++) {
				 tatuagemDAO = corpoDAO.buscarTatuagemById(corpos.get(contPesquisa).getCodigoCorpo());
				 if(tatuagemDAO != null) {
					 tatuagemAUX = conveterTatuagensParaObjeto();
					 
					 if(tatuagemAUX.getBraco() == tatuagemDAO.getBraco())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
					 if(tatuagemAUX.getCostas()== tatuagemDAO.getCostas())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
					 if(tatuagemAUX.getPe() == tatuagemDAO.getPe())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
					 if(tatuagemAUX.getPeito() == tatuagemDAO.getPeito())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
					 if(tatuagemAUX.getPerna() == tatuagemDAO.getPerna())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
					 if(tatuagemAUX.getRosto() == tatuagemDAO.getRosto())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_TATUAGEM);
				 }
				
			 }
			 			 
			//Soma Chance Tatuagem caso tenha sido encontrado algum corpo
			//Zerando Variaveis auxiliares
			 Cicatriz cicatrizAUX = new Cicatriz();
			 Cicatriz cicatrizDAO = new Cicatriz();
			 
			 for(contPesquisa = 0; contPesquisa < corpos.size(); contPesquisa++) {
				 cicatrizDAO = corpoDAO.buscarCicatrizById(corpos.get(contPesquisa).getCodigoCorpo());
				 if(cicatrizDAO != null) {
					 cicatrizAUX = conveterCicatrizParaObjeto();
					 
					 if(cicatrizAUX.getBraco() == cicatrizDAO.getBraco())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
					 if(cicatrizAUX.getCostas()== cicatrizDAO.getCostas())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
					 if(cicatrizAUX.getPe() == cicatrizDAO.getPe())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
					 if(cicatrizAUX.getPeito() == cicatrizDAO.getPeito())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
					 if(cicatrizAUX.getPerna() == cicatrizDAO.getPerna())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
					 if(cicatrizAUX.getRosto() == cicatrizDAO.getRosto())
						 corpos.get(contPesquisa).setChance(corpos.get(contPesquisa).getChance() + CHANCE_CICATRIZ);
				 }
				
			 }
			
			 LogEntity log = new LogEntity();
			 
			 ArrayList<Corpo> retorno = new  ArrayList<Corpo>();

			 
			 //Imprime chances dos corpos no console para apoio ao DEV/TESTE E Remocao CHANCES 100%
			 for(int contAux = 0; contAux < corpos.size(); contAux++) {
				 log = null;
				 if(corpos.get(contAux).getChance() >= 100)
					 corpos.get(contAux).setChance(99.00);
				 log = corpoDAO.buscarIdentificado(corpos.get(contAux).getCodigoCorpo());
				 if(log != null && log.getConfirmado() == 1) {
					 System.out.println("CORPO" + contAux + "REMOVIDO DA BUSCA");
				 }else {
					 retorno.add(corpos.get(contAux));
				 }
				 System.out.println("CORPO " + contAux + " ID " + corpos.get(contAux).getCodigoCorpo() + " CHANCE: " + corpos.get(contAux).getChance());
			 }
			 
			 
			 
			 if(retorno.isEmpty()) {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foram encontrados corpos.", "ERRO - Nao foram encontrados corpos."));
				 return "";
			 }
			 
			 FacesContext context = FacesContext.getCurrentInstance();
			 HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
			 HttpSession session = myRequest.getSession();
			 session.setAttribute("arrayCorpos", retorno);
			 
		}catch (Exception e) {
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Erro na busca de corpos.", "ERRO - Erro na busca de corpos."));
			 return "";
		}
		 
		 return "listaCorpos.xhtml?faces-redirect=true";
		 
	 }
	 
	private void realizaSomatorioChance(ArrayList<Corpo> corpos, ArrayList<Corpo> corpoPorParametro, Double chancePesquisa){
		
		 //Declarando Variaveis auxiliares
		 int indexAux = 0;
		 Double valorAux = 0.0;
		 boolean contains = false;
		 int contAux = 0;
		 int corposSize = corpos.size(); // Tem que ser declarado antes para nao acontecer loop
		 
		 for(int contPesquisa = 0; contPesquisa < corpoPorParametro.size(); contPesquisa++) {
			 
			 //Contains por codigo pois o parametro chance sempre muda, o que torna a funcao contains inviavel.
			 for( contAux = 0; contAux < corposSize; contAux++) {
				 if(corpoPorParametro.get(contPesquisa).getCodigoCorpo() == corpos.get(contAux).getCodigoCorpo()) 
					 contains = true;
			 }
			 
			 //Se o corpo nao existe no array principal realizar soma de chance e adicionar no array principal
			 if(contains != true) {
				 if( corpoPorParametro.get(contPesquisa).getChance() == null) {
					 corpoPorParametro.get(contPesquisa).setChance(chancePesquisa);
				 }else {
					 valorAux = corpoPorParametro.get(contPesquisa).getChance() + chancePesquisa;
					 corpoPorParametro.get(contPesquisa).setChance(valorAux);
					 valorAux = 0.0;
				 }
				 corpos.add(corpoPorParametro.get(contPesquisa));
				 
			 //Se corpo existe no array principal somar chance correspondente ao valor atual de chance.
			 }else {
				 for( contAux = 0; contAux < corposSize; contAux++) {
					 if(corpoPorParametro.get(contPesquisa).getCodigoCorpo() == corpos.get(contAux).getCodigoCorpo()) {
						 indexAux = contAux;
						 break;
					 }
				 }
				 valorAux = corpos.get(contAux).getChance() + chancePesquisa;
				 corpos.get(indexAux).setChance(valorAux);
				 
			 }
		 }
	}
	 
	private Tatuagem conveterTatuagensParaObjeto() {
		
		Tatuagem tatuagemRetorno = new Tatuagem();
		
		if(tattoBraco == true) tatuagemRetorno.setBraco(1); else tatuagemRetorno.setBraco(0);
		if(tattoCostas == true) tatuagemRetorno.setCostas(1); else tatuagemRetorno.setCostas(0);
		if(tattoPe == true) tatuagemRetorno.setPe(1); else tatuagemRetorno.setPe(0);
		if(tattoPeito == true) tatuagemRetorno.setPeito(1); else tatuagemRetorno.setPeito(0);
		if(tattoPerna == true) tatuagemRetorno.setPerna(1); else tatuagemRetorno.setPerna(0);
		if(tattoRosto == true) tatuagemRetorno.setRosto(1); else tatuagemRetorno.setRosto(0);
				
		return tatuagemRetorno;
		
	}
	
	private Cicatriz conveterCicatrizParaObjeto() {
		
		Cicatriz cicatrizRetorno = new Cicatriz();
		
		if(cicatrizBraco == true) cicatrizRetorno.setBraco(1); else cicatrizRetorno.setBraco(0);
		if(cicatrizCostas == true) cicatrizRetorno.setCostas(1); else cicatrizRetorno.setCostas(0);
		if(cicatrizPe == true) cicatrizRetorno.setPe(1); else cicatrizRetorno.setPe(0);
		if(cicatrizPeito == true) cicatrizRetorno.setPeito(1); else cicatrizRetorno.setPeito(0);
		if(cicatrizPerna == true) cicatrizRetorno.setPerna(1); else cicatrizRetorno.setPerna(0);
		if(cicatrizRosto == true) cicatrizRetorno.setRosto(1); else cicatrizRetorno.setRosto(0);
				
		return cicatrizRetorno;
		
	}

	public boolean salvarImagem(String salvarEm) {
		 try {
			 
			 if(!isImg1() && !isImg2() && !isImg3() && !isImg4() && !isImg5() && !isImg6() && !isImg7() && !isImg8() && !isImg9() && !isSemImgPrincipal()) {
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
			 }else {

				 if(isSemImgPrincipal()) {
					 if(arq1 != null) { 
							 arq1.write(salvarEm + IMAGEM2);
					 }
					 if(arq2 != null) { 
						 arq2.write(salvarEm + IMAGEM3);
					 }
					 if(arq3 != null) { 
						 arq3.write(salvarEm + IMAGEM4);
					 }
					 if(arq4 != null) { 
						 arq4.write(salvarEm + IMAGEM5);
					 }
					 if(arq5 != null) { 
						 arq5.write(salvarEm + IMAGEM6);
					 }
					 if(arq6 != null) { 
						 arq6.write(salvarEm + IMAGEM7);
					 }
					 if(arq7 != null) { 
						 arq7.write(salvarEm + IMAGEM8);
					 }
					 if(arq8 != null) { 
						 arq8.write(salvarEm + IMAGEM9);
					 }
				 }else {
					 
					 if(arq2 != null) {
						 if(isImg2()) {
							 arq2.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM2);
						 }else {
							 arq2.write(salvarEm + IMAGEM2);
						 }
					 }
					 
					 if(arq3 != null) {
						 if(isImg3()) {
							 arq3.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM3);
						 }else {
							 arq3.write(salvarEm + IMAGEM3);
	
						 }
					 }
					 
					 if(arq4 != null) {
						 if(isImg4()) {
							 arq4.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM4);
	
						 }else {
							 arq4.write(salvarEm + IMAGEM4);
						 }
					 }
					 
					 if(arq5 != null) {
						 if(isImg5()) {
							 arq5.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM5);
						 }else {
							 arq5.write(salvarEm + IMAGEM5);
	
						 }
					 }
					 
					 if(arq6 != null) {
						 if(isImg6()) {
							 arq6.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM6);
						 }else {
							 arq6.write(salvarEm + IMAGEM6);
						 }
					 }
					 
					 if(arq7 != null) {
						 if(isImg7()) {
							 arq7.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM7);
						 }else {
							 arq7.write(salvarEm + IMAGEM7);
						 }
					 }
					 
					 if(arq8 != null) {
						 if(isImg8()) {
							 arq8.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM8);
						 }else {
							 arq8.write(salvarEm + IMAGEM8);
						 }
					 }
					 
					 if(arq9 != null) {
						 if(isImg9()) {
							 arq9.write(salvarEm + IMAGEM1);
							 arq1.write(salvarEm + IMAGEM9);
						 }else {
							 arq9.write(salvarEm + IMAGEM9);
						 }
					 }
				 }
			 }
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return true;
	 }
	
	 
	 public Part getSemImg() {
		return semImg;
	}

	public void setSemImg(Part semImg) {
		this.semImg = semImg;
	}

	public boolean isImg1() {
		return img1;
	}

	public void setImg1(boolean img1) {
		this.img1 = img1;
	}

	public boolean isImg2() {
		return img2;
	}

	public void setImg2(boolean img2) {
		this.img2 = img2;
	}

	public boolean isImg3() {
		return img3;
	}

	public void setImg3(boolean img3) {
		this.img3 = img3;
	}

	public boolean isImg4() {
		return img4;
	}

	public void setImg4(boolean img4) {
		this.img4 = img4;
	}

	public boolean isImg5() {
		return img5;
	}

	public void setImg5(boolean img5) {
		this.img5 = img5;
	}

	public boolean isImg6() {
		return img6;
	}

	public void setImg6(boolean img6) {
		this.img6 = img6;
	}

	public boolean isImg7() {
		return img7;
	}

	public void setImg7(boolean img7) {
		this.img7 = img7;
	}

	public boolean isImg8() {
		return img8;
	}

	public void setImg8(boolean img8) {
		this.img8 = img8;
	}

	public boolean isImg9() {
		return img9;
	}

	public void setImg9(boolean img9) {
		this.img9 = img9;
	}

	public boolean isSemImgPrincipal() {
		return semImgPrincipal;
	}

	public void setSemImgPrincipal(boolean semImgPrincipal) {
		this.semImgPrincipal = semImgPrincipal;
	}

	private void preencheCicatriz(Integer codigoCorpo) {
		 this.getCicatriz().setCodigoCorpo(codigoCorpo);
		 this.getCicatriz().setRosto(isCicatrizRosto() ? 1 : 0);
		 this.getCicatriz().setBraco(isCicatrizBraco() ? 1 : 0);
		 this.getCicatriz().setCostas(isCicatrizCostas() ? 1 : 0);
		 this.getCicatriz().setPe(isCicatrizPe() ? 1 : 0);
		 this.getCicatriz().setPerna(isCicatrizPerna() ? 1 : 0);
		 this.getCicatriz().setPeito(isCicatrizPeito() ? 1 : 0);
	}

	private void preencheTatto(Integer codigoCorpo) {
		 this.getTatuagem().setCodigoCorpo(codigoCorpo);
		 this.getTatuagem().setRosto(isTattoRosto() ? 1 : 0);
		 this.getTatuagem().setBraco(isTattoBraco() ? 1 : 0);
		 this.getTatuagem().setCostas(isTattoCostas() ? 1 : 0);
		 this.getTatuagem().setPe(isTattoPe() ? 1 : 0);
		 this.getTatuagem().setPerna(isTattoPerna() ? 1 : 0);
		 this.getTatuagem().setPeito(isTattoPeito() ? 1 : 0);
		 
	}
	 
	 //Getters and Setters
	
	public Corpo getCorpo() {
		return corpo;
	}

	public void setCorpo(Corpo corpo) {
		this.corpo = corpo;
	}
	
	public CorpoDAO getCorpoDAO() {
		return corpoDAO;
	}

	public void setCorpoDAO(CorpoDAO corpoDAO) {
		this.corpoDAO = corpoDAO;
	}
	
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

	public Tatuagem getTatuagem() {
		return tatuagem;
	}

	public void setTatuagem(Tatuagem tatuagem) {
		this.tatuagem = tatuagem;
	}

	public boolean isTattoPe() {
		return tattoPe;
	}

	public void setTattoPe(boolean tattoPe) {
		this.tattoPe = tattoPe;
	}
	
	public Cicatriz getCicatriz() {
		return cicatriz;
	}

	public void setCicatriz(Cicatriz cicatriz) {
		this.cicatriz = cicatriz;
	}
	
	public boolean isCicatrizRosto() {
		return cicatrizRosto;
	}

	public void setCicatrizRosto(boolean cicatrizRosto) {
		this.cicatrizRosto = cicatrizRosto;
	}

	public boolean isCicatrizPeito() {
		return cicatrizPeito;
	}

	public void setCicatrizPeito(boolean cicatrizPeito) {
		this.cicatrizPeito = cicatrizPeito;
	}

	public boolean isCicatrizCostas() {
		return cicatrizCostas;
	}

	public void setCicatrizCostas(boolean cicatrizCostas) {
		this.cicatrizCostas = cicatrizCostas;
	}

	public boolean isCicatrizBraco() {
		return cicatrizBraco;
	}

	public void setCicatrizBraco(boolean cicatrizBraco) {
		this.cicatrizBraco = cicatrizBraco;
	}

	public boolean isCicatrizPerna() {
		return cicatrizPerna;
	}

	public void setCicatrizPerna(boolean cicatrizPerna) {
		this.cicatrizPerna = cicatrizPerna;
	}

	public boolean isCicatrizPe() {
		return cicatrizPe;
	}

	public void setCicatrizPe(boolean cicatrizPe) {
		this.cicatrizPe = cicatrizPe;
	}

	public boolean isTattoRosto() {
		return tattoRosto;
	}

	public void setTattoRosto(boolean tattoRosto) {
		this.tattoRosto = tattoRosto;
	}

	public boolean isTattoPeito() {
		return tattoPeito;
	}

	public void setTattoPeito(boolean tattoPeito) {
		this.tattoPeito = tattoPeito;
	}

	public boolean isTattoCostas() {
		return tattoCostas;
	}

	public void setTattoCostas(boolean tattoCostas) {
		this.tattoCostas = tattoCostas;
	}

	public boolean isTattoBraco() {
		return tattoBraco;
	}

	public void setTattoBraco(boolean tattoBraco) {
		this.tattoBraco = tattoBraco;
	}

	public boolean isTattoPerna() {
		return tattoPerna;
	}

	public void setTattoPerna(boolean tattoPerna) {
		this.tattoPerna = tattoPerna;
	}
	
	
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
	
}
