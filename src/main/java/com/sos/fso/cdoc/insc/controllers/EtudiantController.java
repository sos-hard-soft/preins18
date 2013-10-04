/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Activation;
import com.sos.fso.cdoc.insc.entities.Branche;
import com.sos.fso.cdoc.insc.entities.Choix;
import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Sujet;
import com.sos.fso.cdoc.insc.helpers.Hash;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.BrancheFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import com.sos.fso.cdoc.insc.services.MailerBean;
import com.sos.fso.cdoc.insc.services.SujetFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@Named(value = "etudiantController")
@SessionScoped
public class EtudiantController implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    protected MailerBean mailerBean;
    protected String email;
    protected String status;
    private static final Logger logger = Logger.getLogger(EtudiantController.class.getName());
    private Future<String> mailStatus;
        
    @Inject
    private EtudiantFacade etudiantService;
    private Etudiant newEtudiant;
    private Etudiant current;
    private List<Etudiant> etudiants;
    
    @Inject
    private CompteFacade compteService;
    private Compte newCompte;
    private Compte compte;
    
    @Inject
    private ActivationFacade activationService;
    private Activation activation;
    
    @Inject
    private BrancheFacade brancheService;
    private Branche branche = new Branche();
    
    @Inject
    private SujetFacade sujetService;
    private List<Sujet> listChoix;
    private Choix choix = new Choix();
    
    
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showEdit(Etudiant item){
        this.current = item;
        return "/etudiant/view?faces-redirect=true";
    }

    public String showCreate(){
        this.newEtudiant = new Etudiant();
        this.newCompte = new Compte();
        activation = new Activation();
        return "/etudiant/new?faces-redirect=true";
    }
    
    public String showAddChoice(){
    return "/etudiant/addChoice?faces-redirect=true";
    }
    
    public String showSujets(){
        etudiantService.clearCache();
        listChoix = sujetService.findByBranche(branche);
        return "/etudiant/selectSujet?faces-redirect=true";
    }
    
    public String showLoggedDetails(){
        double cne = compte.getCne();
        System.out.println("le cne est : " + cne);
        current = etudiantService.findByCne(cne);
        System.out.println("La personne est : " + current.getNom() + "--> " + current.getCin());

        if (!current.getChoixList().isEmpty()) {
            choix = current.getChoixList().get(1);
            branche = choix.getIdSujet().getBranche();
            System.out.println("la branche : " + branche.getIntitule());
        }else {
            branche = null;
        }
        
        return "/etudiant/view?faces-redirect=true";
    }
    
    public String showLogin(){
        return "/security/login.xhtml";
    }
    // ======================================
    // = Business Methods =
    // ======================================
    
    public void SendEmail(String email, String key) {
        String response = "response?faces-redirect=true";

        try {
            mailStatus = mailerBean.sendVerificationMail(email, key);
            this.setStatus("Envoie en cours ...(veuillez rafraishir !!!)");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        //return response;
    }
    
    public List<Branche> getAllBranches(){
        return brancheService.findAll();
    }
    
    public List<Etudiant> getAll(){
        etudiantService.clearCache();
        return etudiantService.findAll();
    }

    
    
    public void doAddChoix(Sujet sujet){
        choix.setIdEtudiant(current);
        choix.setIdSujet(sujet);
        current.getChoixList().add(choix);
        System.out.println("Edition en cours ajout de " + choix.getIdSujet().getIntitule());
        etudiantService.edit(current);
        etudiantService.clearCache();
    }
    
    
    public String doCreate(){
        //creation du compte a partir des infos de l'etudiant
        newCompte.setCne(newEtudiant.getCne());
        newCompte.setEmail(newEtudiant.getEmail());
        newCompte.setActif(Boolean.FALSE);
        newCompte.setGroupe("candidat");
        //Generation de la cle d'identification et envoie de mail d'activation
        final String key = UUID.randomUUID().toString();
        System.out.println("La cle generer est " + key);
        SendEmail(newEtudiant.getEmail(), key);
        //Creation Du compte
        String password = newCompte.getPassword();
        String hashedPassword = Hash.hash(password);
        System.out.println("the hashed password is " + hashedPassword);

        newCompte.setPassword(hashedPassword);
        compteService.create(newCompte);
        System.out.println("Compte creer");
        //Definition de l'activation
        activation.setActivationKey(key);
        activation.setCompte(newCompte);
        activationService.create(activation);
        
        //Creation de l'etudiant
        etudiantService.create(newEtudiant);
        return "waitValidation?faces-redirect=true";
    }
    
    
    
    
    
    
    
    
    
    // ======================================
    // = Constructors et Helpers=
    // ======================================
    
    public EtudiantController() {
    }
    
    private void addMessage(String key, FacesMessage.Severity severity, String message, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        Flash flash = context.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesMessage msg = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(key, msg);
    }
    
    // ======================================
    // = Getters & setters =
    // ======================================
    public Etudiant getNewEtudiant() {
        return newEtudiant;
    }
    
    public void setNewEtudiant(Etudiant newEtudiant) {
        this.newEtudiant = newEtudiant;
    }

    public Etudiant getCurrent() {
        return current;
    }

    public void setCurrent(Etudiant current) {
        this.current = current;
    }

    public Compte getNewCompte() {
        return newCompte;
    }

    public void setNewCompte(Compte newCompte) {
        this.newCompte = newCompte;
    }

    public Compte getCompte() {
        if (compte == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                long cne = Long.parseLong(principal.getName());
                compte = compteService.findByCne(cne);
            }
        }
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    public List<Sujet> getListChoix() {
        return listChoix;
    }

    public void setListChoix(List<Sujet> listChoix) {
        this.listChoix = listChoix;
    }

    public Choix getChoix() {
        return choix;
    }

    public void setChoix(Choix choix) {
        this.choix = choix;
    }
    
    
    
}
