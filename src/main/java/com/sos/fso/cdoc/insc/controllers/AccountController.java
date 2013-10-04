/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Activation;
import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@Named(value = "accountController")
@SessionScoped
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
    
    private String validationKey;
    
    // ======================================
    // = Business Methods =
    // ======================================
    
    public void doActivate(){
        String message;
        try {
            compte.setActif(Boolean.TRUE);
            compteService.edit(compte);
            message = "Activation reussi !!";
        } catch (Exception e) {
            message = "Echec d'activation !!" + e;
        }
        
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
        activation = activationService.getAccountByKey(validationKey);
        compte = activation.getCompte();
        this.doActivate();
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
    
    
}
