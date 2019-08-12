package Util;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import controller.GenericController;
public class EnviarEmail extends GenericController{
	
	
	public String enviarEmail(String email) {
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        String senha="";


        for (int x=0; x<10; x++){
            int j = (int) (Math.random()*carct.length);
            senha += carct[j];
        }
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication("recuperarsenhasrc@gmail.com", "123imldf");
                         }
                    });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("recuperarsenhasrc@gmail.com")); //Remetente

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(email);  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("Recuperar Senha SRC");//Assunto
              message.setText("Sua nova senha de acesso ao SRC é: \n" + senha);
              /**Método para enviar a mensagem criada*/
              Transport.send(message);

              System.out.println("Feito!!!");
              
              FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO - Nova senha enviada com sucesso.", "SUCESSO - Nova senha enviada com sucesso."));

         } catch (MessagingException e) {
        	 FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO - Nao foi possivel enviar sua nova senha, revise o e-mail informado.", 
								 "ERRO - Nao foi possivel enviar sua nova senha, revise o e-mail informado."));
        } 
		
		return senha; 
	}
}
