package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.CorpoDAO;
import dao.OssadaDAO;
import entities.Cicatriz;
import entities.CicatrizAuxiliar;
import entities.Corpo;
import entities.Dentes;
import entities.DentesAuxiliar;
import entities.Ossada;
import entities.Tatuagem;
import entities.TatuagemAuxiliar;
import entities.Usuario;
import enums.GenericEnum;
import service.JPAUtil;
import service.ValidationFile;

@ManagedBean
@SessionScoped
@MultipartConfig
public class SessionOssadaController extends GenericController {
	private List<Ossada> ossadas;
	private String parametroVal;
	private int sizePaginacao;
	private ArrayList<String> paginacaoAux;
	
	public ArrayList<String> getPaginacaoAux() {
			paginacaoAux = new ArrayList<String>();
			for (int i = 0; i < this.getSizePaginacao(); i++) {
				paginacaoAux.add("");
			}
		return paginacaoAux;
	}
	public void setPaginacaoAux(ArrayList<String> paginacaoAux) {
		this.paginacaoAux = paginacaoAux;
	}
	public int getSizePaginacao() {
		int resto  = this.ossadas.size() % 10 == 0 ? 0 : 1; 
		return this.ossadas.size() / 10 + resto ;
	}
	public void setSizePaginacao(int sizePaginacao) {
		this.sizePaginacao = sizePaginacao;
	}
	public void atualizaPaginacao(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		int paginacao = Integer.parseInt(myRequest.getParameter("paginacao"));
		this.setPaginacao(paginacao-1);
		
	}
	private Ossada ossadaAlteracao;
	private Dentes dentes;
	private Dentes dentesAux;
	private DentesAuxiliar dentesAuxiliar;
	private DentesAuxiliar dentesAux2;
	private String parametroSearch;
	private int paginacao;
	private int isBusca = 0;
	public String getParametroSearch() {
		return parametroSearch;
	}
	public void setParametroSearch(String parametroSearch) {
		this.parametroSearch = parametroSearch;
	}
	public DentesAuxiliar getDentesAuxiliar() {
		return dentesAuxiliar;
	}
	public void atualizaLista() {
		if(this.getParametroVal() == ""){
			this.ossadas = this.getOssadaDAO().listaTodos();
		}else{
			this.ossadas = this.getOssadaDAO().buscaPorParam(this.getParametroSearch(), this.getParametroVal());
		}
		 isBusca = 1;
	}
	public void setDentesAuxiliar(DentesAuxiliar dentesAuxiliar) {
		this.dentesAuxiliar = dentesAuxiliar;
	}

	public DentesAuxiliar getDentesAux2() {
		return dentesAux2;
	}

	public void setDentesAux2(DentesAuxiliar dentesAux2) {
		this.dentesAux2 = dentesAux2;
	}

	public Dentes getDentesAux() {
		return dentesAux;
	}

	public void setDentesAux(Dentes dentesAux) {
		this.dentesAux = dentesAux;
	}

	public int getPaginacao() {
		return paginacao;
	}
	public void setPaginacao(int paginacao) {
		this.paginacao = paginacao;
	}
	public Ossada getOssadaAlteracao() {
		return ossadaAlteracao;
	}

	public void setOssadaAlteracao(Ossada ossadaAlteracao) {
		this.ossadaAlteracao = ossadaAlteracao;
	}

	public String getParametroVal() {
		return parametroVal;
	}

	public void setParametroVal(String parametroVal) {
		this.parametroVal = parametroVal;
	}

	public List<Ossada> getOssadas() {
		
		if(isBusca == 1 && !this.getParametroVal().equals("")){
			return this.ossadas;
		}
		this.ossadas = this.getOssadaDAO().listaTodos();
		int limite;
		if(ossadas.size() < (10+paginacao * 10)){
			limite = ossadas.size();
		}else{
			limite = 10 + paginacao * 10;
		}
		if(this.ossadas.size() > 10){
			
			ArrayList<Ossada> ossadasAux = new ArrayList<Ossada>();
			for (int i = (0 +  paginacao * 10) ; i < limite ; i++) {
				
					ossadasAux.add(this.ossadas.get(i));
					
				
			}
			return ossadasAux;
		}
		isBusca=0;
		return this.ossadas;
	}

