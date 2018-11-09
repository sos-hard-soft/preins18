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
    public Future<String> sendVerificationMail(String email) {
        String status;
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            message.setSubject("Message de confirmation de demande de candidature en Cycle Doctoral");
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            
            //Corps de l'email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h1>Message de confirmation de candidature</h1>"
                    + "<p>Bonjour</p>"
                    + "<p>Vous avez entamez la procédure de candidature au cycle doctorale a la <strong>Faculté des sciences juridiques économiques et sociales</strong> d'Oujda<br />"
                    + "Pour confirmer les données saisi et créer votre compte veuillez vous presenté auprès du service d'étude doctorale a la faculté des Sciences Juridiques Economiques et sociales d'Oujda, "
                    + "munis de votre Carte National d'Identité et cela avant le "
                    + "14 novembre 2018 afin de recuperer votre login et mot de passe d'accès a l'application de candidature.</p>"
                    + "</body></html>";
            htmlPart.setContent(htmlContent, "text/html");
            
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
        return new AsyncResult<>(status);
    }
 
 
    /**
     *
     * @param email
     * @param key
     * @param cin
     * @param password
     * @return
     */
    @Asynchronous
    public Future<String> sendAdminMail(String email, String key,String cin, String password) {
        String status;
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email, false));
            message.setSubject("Activation compte : " + cin);
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            
            //Corps de l'email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h1>Message de confirmation de candidature</h1>"
                    + "<p>Activation du compte du candidat :</p>"
                    + "http://preinsmasterdroit.ump.ma/candidoc/compte/validation.xhtml?key="+key+"<br />"
                    + "<h4>Login est le CIN : " + cin + "</h4>"
                    + "<h4>Mot de passe : " + password + "<h4>"
                    + "</body></html>";
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
        return new AsyncResult<>(status);
    }
    
    
 @Asynchronous
    public Future<String> sendConfirmationCandidatureMail(String email, String branche) {
        String status;
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("alternative");
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("masterfsjes@ump.ac.ma", false));
            message.setSubject("Message de confirmation de candidature");
            message.setHeader("X-Mailer", "JavaMail");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            
            //Corps de l'email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h1>Message de confirmation de candidature</h1>"
                    + "<p>Bonjour</p>"
                    + "<p>Vous avez effectué votre choix de candidature au master <strong>Faculté des sciences juridiques économiques et sociales</strong> dans les filières suivantes : " + branche + "<br /></p>"
                    + "<p>Pour finaliser votre candidature, veuillez deposer votre dossier complet(consulter la page suivante : http://preinsmasterdroit.ump.ma/master) aupres du service de master a la Faculté des sciences juridiques économiques et sociales d'oujda entre le 01 et le 27 octobre 2018.</p>"
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
