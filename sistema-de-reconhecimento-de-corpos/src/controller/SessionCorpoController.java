package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import dao.CorpoDAO;
import dao.UsuarioDAO;
import entities.Cicatriz;
import entities.CicatrizAuxiliar;
import entities.Corpo;
import entities.LogEntity;
import entities.Tatuagem;
import entities.TatuagemAuxiliar;
import entities.Usuario;
import enums.GenericEnum;
import service.JPAUtil;

@ManagedBean
@SessionScoped
@MultipartConfig
public class SessionCorpoController extends GenericController {

	public static final String IMAGEM1 = "imagem1.jpg";
	public static final String IMAGEM2 = "imagem2.jpg";
	public static final String IMAGEM3 = "imagem3.jpg";
	public static final String IMAGEM4 = "imagem4.jpg";
	public static final String IMAGEM5 = "imagem5.jpg";
	public static final String IMAGEM6 = "imagem6.jpg";
	public static final String IMAGEM7 = "imagem7.jpg";
	public static final String IMAGEM8 = "imagem8.jpg";
	public static final String IMAGEM9 = "imagem9.jpg";
	private ArrayList<Corpo> corpos;
	private boolean diretorioListado = false;
	private Corpo corpo;
	private LogEntity logEntity;
	private LogEntity logConfirma;
	private LogEntity logCancela;

