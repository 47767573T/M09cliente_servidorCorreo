
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by 47767573t on 24/02/16.
 */
public class EnviarCorreo {

    static Properties props;
    static final String EMAIL_DESTINO = "dremon.iespoblenou@gmail.com";
    static final String EMAIL_ORIGEN = "47767573t@iespoblenou.org";
    static final String USER = "47767573t@iespoblenou.org";
    static final String PASSWORD = "**************";


    public static void main(String[] args) {

        //primer paso
        addBasicProperties();

        //Crear sesion
        Session session = Session.getInstance(
                props
                , new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER, PASSWORD);
                    }
                }
        );

        //Definir origen, destino y mensaje
        Message msg = montarEmail(session, EMAIL_ORIGEN, EMAIL_DESTINO);

        //Enviar mensaje
        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    static public void addBasicProperties(){

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public static Message montarEmail(Session session, String origen, String destino){

        // Crear Objeto MIME para mensajes
        Message message = new MimeMessage(session);


        try {

            // definir desde donde sale

            message.setFrom(new InternetAddress(origen));


            // definir a donde va
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(origen));

            // Definir asunto
            message.setSubject("MOISES PRUEBA3 DE ENVIO");

            // Definir mensaje
            message.setText("Esto es una prueba para envio a Dionis con copia oculta a mi mismo");

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }



}
