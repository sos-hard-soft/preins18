/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Activation;
import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.helpers.Sosutil;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@Named(value = "accountController")
@RequestScoped
public class AccountController implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private ActivationFacade activationService;
    private Activation activation;
    
    @Inject
    private CompteFacade compteService;
    private Compte compte;
    
    @Inject
    private EtudiantFacade etudiantService;
    private Etudiant etudiant;
    
    private String validationKey;
    private String text = "Page de validation par email";

    // ======================================
    // = Business Methods =
    // ======================================
    
    public void doActivate(){
        String message;
        try {
            compte.setActif(Boolean.TRUE);
            compteService.edit(compte);
            message = "Activation reussi !!";
            System.out.println(message);
        } catch (Exception e) {
            message = "Echec d'activation !!" + e;
            System.out.println(message);
        }
        
    }
    
    public String showIndex() {
        return "/index?faces-redirect=true";
    }
    // ======================================
    // = Constructors et Helpers=
    // ======================================
    public AccountController() {
    }
    @PostConstruct
    public void init(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
                
        Map<String,String> params;
        params = facesContext.getExternalContext().getRequestParameterMap();
        String key = params.get("key");
        
        validationKey = key;
        try {
            activation = activationService.getAccountByKey(validationKey);
            if (activation == null) {
                addMessage("fatal", FacesMessage.SEVERITY_FATAL, "le code de validation est incorrect ou corrempu !!", "Error !!");
            }else{
                compte = activation.getCompte();
                this.doActivate();
                
        long cne = compte.getCne();
        etudiant = etudiantService.findByCne(cne);
              }
        } catch (Exception e) {
            
        }
        
        
    }
    
    
    public void addMessage(String key, FacesMessage.Severity severity, String message, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        Flash flash = context.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesMessage msg = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(key, msg);
    }
    // ======================================
    // = Getters & setters =
    // ======================================
    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

  
}