	public LogEntity getLogEntity() {
		return logEntity;
	}
	public void preparaConfirmaIdentificacao(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String login = (String) myRequest.getParameter("log");
		LogEntity log = this.getCorpoDAO().buscarByLogId(Integer.parseInt(login));
		this.logConfirma = log;
	}
	public void preparaCancelaIdentificacao(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String login = (String) myRequest.getParameter("log");
		LogEntity log = this.getCorpoDAO().buscarByLogId(Integer.parseInt(login));
		this.logCancela = log;
	}
	public void setLogEntity(LogEntity logEntity) {
		this.logEntity = logEntity;
	}
	public void cancelarIdentificacao(){
		try {
		logCancela.setConfirmado(0);
		logCancela.setIdentificado(0);
		Corpo corpo = this.getCorpoDAO().buscarById(logCancela.getCorpo().getCodigoCorpo());
		corpo.setIdentificado(0);
	
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.merge(logCancela);
		manager.merge(corpo);
		manager.getTransaction().commit();
		manager.close();
		}catch(Exception e ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO - Nao foi possivel cancelar identificação deste corpo.", "ERRO - Nao foi possivel cancelar identificação deste corpo"));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"SUCESSO - Identificação cancelada com sucesso.", "SUCESSO - Identificação cancelada com sucesso!"));

	}
	public void confirmarIdentificacao(){
		try {
			logConfirma.setConfirmado(1);
			logConfirma.setIdentificado(1);
			EntityManager manager = new JPAUtil().getEntityManager();
			Corpo corpo = this.getCorpoDAO().buscarById(logCancela.getCorpo().getCodigoCorpo());
			corpo.setIdentificado(0);
			manager.getTransaction().begin();
			manager.merge(logConfirma);
			manager.getTransaction().commit();
			manager.close();
		}catch(Exception e ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO - Nao foi possivel confirmar identificação deste corpo.", "ERRO - Nao foi possivel confirmar identificação deste corpo"));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"SUCESSO - Identificação confirmada com sucesso.", "SUCESSO - Identificação confirmada com sucesso!"));

	}

	private TatuagemAuxiliar tattooAux;
	private CicatrizAuxiliar cicatrizAux;
	private CicatrizAuxiliar cicatrizAuxB;
	private CorpoDAO corpoDAO;
	private UsuarioDAO usuarioDAO;
	private String dataChegada;
	private String horarioChegada;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	private Part arq1;
	private Part arq2;
	private Part arq3;
	private Part arq4;
	private Part arq5;
	private Part arq6;
	private Part arq7;
	private Part arq8;
	private Part arq9;
	String caminho;
	public ArrayList<String> diretorio;

	RequestCorpoController rcc = new RequestCorpoController();

	// Objetos Auxiliares
	private Tatuagem tatuagem;
	private Cicatriz cicatriz;
	private Corpo corpoAux;
	private TatuagemAuxiliar tatuagemAuxB;
	private int paginacao;
	private int paginacao2;
	private TatuagemAuxiliar tatuagemAuxA;
	private Tatuagem tatAux;
	private String parametroSearch;
	private List<Corpo> corposHist;
	private List<LogEntity> logHist;
	private String parametroVal;
	private int sizePaginacao;
	private ArrayList<String> paginacaoAux;
	private int isBusca = 0;
	private int sizePaginacao2;

	// Construtor

	public SessionCorpoController() {
		this.usuarioDAO = new UsuarioDAO();
		this.corpoAux = new Corpo();
		this.corpoDAO = new CorpoDAO();
		this.corpo = new Corpo();
		this.tatuagem = new Tatuagem();
		this.cicatriz = new Cicatriz();
		this.tatAux = new Tatuagem();
		this.tatuagemAuxA = new TatuagemAuxiliar();
		this.tatuagemAuxB = new TatuagemAuxiliar();
		this.cicatrizAuxB = new CicatrizAuxiliar();
	}

	// Metodos
	public void confirmaIdentificacao(int codigoCorpo) {
		try {
			verificaLatitudeLongitude();
			LogEntity log = this.getCorpoDAO().buscarByLogId(codigoCorpo);
			log.setConfirmado(1);
			log.setIdentificado(1);
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
			manager.merge(log);
			manager.getTransaction().commit();
			manager.close();
		}catch(Exception e ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO - Nao foi possivel confirmar identificação deste corpo.", "ERRO - Nao foi possivel confirmar identificação deste corpo"));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"SUCESSO - Identificação confirmada com sucesso.", "SUCESSO - Identificação confirmada com sucesso!"));

	}
	public void confirmaIdentificacao() {
		try{
			verificaLatitudeLongitude();
			this.logEntity.setConfirmado(1);
			this.getLogEntity().setIdentificado(1);
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
			manager.merge(this.logEntity);
			manager.getTransaction().commit();
			manager.close();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO - Nao foi possivel confirmar identificação deste corpo.", "ERRO - Nao foi possivel confirmar identificação deste corpo"));
		}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"SUCESSO - Identificação confirmada com sucesso.", "SUCESSO - Identificação confirmada com sucesso!"));
	}

	public void cancelaIdentificacao(int codigoCorpo) {
		LogEntity log = this.getCorpoDAO().buscarByLogId(codigoCorpo);
		log.setConfirmado(0);
		log.setIdentificado(0);
		Corpo corpo = this.getCorpoDAO().buscarById(log.getCorpo().getCodigoCorpo());
		corpo.setIdentificado(0);
	
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.merge(log);
		manager.merge(corpo);
		manager.getTransaction().commit();
		manager.close();
	}

	public void buscarByList() {
		int cont = 1;
		diretorio = new ArrayList<String>();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		int i = 0;
		Collections.sort(corpos, new Comparator<Corpo>() {
			@Override
			public int compare(Corpo corpo, Corpo corpo1) {

				return corpo1.getChance().compareTo(corpo.getChance());
			}
		});
		for (Corpo corpo : corpos) {

			String path = myRequest.getServletContext().getRealPath("pasta_corpo" + corpo.getCodigoCorpo());
			File file = new File(path);
			File[] arq = file.listFiles();
			if (arq != null && arq.length > 0) {

				File nome = arq[0];

				System.out.println(nome.getName());
				if (arq[0].getPath().equalsIgnoreCase(path + "\\imagem2.jpg")) {
					diretorio.add(i,
							"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg");
				} else {
					diretorio.add(i, "pasta_corpo" + corpo.getCodigoCorpo() + "/imagem1.jpg");
				}
			} else {
				diretorio.add(i,
						"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg");
			}
			i++;
		}

	}

	public String getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}

