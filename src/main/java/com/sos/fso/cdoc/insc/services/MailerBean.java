/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author mab.salhi
 */
@Named
@Stateless
public class MailerBean {
    @Resource(name="mail/fsjesMailer")
    private Session session;

    private static final Logger logger = Logger.getLogger(MailerBean.class.getName());

    /**
     *
     * @param email
     * @param key
     * @return
     */
    @Asynchronous
    public Future<String> sendVerificationMail(String email, String key,long cne, String password) {
        String status;
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            message.setSubject("Message de confirmation de demande d'inscription en Cycle Doctoral");
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            
            //Corps de l'email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h1>Message de confirmation de candidature</h1>"
                    + "<p>Bonjour</p>"
                    + "<p>Vous avez entamez la procédure de candidature au postes de doctorants au <strong>Centre d'Etudes Doctorales Sciences et Téchniques</strong><br />"
                    + "Pour confirmer les données saisi et créer votre compte veuillez cliquez sur le lien suivant ou le copiez collez dans votre navigateur : </p>"
                    + "http://www.fso.ump.ma/cedocinsc/compte/validation.xhtml?key="+key+"<br />"
                    + "<p>Le login est votre cne : "+ cne + "<br />"
                    + "Votre mot de passe est : " + password + "</p></body></html>";
            htmlPart.setContent(htmlContent, "text/html");
            
            //String messageBody = "Pour confirmer votre demande d'inscription veuillez , cliquez sur le lien suivant : "
             //       + "http://localhost:8080/cdocFSOinsc/compte/validation.xhtml?key="+key;
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            
            message.setSentDate(timeStamp);
            Transport.send(message);
            status = "Sent";
            logger.log(Level.INFO, "Mail sent to {0}", email);
        } catch (MessagingException ex) {
            logger.severe("Error in sending message.");
            status = "Encountered an error: " + ex.getMessage();
            logger.severe(ex.getMessage());
        }
        return new AsyncResult<String>(status);
    }
 
 
 @Asynchronous
    public Future<String> sendConfirmationCandidatureMail(String email, String branche) {
        String status;
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            message.setSubject("Message de confirmation de candidature");
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            
            //Corps de l'email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h1>Message de confirmation de candidature</h1>"
                    + "<p>Bonjour</p>"
                    + "<p>Vous avez effectué votre choix de candidature au postes de doctorants au <strong>Centre d'Etudes Doctorales Sciences et Téchniques</strong> dans la formation doctorale : " + branche + "<br /></p>"
                    + "<p>Pour finaliser votre candidature, veuillez deposer votre dossier complet(consulter la page suivante : http://www.fso.ump.ma/recherche/etude-doctorale) aupres du service de doctorat a la faculte des sciences d'oujda entre le 21 et le 27 octobre 2013.</p>"
                    + "Merci.</body></html>";
            htmlPart.setContent(htmlContent, "text/html");
            
            //String messageBody = "Pour confirmer votre demande d'inscription veuillez , cliquez sur le lien suivant : "
             //       + "http://localhost:8080/cdocFSOinsc/compte/validation.xhtml?key="+key;
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            
            message.setSentDate(timeStamp);
            Transport.send(message);
            status = "Sent";
            logger.log(Level.INFO, "Mail sent to {0}", email);
        } catch (MessagingException ex) {
            logger.severe("Error in sending message.");
            status = "Encountered an error: " + ex.getMessage();
            logger.severe(ex.getMessage());
        }
        return new AsyncResult<String>(status);
    }
    
}
