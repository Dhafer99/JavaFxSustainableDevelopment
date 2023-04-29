/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Services.ServiceUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.*;
/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class mailingController implements Initializable {
static String randomString ;
static String emailsent ;


    public static String getRandomString() {
        return randomString;
    }

    public static void setRandomString(String randomString) {
        mailingController.randomString = randomString;
    }

    
    @FXML
    private Button send;
    @FXML
    private TextField email;
    @FXML
    private Button retour;
    @FXML
    private TextField Msg;
    @FXML
    private TextField subject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    

    @FXML
    private void Notifier(ActionEvent event) throws IOException, SQLException {
        URL url_name = new URL("http://checkip.amazonaws.com");
            BufferedReader br = new BufferedReader(new InputStreamReader(url_name.openStream()));
            String ip_address = br.readLine();
            
            setRandomString(generateRandomString(6));
        // Set the HTML content of the message
String htmlContent = "<center>\n" +
"    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0;padding:0;width:100%;height:100%\" bgcolor=\"#ffffff\" class=\"m_-4249080613097279967gwfw\">\n" +
"        <tbody><tr>\n" +
"            <td style=\"margin:0;padding:0;width:100%;height:100%\" align=\"center\" valign=\"top\">\n" +
"                <table width=\"775\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"m_-4249080613097279967m-shell\">\n" +
"                    <tbody><tr>\n" +
"                        <td class=\"m_-4249080613097279967td\" style=\"width:775px;min-width:775px;font-size:0pt;line-height:0pt;padding:0;margin:0;font-weight:normal\">\n" +
"                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                \n" +
"                                <tbody><tr>\n" +
"                                    <td class=\"m_-4249080613097279967mpy-35 m_-4249080613097279967mpx-15\" bgcolor=\"#212429\" style=\"padding:80px; background: linear-gradient(to right, #02AABD, #00CDAC);\">\n" +
"                                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"\n" +
"                                            \n" +
"                                            <tbody><tr>\n" +
"                                                <td style=\"font-size:0pt;line-height:0pt;text-align:left;padding-bottom:45px\">\n" +
"                                                    <a href=\"\" target=\"_blank\" data-saferedirecturl=\"\">\n" +
"                                                        <img src=\"https://i.postimg.cc/65VqYgB5/logoequipesansgenereux-removebg-preview.png\" width=\"250\" height=\"100\" border=\"0\" alt=\"Steam\" class=\"CToWUd\" data-bit=\"iit\">\n" +
"                                                    </a>\n" +
"\n" +
"                                                </td>\n" +
"                                            </tr>\n" +
"                                            \n" +
"\n" +
"                                            \n" +
"                                            <tr>\n" +
"                                                <td>\n" +
"\n" +
"			<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"				<tbody><tr>\n" +
"					<td style=\"font-size:36px;line-height:42px;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;padding-bottom:30px;color:#bfbfbf;font-weight:bold\">Cher "+ServiceUser.getInstance().getUserName(email.getText())+",</td>\n" +
"				</tr>\n" +
"			</tbody></table>\n" +
"						<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"				<tbody><tr>\n" +
"					<td style=\"font-size:18px;line-height:25px;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;color:#dbdbdb;padding-bottom:30px\">c'est ton <span class=\"il\">Genereux</span>  code que vous avez besoin pour recuperer ton compte "+ServiceUser.getInstance().getUserName(email.getText())+":</td>\n" +
"				</tr>\n" +
"			</tbody></table>\n" +
"						<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"				<tbody><tr>\n" +
"					<td class=\"m_-4249080613097279967mpb-50\" style=\"padding-bottom:70px\">\n" +
"						<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#17191c\">\n" +
"							<tbody><tr>\n" +
"								<td style=\"padding-top:30px;padding-bottom:30px;padding-left:56px;padding-right:56px\">\n" +
"									<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"																				<tbody><tr>\n" +
"											<td style=\"font-size:48px;line-height:52px;font-family:Arial,sans-serif,'Motiva Sans';color:#3a9aed;font-weight:bold;text-align:center\">\n" +
"												"+getRandomString()+"										</td>\n" +
"										</tr>\n" +
"									</tbody></table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody></table>\n" +
"					</td>\n" +
"				</tr>\n" +
"			</tbody></table>\n" +
"						<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"				<tbody><tr>\n" +
"					<td style=\"font-size:18px;line-height:25px;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;color:#dbdbdb;padding-bottom:30px\">Cette email est envoyé par ce que vous avez envoyé une demande de récupération de mot de passe <a style=\"color:#c6d4df\" href=\"https://steamcommunity.com/actions/ReportSuspiciousLogin?stoken=2b1a03b8d8fcc2afb0a8193e12504a05da51371cc638122c27992b6180885d096371e8458b29a2d104b8458e34367eda2b908531a70b463d0676f5f0862fd9cac859f2daae44e13843b136fa024010cb2af4c08e47505d7a48b1791d75f919dc\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://steamcommunity.com/actions/ReportSuspiciousLogin?stoken%3D2b1a03b8d8fcc2afb0a8193e12504a05da51371cc638122c27992b6180885d096371e8458b29a2d104b8458e34367eda2b908531a70b463d0676f5f0862fd9cac859f2daae44e13843b136fa024010cb2af4c08e47505d7a48b1791d75f919dc&amp;source=gmail&amp;ust=1682607522418000&amp;usg=AOvVaw3j1Txa_or64mrmxUQ4G8IP\">localisé a "+ip_address+" (TN).</a>  The login attempt included your correct account name and password.<br><br>\n" +
"Ton <span class=\"il\">Genereux</span>  code est requis pour completer le login  <span style=\"color:#ffffff;font-weight:bold\">Aucun ne peut acceder votre compte sans cette email.</span><br><br>\n" +
"<span style=\"color:#ffffff;font-weight:bold\">Si vous n'avez pas essayer de vous connecter</span> Veuillez changer votre  <span class=\"il\">Genereux </span> Mot de passe, et considerer changer votre email pour votre sécurité.</td>\n" +
"				</tr>\n" +
"			</tbody></table>\n" +
"			            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                <tbody><tr>\n" +
"                    <td style=\"font-size:18px;line-height:25px;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;color:#7abefa;padding-bottom:40px\"></td>\n" +
"                </tr>\n" +
"            </tbody></table>\n" +
"            \n" +
"                                                                                                \n" +
"                                                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                    <tbody><tr>\n" +
"                                                        <td style=\"padding-top:30px\">\n" +
"                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                                <tbody><tr>\n" +
"                                                                    <td width=\"3\" bgcolor=\"#3a9aed\" style=\"font-size:0pt;line-height:0pt;text-align:left\"></td>\n" +
"                                                                    <td width=\"37\" style=\"font-size:0pt;line-height:0pt;text-align:left\"></td>\n" +
"                                                                    <td>\n" +
"                                                                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                                            <tbody><tr>\n" +
"                                                                                                                                                                    <td style=\"font-size:16px;line-height:22px;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;padding-top:20px;padding-bottom:20px;color:#f1f1f1\">\n" +
"                                                                                        Cheers,<br>\n" +
"The <span class=\"il\">Scared to Compile</span> Team                                                                                    </td>\n" +
"                                                                                                                                                            </tr>\n" +
"                                                                        </tbody></table>\n" +
"                                                                    </td>\n" +
"                                                                </tr>\n" +
"                                                            </tbody></table>\n" +
"                                                        </td>\n" +
"                                                    </tr>\n" +
"                                                </tbody></table>\n" +
"                                                \n" +
"                                                \n" +
"                                                </td>\n" +
"                                            </tr>\n" +
"\n" +
"                                        </tbody></table>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                \n" +
"\n" +
"                                \n" +
"                                <tr>\n" +
"                                    <td class=\"m_-4249080613097279967mpy-40 m_-4249080613097279967mpx-15\" style=\"padding-top:60px;padding-bottom:60px;padding-left:90px;padding-right:90px\">\n" +
"                                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"\n" +
"                                                                                        <tbody><tr>\n" +
"                                                <td class=\"m_-4249080613097279967mpb-40\" style=\"font-size:18px;line-height:25px;color:#000001;font-family:Arial,sans-serif,'Motiva Sans';text-align:left;padding-bottom:60px\">\n" +
"			                                        Cette notification a été envoyée à l'adresse e-mail associée à votre  <span class=\"il\">compte</span>Genereux .                                                    <br><br>\n" +
"			                                        Cet e-mail a été généré automatiquement. Ne pas répondre s'il vous plait. Si vous avez besoin d'aide supplémentaire, veuillez visiter <span class=\"il\">Genereux</span> Support.                                                </td>\n" +
"                                            </tr>\n" +
"                                            \n" +
"                                                                                        \n" +
"                                                                                        <tr>\n" +
"                                           \n" +
"                                            </tr>\n" +
"                                            \n" +
"\n" +
"                                            \n" +
"                                                                                            <tr>\n" +
"                                                    <td style=\"padding-bottom:50px\">\n" +
"                                                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                            <tbody><tr>\n" +
"                                                                <th class=\"m_-4249080613097279967column\" width=\"270\" valign=\"top\" style=\"font-size:0pt;line-height:0pt;padding:0;margin:0;font-weight:normal;vertical-align:top\">\n" +
"                                                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                                        <tbody><tr>\n" +
"                                                                            <td class=\"m_-4249080613097279967mpt-0\" style=\"font-size:0pt;line-height:0pt;text-align:left\">\n" +
"                                                                                <a href=\"\" target=\"_blank\" data-saferedirecturl=\"\"><img src=\"https://i.postimg.cc/65VqYgB5/logoequipesansgenereux-removebg-preview.png\" width=\"351\" height=\"360\" border=\"0\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></a>\n" +
"                                                                            </td>\n" +
"                                                                        </tr>\n" +
"                                                                    </tbody></table>\n" +
"                                                                </th>\n" +
"                                                                <th class=\"m_-4249080613097279967column-top m_-4249080613097279967mpb-40\" width=\"15\" style=\"font-size:0pt;line-height:0pt;padding:0;margin:0;font-weight:normal;vertical-align:top\"></th>\n" +
"                                                                <th class=\"m_-4249080613097279967column\" style=\"font-size:0pt;line-height:0pt;padding:0;margin:0;font-weight:normal\">\n" +
"                                                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                                                                                                                                                    <tbody><tr>\n" +
"                                                                                <td style=\"font-size:12px;line-height:18px;color:#000001;font-family:Arial,sans-serif,'Motiva Sans';text-align:left\">\n" +
"                                                                                                                                                                            Pour télécharger <span class=\"il\">Genereux</span> desktop client et connaitre mieux <span class=\"il\">Genereux</span>, Veuillez Visiter <span class=\"il\">Notre Site web Genereux</span>.                                                                                        <br><br>\n" +
"                                                                                        <a href=\"\" style=\"text-decoration:underline;color:#000001\" target=\"_blank\" data-saferedirecturl=\"\">\n" +
"                                                                                            <span style=\"text-decoration:underline;color:#000001\"><strong>About <span class=\"il\">Genereux</span></strong>\n" +
"                                                                                            </span>\n" +
"                                                                                        </a>\n" +
"                                                                                                                                                                    </td>\n" +
"                                                                            </tr>\n" +
"                                                                                                                                            </tbody></table>\n" +
"                                                                </th>\n" +
"                                                            </tr>\n" +
"                                                        </tbody></table>\n" +
"                                                    </td>\n" +
"                                                </tr>\n" +
"\n" +
"                                            \n" +
"\n" +
"                                            \n" +
"                                            \n" +
"                                        </tbody></table>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                            \n" +
"                            </tbody></table>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </tbody></table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </tbody></table>\n" +
"</center>";

        // Recipient's email ID needs to be mentioned.
        String to = email.getText() ; 

        // Sender's email ID needs to be mentioned
        String from = "genereux.scaredtocompile@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        //properties.put("mail.smtp.port", "465");
        //properties.put("mail.smtp.ssl.enable", "true");
        //properties.put("mail.smtp.auth", "true");
        
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.starttls.required", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("genereux.scaredtocompile@gmail.com", "qxrosldtlgtwnpky");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Voici votre Code de récupération");

            
             
                
            
            // Set the content of the message to HTML
            message.setContent("<html><head><style type='text/css'>" +
                   "body { margin: 0; padding: 0; }" +
                   ".container { width: 100%; max-width: 600px; margin: 0 auto; }" +
                   "h2, p { margin: 0; }" +
                   "h2 { font-size: 24px; font-weight: bold; }" +
                   "p { font-size: 16px; }" +
                   "</style></head><body>" +
                   "<div class='container'>" + htmlContent + "</div>" +
                   "</body></html>", "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            emailsent=email.getText();
            ProjetPiDD m = new ProjetPiDD();
            m.changeScene("ForgotPasswordKey.fxml");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        
        
        
        
    }

    @FXML
    private void retourpage(ActionEvent event) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReservation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();*/
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
    
    
    
    
    
}