	public String getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(String horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public RequestCorpoController getRcc() {
		return rcc;
	}

	public void setRcc(RequestCorpoController rcc) {
		this.rcc = rcc;
	}

	public int getPaginacao2() {
		return paginacao2;
	}

	public void setPaginacao2(int paginacao2) {
		this.paginacao2 = paginacao2;
	}

	public List<LogEntity> getLogHist() {
		return logHist;
	}

	public void setLogHist(List<LogEntity> logHist) {
		this.logHist = logHist;
	}

	public int getIsBusca() {
		return isBusca;
	}

	public void setIsBusca(int isBusca) {
		this.isBusca = isBusca;
	}

	public static String getImagem1() {
		return IMAGEM1;
	}

	public static String getImagem2() {
		return IMAGEM2;
	}

	public static String getImagem3() {
		return IMAGEM3;
	}

	public static String getImagem4() {
		return IMAGEM4;
	}

	public static String getImagem5() {
		return IMAGEM5;
	}

	public static String getImagem6() {
		return IMAGEM6;
	}

	public static String getImagem7() {
		return IMAGEM7;
	}

	public static String getImagem8() {
		return IMAGEM8;
	}

	public static String getImagem9() {
		return IMAGEM9;
	}

	public String buscarById(int codigoCorpo) {

		try {
			int cont = 1;

			System.out.println(GenericEnum.dir_img);

			diretorio = new ArrayList<String>();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();

			String path = myRequest.getServletContext().getRealPath("pasta_corpo" + codigoCorpo);
			caminho = myRequest.getServletContext().getRealPath("");
			corpo = corpoDAO.buscarById(codigoCorpo);
			if((this.logEntity = corpoDAO.buscarLogPorCorpo(codigoCorpo)) == null){
				this.logEntity = new LogEntity();
				this.logEntity.setIdentificado(0);
			}
			
			System.out.println(corpo.getIdentificado());
			tatuagem = corpoDAO.buscarTatuagemById(codigoCorpo);
			cicatriz = corpoDAO.buscarCicatrizById(codigoCorpo);

			setCicatrizAndTatuagem(tatuagem, cicatriz);

			File file = new File(path);
			File[] arq = file.listFiles();
			if (arq != null) {
				for (int i = 0; i < arq.length; i++) {
					File nome = arq[i];
					System.out.println(nome.getName());
				}
			}

			boolean flag = false;
			int contadorArq = arq.length;
			for (int i = 0; i < 9; i++) {
				if (arq == null) {
					if (i == 0) {
						diretorio.add(i,
								"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg");
					} else {
						diretorio.add("-");
					}
				} else {
					if (diretorio.contains(
							"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg")
							&& !flag) {
						contadorArq += 1;
						flag = true;
					}

					if (cont <= contadorArq) {

						if (i == 0) {
							if (arq[i].getPath().equalsIgnoreCase(path + "\\imagem2.jpg")
									&& arq[0].getPath().equalsIgnoreCase(path + "\\imagem2.jpg")) {
								diretorio.add(i,
										"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg");

							} else {
								diretorio.add(i, "pasta_corpo" + codigoCorpo + "/imagem" + cont + ".jpg	");
							}
						} else {
							diretorio.add(i, "pasta_corpo" + codigoCorpo + "/imagem" + cont + ".jpg	");

						}

					} else {
						diretorio.add("-");
					}

				}
				cont++;
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO - Nao foi possivel detalhar este corpo.", "ERRO - Nao foi possivel detalhar este corpo."));
		}
		// diretorio = "pasta_corpo10/imagem1.jpg";
		return "detalheCorpo.xhtml?faces-redirect=true";
	}

	public void alterar() {

		try {

			Corpo corpoAux = this.corpoAux;
			/*
			 * Corpo comparaIdentificado =
			 * this.getCorpoDAO().buscarById(corpo.getCodigoCorpo());
			 * if(comparaIdentificado.getIdentificado() !=
			 * corpo.getIdentificado()){ FacesContext context =
			 * FacesContext.getCurrentInstance(); HttpServletRequest myRequest =
			 * (HttpServletRequest) context.getExternalContext().getRequest();
			 * HttpSession session = myRequest .getSession(); String user =
			 * (String) session.getAttribute("uname"); Usuario usuario =
			 * this.getUsuarioDAO().buscaPorNome(user); DateFormat dateFormat =
			 * new SimpleDateFormat("dd/MM/yyyy"); Date date = new Date();
			 * LogEntity log = new LogEntity(); log.setUsuario(usuario);
			 * log.setCorpo(corpo); log.setData(date);
			 * log.setIdentificado(corpo.getIdentificado()); }
			 */
			corpoAux.setIdentificado(corpo.getIdentificado());
			if (corpo.getLatitude().isEmpty())
				corpoAux.setLatitude(null);
			else
				corpoAux.setLatitude(corpo.getLatitude());
			if (corpo.getLongitude().isEmpty())
				corpoAux.setLongitude(null);
			else
				corpoAux.setLongitude(corpo.getLongitude());

			TatuagemAuxiliar tatuagemAuxB = this.tatuagemAuxB;
			CicatrizAuxiliar cicatrizAuxB = this.cicatrizAuxB;

			atualiza(corpoAux, tatuagemAuxB, cicatrizAuxB);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"SUCESSO - Corpo alterado com sucesso.", "SUCESSO - Corpo cadastrado com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"ERRO - Nao foi possivel alterar este corpo, revise os parametros informados.",
							"ERRO - Nao foi possivel alterar este corpo, revise os parametros informados."));
		}
	}