	public String carregaDetalhes() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		int codigoOssada = Integer.parseInt(myRequest.getParameter("ossada"));
		this.setOssadaAlteracao((this.getOssadaDAO().buscarById(codigoOssada)));
		this.setOssada(this.ossadaAlteracao);
		this.dentes = this.getOssadaDAO().buscarTatuagemById(codigoOssada);
		this.buscarById(codigoOssada);
		if(this.dentes != null){
			this.dentesAuxiliar.setCanino(this.dentes.getCanino() == 1 ? true : false);
			this.dentesAuxiliar.setIncisivo(this.dentes.getIncisivo() == 1 ? true : false);
			this.dentesAuxiliar.setMolares(this.dentes.getMolares() == 1 ? true : false);
			this.dentesAuxiliar.setPremolares(this.dentes.getPremolares() == 1 ? true : false);
			this.dentesAuxiliar.setSiso(this.dentes.getSiso() == 1 ? true : false);
		}
		return "alterarOssada.xhtml?faces-redirect=true";
	}

	public Dentes getDentes() {
		return dentes;
	}

	public void setDentes(Dentes dentes) {
		this.dentes = dentes;
	}

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

	public void setOssadas(List<Ossada> ossadas) {
		this.ossadas = ossadas;
	}

	private Ossada ossada;
	private Ossada ossadaAux;

	public Ossada getOssadaAux() {
		return ossadaAux;
	}

	public void setOssadaAux(Ossada ossadaAux) {
		this.ossadaAux = ossadaAux;
	}

	private Corpo corpo;
	private OssadaDAO ossadaDAO;

	public OssadaDAO getOssadaDAO() {
		return ossadaDAO;
	}

	public void setOssadaDAO(OssadaDAO ossadaDAO) {
		this.ossadaDAO = ossadaDAO;
	}

	private CorpoDAO corpoDAO;
	private String dataChegada;
	private String horarioChegada;
	GenericEnum genEnum;
	private ArrayList<String> diretorio;

	/*
	 * //Chances da Pesquisa static final Double CHANCE_COR_PELE = 30.00; static
	 * final Double CHANCE_COR_OLHOS = 5.00; static final Double
	 * CHANCE_COR_CABELO = 5.00; static final Double CHANCE_ALTURA = 10.00;
	 * static final Double CHANCE_IDADE = 10.00; static final Double CHANCE_PESO
	 * = 10.00; static final Double CHANCE_TATUAGEM = 2.50; static final Double
	 * CHANCE_CICATRIZ = 2.50;
	 */

	public ArrayList<String> getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(ArrayList<String> diretorio) {
		this.diretorio = diretorio;
	}

	public SessionOssadaController() {
		this.ossadaDAO = new OssadaDAO();
		this.ossada = new Ossada();
		this.ossadaAux = new Ossada();
		this.dentesAux = new Dentes();
		this.ossadas = new ArrayList<Ossada>();
		this.dentesAux2 = new DentesAuxiliar();
		this.dentesAuxiliar = new DentesAuxiliar();
	}
	
	public void alterar() {

		Ossada ossadaAux = this.ossadaAux;
		Dentes dentesAux = this.dentesAux;
		// atualiza(corpoAux);

		atualiza(ossadaAux, dentesAux);
		
	}

	public void atualiza(Ossada ossada, Dentes dentesAux) {
		try{
		EntityManager manager = new JPAUtil().getEntityManager();
		ossadaAux.setCodigoOssada(this.getOssadaAlteracao().getCodigoOssada());
		
		if(this.getOssadaAlteracao().getLatitude().isEmpty()) ossadaAux.setLatitude(null);
		else ossadaAux.setLatitude(this.getOssadaAlteracao().getLatitude());
		if(this.getOssadaAlteracao().getLongitude().isEmpty()) ossadaAux.setLongitude(null);
		else ossadaAux.setLongitude(this.getOssadaAlteracao().getLongitude());
		
		this.dentesAux.setCodigoOssada(this.getOssadaAlteracao().getCodigoOssada());
		this.dentesAux.setCanino(this.dentesAux2.isCanino() ? 1 : 0);
		this.dentesAux.setIncisivo(this.dentesAux2.isIncisivo() ? 1 : 0);
		this.dentesAux.setMolares(this.dentesAux2.isMolares() ? 1 : 0);
		this.dentesAux.setPremolares(this.dentesAux2.isPremolares() ? 1 : 0);
		this.dentesAux.setSiso(this.dentesAux2.isSiso() ? 1 : 0);
		this.setDentesAuxiliar(dentesAux2);
		this.ossadaAlteracao = ossadaAux;
		
		manager.getTransaction().begin();
		manager.merge(ossadaAux);
		manager.merge(this.dentesAux);
		manager.getTransaction().commit();
		manager.close();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO - Ossada alterada com sucesso.", "SUCESSO - Ossada alterada com sucesso!"));	
		}catch(Exception e){
			 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foi possivel alterar esta ossada, revise os parametros informados.", 
								"ERRO - Nao foi possivel alterar esta ossada, revise os parametros informados."));

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
	
	private void verificaLatitudeLongitude() {
		if (ossada.getLatitude().isEmpty())
			ossada.setLatitude(null);
		if (ossada.getLongitude().isEmpty())
			ossada.setLongitude(null);
	}

	public void gerarPDF() throws DocumentException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String path = myRequest.getServletContext().getRealPath("");
		Document document = new Document();

		try {

			verificaLatitudeLongitude();

			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			Font f2 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

			// String k = "<html><body> <h1>SRC - SISTEMA DE RECONHECIMENTO DE
			// CORPOS</h1></body></html>";

			PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\pdfossada.pdf"));
			document.open();
			// HTMLWorker htmlWorker = new HTMLWorker(document);
			// htmlWorker.parse(new StringReader(k));
			// p1.setAlignment(Element.ALIGN_CENTER);
			Paragraph p = new Paragraph("SRC - SISTEMA DE RECONHECIMENTO DE CORPOS", f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(2);

			Paragraph p2 = new Paragraph("Cor da Pele", f2);

			table.addCell(p2);
			if(getOssada().getTipoRacial()!= null) {
				 table.addCell(getOssada().getTipoRacial());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }
			

			Paragraph p3 = new Paragraph("Estrutura dentaria", f2);

			table.addCell(p3);
			 if(getOssada().getEstruturaDentaria()!= null) {
				 table.addCell(getOssada().getEstruturaDentaria().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }
			Paragraph p4 = new Paragraph("Altura", f2);

			table.addCell(p4);
			if(getOssada().getAltura()!= null) {
				table.addCell(getOssada().getAltura().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }
			

			Paragraph p5 = new Paragraph("Idade", f2);

			table.addCell(p5);
			if(getOssada().getFaixaEtaria()!= null) {
				table.addCell(getOssada().getFaixaEtaria().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }
			

			Paragraph p7 = new Paragraph("Data da Chegada", f2);
			table.addCell(p7);
			if(getOssada().getDataChegada() != null) {
				table.addCell(getOssada().getDataChegada().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }
			
			

			Paragraph p8 = new Paragraph("Hora da Chegada", f2);

			table.addCell(p8);
		
			if(getOssada().getHorarioChegada() != null) {
				table.addCell(getOssada().getHorarioChegada().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }

			Paragraph p9 = new Paragraph("Observacoes", f2);

			table.addCell(p9);
			if(getOssada().getDescricao() != null) {
				table.addCell(getOssada().getDescricao().toString());
			 }else{
				 table.addCell("NAO CADASTRADO");
			 }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            int contImagens = 0;
            for (String dir : diretorio) {
				if(!dir.equalsIgnoreCase("-")) {
					contImagens++;
				}
			}
            if(contImagens%2 == 0) {
            	contImagens = 2;
            }
        	if(contImagens%3 ==0) {
        		contImagens = 3;
        	}
        	if(contImagens == 5 || contImagens == 7) {
        		contImagens = 1;
        	}
            
	

			
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

		    PdfPTable table2 = new PdfPTable(contImagens); 
            Paragraph paragrafo = new Paragraph("Fotos da Ossada", f2);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            PdfPCell header = new PdfPCell(paragrafo);
            header.setColspan(contImagens);
            header.setVerticalAlignment(Element.ALIGN_CENTER);
            header.setBorderColor(BaseColor.GRAY);
            table2.addCell(header);
            table2.getDefaultCell().setBorderColor(BaseColor.GRAY);
        if(diretorio.size() > 1 ) {
            	for (String dir : diretorio) { 
            		  
            		if(!dir.equals("-")) {
            			if(!dir.equalsIgnoreCase("http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg")) {
            				Image img = Image.getInstance(String.format("http://localhost:8080/jsf/"+ dir));
                        	img.scaleToFit(200, 200); 
                        	if(contImagens == 1) {
                        		table2.setTotalWidth(200);
                            	table2.setLockedWidth(true);
                        	}
                        	
                        	table2.addCell(img);
            			}
            			
            		}
    			}
            	document.add(table2);
        }   
		}  catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }		// Runtime.getRuntime().exec (new String[]{"cmd.exe", "/c",
		// "start","C:\\pdf\\pdfyes.pdf"});
		java.awt.Desktop.getDesktop().open(new File("C:\\pdf\\pdfossada.pdf"));

		document.close();

	}

	public void buscarById(int codigoCorpo) {
		diretorio = new ArrayList<String>();
		int cont = 1;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String path = myRequest.getServletContext().getRealPath("pasta_ossada" + codigoCorpo);

		ossada = ossadaDAO.buscarById(codigoCorpo);
		File file = new File(path);
		File[] arq = file.listFiles();
		if (arq != null) {
			for (int i = 0; i < arq.length; i++) {
				File nome = arq[i];
				System.out.println(nome.getName());

			}
		}
		for (int i = 0; i < 9; i++) {

			if (arq != null && cont <= arq.length) {
				diretorio.add(i, "pasta_ossada" + codigoCorpo + "/imagem" + cont + ".jpg	");
			} else {
				diretorio.add("-");
			}

			cont++;
		}

	}

	public Ossada getOssada() {
		return ossada;
	}

	public void setOssada(Ossada ossada) {
		this.ossada = ossada;
	}

	/*
	 * PESQUISAR
	 * 
	 * METODO RESPONSAVEL POR BATER INFORMACAO POR INFORMACAO PARA ACHAR OS
	 * CORPOS COM MAIOR CHANCE DE SER O CORPO BUSCADO E ENVIAR PARA TELA DE
	 * LISTAGEM
	 */
	/*
	 * public String pesquisar() throws Exception {
	 * 
	 * try {
	 * 
	 * int contPesquisa; Double valorAux = 0.0; int indexAux = 0;
	 * 
	 * //Busca todos os arrays ArrayList<Corpo> corposPorCorPele =
	 * corpoDAO.buscarCorposPorCorDePele(this.getCorpo().getCorPele());
	 * ArrayList<Corpo> corposPorCorOlhos =
	 * corpoDAO.buscarCorposPorCorOlhos(this.getCorpo().getCorOlhos());
	 * ArrayList<Corpo> corposPorCorCabelo =
	 * corpoDAO.buscarCorposPorCorCabelo(this.getCorpo().getCorCabelo());
	 * ArrayList<Corpo> corposPorAltura = null; ArrayList<Corpo> corposPorIdade
	 * = null; ArrayList<Corpo> corposPorPeso = null;
	 * 
	 * if(this.getCorpo().getAltura() != null) { corposPorAltura =
	 * corpoDAO.buscarCorposPorAlturaAproximada(this.getCorpo().getAltura()); }
	 * if(this.getCorpo().getIdade() != null) { corposPorIdade =
	 * corpoDAO.buscarCorposPorIdadeAproximada(this.getCorpo().getIdade()); }
	 * if(this.getCorpo().getPeso() != null) { corposPorPeso =
	 * corpoDAO.buscarCorposPorPesoAproximado(this.getCorpo().getPeso()); }
	 * 
	 * ArrayList<Corpo> corpos = new ArrayList<Corpo>();
	 * 
	 * //Realiza calculos das chances do corpo ser o que foi buscado
	 * 
	 * //Soma Chance Cor de Pele for(contPesquisa = 0; contPesquisa <
	 * corposPorCorPele.size(); contPesquisa++) {
	 * if(corposPorCorPele.get(contPesquisa) != null) { if(
	 * corposPorCorPele.get(contPesquisa).getChance() == null) {
	 * corposPorCorPele.get(contPesquisa).setChance(CHANCE_COR_PELE); }else {
	 * valorAux = corposPorCorPele.get(contPesquisa).getChance() +
	 * CHANCE_COR_PELE; corposPorCorPele.get(contPesquisa).setChance(valorAux);
	 * valorAux = 0.0; } } }
	 * 
	 * //Preenche array corpos corpos = corposPorCorPele;
	 * 
	 * //Variaveis auxiliares valorAux = 0.0; boolean contains = false; int
	 * contAux = 0; int corposSize = corpos.size(); // Tem que ser declarado
	 * antes para n�o acontecer loop
	 * 
	 * //Soma Chance Cor dos Olhos for(contPesquisa = 0; contPesquisa <
	 * corposPorCorOlhos.size(); contPesquisa++) {
	 * 
	 * //Contains por c�digo pois o parametro chance sempre muda, o que torna
	 * a funcao contains invi�vel. for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorCorOlhos.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) contains = true; }
	 * 
	 * //Se o corpo nao existe no array principal realizar soma de chance e
	 * adicionar no array principal if(contains != true) { if(
	 * corposPorCorOlhos.get(contPesquisa).getChance() == null) {
	 * corposPorCorOlhos.get(contPesquisa).setChance(CHANCE_COR_OLHOS); }else {
	 * valorAux = corposPorCorOlhos.get(contPesquisa).getChance() +
	 * CHANCE_COR_OLHOS;
	 * corposPorCorOlhos.get(contPesquisa).setChance(valorAux); valorAux = 0.0;
	 * } corpos.add(corposPorCorOlhos.get(contPesquisa));
	 * 
	 * //Se corpo existe no array principal somar chance correspondente ao valor
	 * atual de chance. }else { for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorCorOlhos.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) { indexAux = contAux; break; } }
	 * valorAux = corpos.get(contAux).getChance() + CHANCE_COR_OLHOS;
	 * corpos.get(indexAux).setChance(valorAux);
	 * 
	 * //Reset de auxiliares valorAux = 0.0; indexAux = 0; contains = false; } }
	 * 
	 * //Zerando Variaveis auxiliares indexAux = 0; valorAux = 0.0; contains =
	 * false; contAux = 0; corposSize = corpos.size(); // Tem que ser declarado
	 * antes para nao acontecer loop
	 * 
	 * //Soma Chance Cor do Cabelo for(contPesquisa = 0; contPesquisa <
	 * corposPorCorCabelo.size(); contPesquisa++) {
	 * 
	 * //Contains por c�digo pois o parametro chance sempre muda, o que torna
	 * a funcao contains invi�vel. for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorCorCabelo.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) contains = true; }
	 * 
	 * //Se o corpo nao existe no array principal realizar soma de chance e
	 * adicionar no array principal if(contains != true) { if(
	 * corposPorCorCabelo.get(contPesquisa).getChance() == null) {
	 * corposPorCorCabelo.get(contPesquisa).setChance(CHANCE_COR_CABELO); }else
	 * { valorAux = corposPorCorCabelo.get(contPesquisa).getChance() +
	 * CHANCE_COR_CABELO;
	 * corposPorCorCabelo.get(contPesquisa).setChance(valorAux); valorAux = 0.0;
	 * } corpos.add(corposPorCorCabelo.get(contPesquisa));
	 * 
	 * //Se corpo existe no array principal somar chance correspondente ao valor
	 * atual de chance. }else { for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorCorCabelo.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) { indexAux = contAux; break; } }
	 * valorAux = corpos.get(contAux).getChance() + CHANCE_COR_CABELO;
	 * corpos.get(indexAux).setChance(valorAux);
	 * 
	 * //Reset de auxiliares valorAux = 0.0; indexAux = 0; contains = false; } }
	 * 
	 * //Soma Chance Altura if(corposPorAltura != null) {
	 * 
	 * //Zerando Variaveis auxiliares indexAux = 0; valorAux = 0.0; contains =
	 * false; contAux = 0; corposSize = corpos.size(); // Tem que ser declarado
	 * antes para nao acontecer loop
	 * 
	 * for(contPesquisa = 0; contPesquisa < corposPorAltura.size();
	 * contPesquisa++) {
	 * 
	 * //Contains por c�digo pois o parametro chance sempre muda, o que torna
	 * a funcao contains invi�vel. for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorAltura.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) contains = true; }
	 * 
	 * //Se o corpo nao existe no array principal realizar soma de chance e
	 * adicionar no array principal if(contains != true) { if(
	 * corposPorAltura.get(contPesquisa).getChance() == null) {
	 * corposPorAltura.get(contPesquisa).setChance(CHANCE_ALTURA); }else {
	 * valorAux = corposPorAltura.get(contPesquisa).getChance() + CHANCE_ALTURA;
	 * corposPorAltura.get(contPesquisa).setChance(valorAux); valorAux = 0.0; }
	 * corpos.add(corposPorAltura.get(contPesquisa));
	 * 
	 * //Se corpo existe no array principal somar chance correspondente ao valor
	 * atual de chance. }else { for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorAltura.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) { indexAux = contAux; break; } }
	 * valorAux = corpos.get(contAux).getChance() + CHANCE_ALTURA;
	 * corpos.get(indexAux).setChance(valorAux);
	 * 
	 * //Reset de auxiliares valorAux = 0.0; indexAux = 0; contains = false; } }
	 * 
	 * }
	 * 
	 * //Soma Chance Idade if(corposPorIdade != null) {
	 * 
	 * //Zerando Variaveis auxiliares indexAux = 0; valorAux = 0.0; contains =
	 * false; contAux = 0; corposSize = corpos.size(); // Tem que ser declarado
	 * antes para nao acontecer loop
	 * 
	 * for(contPesquisa = 0; contPesquisa < corposPorIdade.size();
	 * contPesquisa++) {
	 * 
	 * //Contains por c�digo pois o parametro chance sempre muda, o que torna
	 * a funcao contains invi�vel. for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorIdade.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) contains = true; }
	 * 
	 * //Se o corpo nao existe no array principal realizar soma de chance e
	 * adicionar no array principal if(contains != true) { if(
	 * corposPorIdade.get(contPesquisa).getChance() == null) {
	 * corposPorIdade.get(contPesquisa).setChance(CHANCE_IDADE); }else {
	 * valorAux = corposPorIdade.get(contPesquisa).getChance() + CHANCE_IDADE;
	 * corposPorIdade.get(contPesquisa).setChance(valorAux); valorAux = 0.0; }
	 * corpos.add(corposPorIdade.get(contPesquisa));
	 * 
	 * //Se corpo existe no array principal somar chance correspondente ao valor
	 * atual de chance. }else { for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorIdade.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) { indexAux = contAux; break; } }
	 * valorAux = corpos.get(contAux).getChance() + CHANCE_IDADE;
	 * corpos.get(indexAux).setChance(valorAux);
	 * 
	 * //Reset de auxiliares valorAux = 0.0; indexAux = 0; contains = false; } }
	 * 
	 * }
	 * 
	 * //Soma Chance Peso if(corposPorPeso != null) {
	 * 
	 * //Zerando Variaveis auxiliares indexAux = 0; valorAux = 0.0; contains =
	 * false; contAux = 0; corposSize = corpos.size(); // Tem que ser declarado
	 * antes para nao acontecer loop
	 * 
	 * for(contPesquisa = 0; contPesquisa < corposPorPeso.size();
	 * contPesquisa++) {
	 * 
	 * //Contains por c�digo pois o parametro chance sempre muda, o que torna
	 * a funcao contains invi�vel. for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorPeso.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) contains = true; }
	 * 
	 * //Se o corpo nao existe no array principal realizar soma de chance e
	 * adicionar no array principal if(contains != true) { if(
	 * corposPorPeso.get(contPesquisa).getChance() == null) {
	 * corposPorPeso.get(contPesquisa).setChance(CHANCE_PESO); }else { valorAux
	 * = corposPorPeso.get(contPesquisa).getChance() + CHANCE_PESO;
	 * corposPorPeso.get(contPesquisa).setChance(valorAux); valorAux = 0.0; }
	 * corpos.add(corposPorPeso.get(contPesquisa));
	 * 
	 * //Se corpo existe no array principal somar chance correspondente ao valor
	 * atual de chance. }else { for( contAux = 0; contAux < corposSize;
	 * contAux++) { if(corposPorPeso.get(contPesquisa).getCodigoCorpo() ==
	 * corpos.get(contAux).getCodigoCorpo()) { indexAux = contAux; break; } }
	 * valorAux = corpos.get(contAux).getChance() + CHANCE_PESO;
	 * corpos.get(indexAux).setChance(valorAux);
	 * 
	 * //Reset de auxiliares valorAux = 0.0; indexAux = 0; contains = false; } }
	 * 
	 * }
	 * 
	 * //Soma Chance Tatuagem
	 * 
	 * //Zerando Variaveis auxiliares corposSize = corpos.size();
	 * 
	 * //Imprime chances dos corpos no console para apoio ao DEV/TESTE E Remocao
	 * CHANCES 100% for(contAux = 0; contAux < corpos.size(); contAux++) {
	 * if(corpos.get(contAux).getChance() >= 100)
	 * corpos.get(contAux).setChance(99.00); System.out.println("CORPO " +
	 * contAux + " ID " + corpos.get(contAux).getCodigoCorpo() + " CHANCE: " +
	 * corpos.get(contAux).getChance()); }
	 * 
	 * if(corpos.isEmpty()) { FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "ERRO - Nao foram encontrados corpos.",
	 * "ERRO - Nao foram encontrados corpos.")); return ""; } FacesContext
	 * context = FacesContext.getCurrentInstance(); HttpServletRequest myRequest
	 * = (HttpServletRequest) context.getExternalContext().getRequest();
	 * HttpSession session = myRequest.getSession();
	 * session.setAttribute("arrayCorpos", corpos);
	 * 
	 * }catch (Exception e) { FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "ERRO - Erro na busca de corpos.", "ERRO - Erro na busca de corpos."));
	 * return ""; }
	 * 
	 * return "listaCorpos.xhtml?faces-redirect=true";
	 * 
	 * }
	 */

	/*
	 * public boolean salvarImagem(String salvarEm) { try { if(arq1 != null) {
	 * arq1.write(salvarEm + IMAGEM1); } if(arq2 != null) { arq2.write(salvarEm
	 * + IMAGEM2); } if(arq3 != null) { arq3.write(salvarEm + IMAGEM3); }
	 * if(arq4 != null) { arq4.write(salvarEm + IMAGEM4); } if(arq5 != null) {
	 * arq5.write(salvarEm + IMAGEM5); } if(arq6 != null) { arq6.write(salvarEm
	 * + IMAGEM6); } if(arq7 != null) { arq7.write(salvarEm + IMAGEM7); }
	 * if(arq8 != null) { arq8.write(salvarEm + IMAGEM8); } if(arq9 != null) {
	 * arq9.write(salvarEm + IMAGEM9); } } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return true; }
	 * 
	 * 
	 */
	// Getters and Setters

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

}