	public void gerarPDF() throws DocumentException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String path = myRequest.getServletContext().getRealPath("");
		Document document = new Document();

		verificaLatitudeLongitude();
		try {
			Font f = new Font(FontFamily.COURIER, 12, Font.BOLD);
			Font f2 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

			//String k = "<html><body> <h1>SRC - SISTEMA DE RECONHECIMENTO DE	CORPOS</h1></body></html>";
			File pdf = new File("C:\\pdf\\");

			if (!pdf.exists()) {
				pdf.mkdir();
			}
			PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\pdfcorpo.pdf"));
			document.open();

			StringBuilder sb = new StringBuilder();
			sb.append("<div>\n<p align=\"center\">");
			sb.append("<font size=\"5\">");
			sb.append(
					"<b>&nbsp;<img src=\"https://www.pcdf.df.gov.br/templates/pcdf-cidadao/images/logo-pcdf.png\"  height=\"150\" width=\"400\"/></b>");
			sb.append("</font>");
			sb.append("<font color=\"#32cd32\">&nbsp;</font>");
			sb.append("</p>\n</div>");
			PdfPTable table3 = new PdfPTable(1);
			PdfPCell cell = new PdfPCell();
			ElementList list = XMLWorkerHelper.parseToElementList(sb.toString(), null);
			for (Element element : list) {
			  cell.addElement(element);
			}
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			table3.addCell(cell);
			document.add(table3);

			HTMLWorker htmlWorker = new HTMLWorker(document);
			//htmlWorker.parse(new StringReader(k));
			Paragraph p = new Paragraph("SRC - SISTEMA DE RECONHECIMENTO DE CORPOS", f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(2);

			Paragraph p11 = new Paragraph("ID Corpo", f2);
			table.addCell(p11);
			if (getCorpo().getCodigoCorpo() != null) {
				table.addCell(getCorpo().getCodigoCorpo().toString());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p10 = new Paragraph("Sexo", f2);
			table.addCell(p10);
			if (getCorpo().getSexo() != null) {
				table.addCell(getCorpo().getSexo());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p2 = new Paragraph("Cor da Pele", f2);

			table.addCell(p2);
			if (getCorpo().getCorPele() != null) {
				table.addCell(getCorpo().getCorPele());
			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p3 = new Paragraph("Cor dos Olhos", f2);

			table.addCell(p3);
			if (getCorpo().getCorOlhos() != null) {
				table.addCell(getCorpo().getCorOlhos());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p4 = new Paragraph("Altura", f2);

			table.addCell(p4);
			if (getCorpo().getAltura() != null) {
				table.addCell(getCorpo().getAltura().toString());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p5 = new Paragraph("Idade", f2);

			table.addCell(p5);
			if (getCorpo().getFaixaEtaria() != null) {
				table.addCell(getCorpo().getFaixaEtaria().toString());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p6 = new Paragraph("Peso", f2);

			table.addCell(p6);
			if (getCorpo().getPeso() != null) {
				table.addCell(getCorpo().getPeso().toString());
			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p7 = new Paragraph("Data da Chegada", f2);
			table.addCell(p7);

			if (getCorpo().getDataChegada() != null) {
				table.addCell(getCorpo().getDataChegada().toString());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p8 = new Paragraph("Hora da Chegada", f2);

			table.addCell(p8);
			if (getCorpo().getHorarioChegada() != null) {
				table.addCell(getCorpo().getHorarioChegada().toString());
			} else {
				table.addCell("NAO CADASTRADO");
			}

			Paragraph p9 = new Paragraph("Observacoes", f2);
			table.addCell(p9);
			if (getCorpo().getDescricao() != null) {
				table.addCell(getCorpo().getDescricao());

			} else {
				table.addCell("NAO CADASTRADO");
			}

			document.add(table);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			int contImagens = 0;
			for (String dir : diretorio) {
				if (!dir.equalsIgnoreCase("-") && !dir.equalsIgnoreCase(
						"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg")) {
					contImagens++;
				}
			}
			if (contImagens % 2 == 0) {
				contImagens = 2;
			}
			if (contImagens % 3 == 0) {
				contImagens = 3;
			}
			if (contImagens == 5 || contImagens == 7) {
				contImagens = 1;
			}

			PdfPTable table2 = new PdfPTable(contImagens);
			Paragraph paragrafo = new Paragraph("Fotos do Corpo", f2);
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			PdfPCell header = new PdfPCell(paragrafo);
			header.setColspan(contImagens);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			header.setBorderColor(BaseColor.GRAY);
			table2.addCell(header);
			table2.getDefaultCell().setBorderColor(BaseColor.GRAY);
			if (diretorio.size() > 1) {
				for (String dir : diretorio) {

					if (!dir.equals("-")) {
						if (!dir.equalsIgnoreCase(
								"http://www.politize.com.br/wp-content/uploads/2016/08/imagem-sem-foto-de-perfil-do-facebook-1348864936180_956x5001.jpg")) {
							Image img = Image.getInstance(String.format("http://localhost:8080/jsf/" + dir));
							img.scaleToFit(200, 200);
							if (contImagens == 1) {
								table2.setTotalWidth(200);
								table2.setLockedWidth(true);
							}

							table2.addCell(img);
						}

					}
				}
				document.add(table2);
			}
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		// Runtime.getRuntime().exec (new String[]{"cmd.exe", "/c",
		// "start","C:\\pdf\\pdfyes.pdf"});
		java.awt.Desktop.getDesktop().open(new File("C:\\pdf\\pdfcorpo.pdf"));

		document.close();

	}

	public void atualiza(Corpo corpoB, TatuagemAuxiliar tattoo, CicatrizAuxiliar cicatrizAuxB) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		tatuagem.setRosto(tattoo.isRosto() ? 1 : 0);
		tatuagem.setBraco(tattoo.isBraco() ? 1 : 0);
		tatuagem.setCostas(tattoo.isCostas() ? 1 : 0);
		tatuagem.setPerna(tattoo.isPerna() ? 1 : 0);
		tatuagem.setPeito(tattoo.isPeito() ? 1 : 0);
		tatuagem.setPe(tattoo.isPe() ? 1 : 0);

		cicatriz.setRosto(cicatrizAuxB.isRosto() ? 1 : 0);
		cicatriz.setBraco(cicatrizAuxB.isBraco() ? 1 : 0);
		cicatriz.setCostas(cicatrizAuxB.isCostas() ? 1 : 0);
		cicatriz.setPerna(cicatrizAuxB.isPerna() ? 1 : 0);
		cicatriz.setPeito(cicatrizAuxB.isPeito() ? 1 : 0);
		cicatriz.setPe(cicatrizAuxB.isPe() ? 1 : 0);
		corpoB.setCodigoCorpo(corpo.getCodigoCorpo());
		manager.merge(corpoB);
		manager.merge(tatuagem);
		manager.merge(cicatriz);
		manager.getTransaction().commit();

		manager.close();

		buscarById(corpoB.getCodigoCorpo());
	}

	/*
	 * public void atualiza(Corpo corpo) {
	 * 
	 * EntityManager manager = new JPAUtil().getEntityManager();
	 * manager.getTransaction().begin();
	 * 
	 * corpo.setIdentificado(0); // ARRUMAR ISSO QUE TA MEGA ERRADO
	 * 
	 * manager.merge(corpo); manager.getTransaction().commit(); manager.close();
	 * 
	 * buscarById(corpo.getCodigoCorpo());
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<Corpo> getCorposPesquisados() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = myRequest.getSession();
		corpos = (ArrayList<Corpo>) session.getAttribute("arrayCorpos");
		for (Corpo corpo : corpos) {
			System.out.println(corpo.getAltura());
		}
		this.buscarByList();
		diretorioListado = true;
		return corpos;
	}

	public void identificar() {
		verificaLatitudeLongitude();
		LogEntity log;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = myRequest.getSession();
		String user = (String) session.getAttribute("uname");
		Usuario usuario = this.getUsuarioDAO().buscaPorNome(user);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		if((log = this.getCorpoDAO().buscarLogPorCorpo(this.corpo.getCodigoCorpo())) == null){
			 log = new LogEntity();
		}
		log.setUsuario(usuario);
		log.setData(date);
		log.setIdentificado(1);
		log.setConfirmado(0);

		try {
			gerarPdfIdentificado();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		corpo.setIdentificado(1);
		log.setCorpo(corpo);
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.merge(corpo);
		manager.merge(log);
		manager.getTransaction().commit();
		manager.close();
		buscarById(corpo.getCodigoCorpo());

	}

	private void gerarPdfIdentificado() throws DocumentException, IOException {
		Document document = new Document();

		try {

			Font f = new Font(FontFamily.COURIER, 12, Font.BOLD);
			Font f2 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		    String k = "<html><body> <h1>SRC - SISTEMA DE RECONHECIMENTO DE CORPOS</h1></body></html>";
			File pdf = new File("C:\\pdf\\");

			if (!pdf.exists()) {
				pdf.mkdir();
			}
			PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\pdfCorpoIdentificado.pdf"));
			document.open();

			StringBuilder sb = new StringBuilder();
			sb.append("<div>\n<p align=\"center\">");
			sb.append("<font size=\"5\">");
			sb.append(
					"<b>&nbsp;<img src=\"https://www.pcdf.df.gov.br/templates/pcdf-cidadao/images/logo-pcdf.png\"  height=\"150\" width=\"400\"/></b>");
			sb.append("</font>");
			sb.append("<font color=\"#32cd32\">&nbsp;</font>");
			sb.append("</p>\n</div>");
			PdfPTable table3 = new PdfPTable(1);
			PdfPCell cell = new PdfPCell();
			
			ElementList list = XMLWorkerHelper.parseToElementList(sb.toString(), null); for
			(Element element : list) { cell.addElement(element); }
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			table3.addCell(cell);
			document.add(table3);

			Paragraph p = new Paragraph("SRC - SISTEMA DE RECONHECIMENTO DE CORPOS", f);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(1);
			PdfPCell cell2 = new PdfPCell();

			Paragraph p11 = new Paragraph("O corpo de identificação número " + getCorpo().getCodigoCorpo().toString()
					+ " foi possivelmente identificado!", f2);

			Paragraph p12 = new Paragraph(
					"Dirija-se ao IML de Brasília e informe número do corpo: " + getCorpo().getCodigoCorpo().toString(),
					f2);

			cell2.addElement(p11);
			cell2.addElement(p12);
			cell2.setBorder(0);
			table.addCell(cell2);
			document.add(table);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		java.awt.Desktop.getDesktop().open(new File("C:\\pdf\\pdfCorpoIdentificado.pdf"));

		document.close();

	}

	private void verificaLatitudeLongitude() {
		if (corpo.getLatitude().isEmpty())
			corpo.setLatitude(null);
		if (corpo.getLongitude().isEmpty())
			corpo.setLongitude(null);
	}

	private void setCicatrizAndTatuagem(Tatuagem tatuagem, Cicatriz cicatriz) {
		tattooAux = new TatuagemAuxiliar();
		cicatrizAux = new CicatrizAuxiliar();

		if (tatuagem.getBraco() == 1) {
			tattooAux.setBraco(true);
		}
		if (tatuagem.getCostas() == 1) {
			tattooAux.setCostas(true);
		}
		if (tatuagem.getPe() == 1) {
			tattooAux.setPe(true);
		}
		if (tatuagem.getPeito() == 1) {
			tattooAux.setPeito(true);
		}
		if (tatuagem.getPerna() == 1) {
			tattooAux.setPerna(true);
		}
		if (tatuagem.getRosto() == 1) {
			tattooAux.setRosto(true);
		}
		// ================================================//

		if (cicatriz.getBraco() == 1) {
			cicatrizAux.setBraco(true);
		}
		if (cicatriz.getCostas() == 1) {
			cicatrizAux.setCostas(true);
		}
		if (cicatriz.getPe() == 1) {
			cicatrizAux.setPe(true);
		}
		if (cicatriz.getPeito() == 1) {
			cicatrizAux.setPeito(true);
		}
		if (cicatriz.getPerna() == 1) {
			cicatrizAux.setPerna(true);
		}
		if (cicatriz.getRosto() == 1) {
			cicatrizAux.setRosto(true);
		}

	}

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

	public Tatuagem getTatuagem() {
		return tatuagem;
	}

	public void setTatuagem(Tatuagem tatuagem) {
		this.tatuagem = tatuagem;
	}

	public Cicatriz getCicatriz() {
		return cicatriz;
	}

	public void setCicatriz(Cicatriz cicatriz) {
		this.cicatriz = cicatriz;
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

	public TatuagemAuxiliar getTattooAux() {
		return tattooAux;
	}

	public void setTattooAux(TatuagemAuxiliar tattooAux) {
		this.tattooAux = tattooAux;
	}

	public CicatrizAuxiliar getCicatrizAux() {
		return cicatrizAux;
	}

	public void setCicatrizAux(CicatrizAuxiliar cicatrizAux) {
		this.cicatrizAux = cicatrizAux;
	}

	public boolean isDiretorioListado() {
		return diretorioListado;
	}

	public void setDiretorioListado(boolean diretorioListado) {
		this.diretorioListado = diretorioListado;
	}

	public ArrayList<Corpo> getCorpos() {
		return corpos;
	}

	public void setCorpos(ArrayList<Corpo> corpos) {
		this.corpos = corpos;
	}

	public Corpo getCorpoAux() {
		return corpoAux;
	}

	public void setCorpoAux(Corpo corpoAux) {
		this.corpoAux = corpoAux;
	}

	public Tatuagem getTatAux() {
		return tatAux;
	}

	public void setTatAux(Tatuagem tatAux) {
		this.tatAux = tatAux;
	}

	public TatuagemAuxiliar getTatA() {
		return tatuagemAuxA;
	}

	public void setTatA(TatuagemAuxiliar tatA) {
		this.tatuagemAuxA = tatA;
	}

	public TatuagemAuxiliar getTatuagemAuxB() {
		return tatuagemAuxB;
	}

	public void setTatuagemAuxB(TatuagemAuxiliar tatuagemAuxB) {
		this.tatuagemAuxB = tatuagemAuxB;
	}

	public CicatrizAuxiliar getCicatrizAuxB() {
		return cicatrizAuxB;
	}

	public void setCicatrizAuxB(CicatrizAuxiliar cicatrizAuxB) {
		this.cicatrizAuxB = cicatrizAuxB;
	}

	public TatuagemAuxiliar getTatuagemAuxA() {
		return tatuagemAuxA;
	}

	public void setTatuagemAuxA(TatuagemAuxiliar tatuagemAuxA) {
		this.tatuagemAuxA = tatuagemAuxA;
	}

	public String getParametroSearch() {
		return parametroSearch;
	}

	public void setParametroSearch(String parametroSearch) {
		this.parametroSearch = parametroSearch;
	}

	public List<LogEntity> getCorposIdentificados() {

		if (isBusca == 1 && !this.getParametroVal().equals("")) {
			return this.logHist;
		}
		this.logHist = this.getCorpoDAO().buscarByIdentificados();
		int limite;
		if (logHist.size() < (10 + paginacao2 * 10)) {
			limite = logHist.size();
		} else {
			limite = 10 + paginacao2 * 10;
		}
		if (this.logHist.size() > 10) {

			ArrayList<LogEntity> logAux = new ArrayList<LogEntity>();
			for (int i = (0 + paginacao2 * 10); i < limite; i++) {

				logAux.add(this.logHist.get(i));

			}
			return logAux;
		}
		isBusca = 0;
		return this.logHist;
	}

	public List<Corpo> getCorposHist() {

		if (isBusca == 1 && !this.getParametroVal().equals("")) {
			return this.corposHist;
		}
		this.corposHist = this.getCorpoDAO().buscarTodos();
		int limite;
		if (corposHist.size() < (10 + paginacao * 10)) {
			limite = corposHist.size();
		} else {
			limite = 10 + paginacao * 10;
		}
		if (this.corposHist.size() > 10) {

			ArrayList<Corpo> corposAux = new ArrayList<Corpo>();
			for (int i = (0 + paginacao * 10); i < limite; i++) {

				corposAux.add(this.corposHist.get(i));

			}
			return corposAux;
		}
		isBusca = 0;
		return this.corposHist;
	}

	public void setCorposHist(List<Corpo> corposHist) {
		this.corposHist = corposHist;
	}

	public String getParametroVal() {
		return parametroVal;
	}

	public void setParametroVal(String parametroVal) {
		this.parametroVal = parametroVal;
	}

	public int getSizePaginacao2() {
		int resto = this.logHist.size() % 10 == 0 ? 0 : 1;
		return this.logHist.size() / 10 + resto;
	}

	public void setSizePaginacao2(int sizePaginacao) {
		this.sizePaginacao2 = sizePaginacao;
	}

	public ArrayList<String> getPaginacaoAux2() {
		paginacaoAux = new ArrayList<String>();
		for (int i = 0; i < this.getSizePaginacao2(); i++) {
			paginacaoAux.add("");
		}
		return paginacaoAux;
	}

	public int getSizePaginacao() {
		int resto = this.corposHist.size() % 10 == 0 ? 0 : 1;
		return this.corposHist.size() / 10 + resto;
	}

	public void setSizePaginacao(int sizePaginacao) {
		this.sizePaginacao = sizePaginacao;
	}

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

	public void atualizaPaginacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		int paginacao = Integer.parseInt(myRequest.getParameter("paginacao"));
		this.setPaginacao(paginacao - 1);

	}

	public void atualizaPaginacao2() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		int paginacao = Integer.parseInt(myRequest.getParameter("paginacao"));
		this.setPaginacao2(paginacao - 1);

	}

	public void atualizaLista() {
		if (this.getParametroVal() == "") {
			this.corposHist = this.getCorpoDAO().buscarTodos();
		} else {
			this.corposHist = this.getCorpoDAO().buscaPorParam(this.getParametroSearch(), this.getParametroVal());
		}
		isBusca = 1;
	}

	public void atualizaLista2() {
		if (this.getParametroVal() == "") {
			this.logHist = this.getCorpoDAO().buscarTodosLog();
		} else {
			this.logHist = this.getCorpoDAO().buscaPorParamLog(this.getParametroSearch(), this.getParametroVal());
		}
		isBusca = 1;
	}

	public int getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(int paginacao) {
		this.paginacao = paginacao;
	}

}