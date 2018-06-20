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
import com.sos.fso.cdoc.insc.entities.Qualification;
import com.sos.fso.cdoc.insc.entities.Sujet;
import com.sos.fso.cdoc.insc.helpers.Hash;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.BrancheFacade;
import com.sos.fso.cdoc.insc.services.ChoixFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import com.sos.fso.cdoc.insc.services.MailerBean;
import com.sos.fso.cdoc.insc.services.QualificationFacade;
import com.sos.fso.cdoc.insc.services.SujetFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

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
    private boolean sessionOuverte;

    @Inject
    private QualificationFacade qualificationService;
    private Qualification newQualification;
    private Qualification currentQualification = new Qualification();

    @Inject
    private ActivationFacade activationService;
    private Activation activation;

    @Inject
    private BrancheFacade brancheService;
    private Branche branche = new Branche();

    @Inject
    private SujetFacade sujetService;
    private List<Sujet> listChoix;

    @Inject
    private ChoixFacade choixService;
    private Choix choix = new Choix();

    private boolean visibled = false;
    private boolean visible = false;

    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showIndex() {
        return "/index?faces-redirect=true";
    }

    public String showPrint(){
        return "/etudiant/formCandidature?faces-redirect=true";
        }
    
    public String showEdit() {
        //this.current = item;
        return "/etudiant/edit?faces-redirect=true";
    }

    public String showDetails() {
        return "/etudiant/view?faces-redirect=true";
    }

    public String showCreate() {
        this.newEtudiant = new Etudiant();
        this.newCompte = new Compte();
        activation = new Activation();
        return "/etudiant/new?faces-redirect=true";
    }

    public String showAddQualification() {
        newQualification = new Qualification();
        return "/etudiant/addQualification?faces-redirect=true";
    }

    public String showAddChoice() {
        getCurrentBranche();
        if (branche == null) {
            branche = new Branche();
            return "/etudiant/addChoice?faces-redirect=true";
        }
        listChoix = sujetService.findByBranche(branche);
        return "/etudiant/selectSujet?faces-redirect=true";
    }

    public String showSujets() {
        etudiantService.clearCache();
        listChoix = sujetService.findByBranche(branche);
        return "/etudiant/selectSujet?faces-redirect=true";
    }

    public String showLoggedDetails() {
        long cne = compte.getCne();
        System.out.println("le cne est : " + cne);
        current = etudiantService.findByCne(cne);
        System.out.println("La personne est : " + current.getNom() + "--> " + current.getCin());
        etudiantService.clearCache();
        return "/etudiant/view?faces-redirect=true";
    }

    public String showLogin() {
        return "/secured/logedEtudiant?faces-redirect=true";
    }

    public String showEditQualification(Qualification diplome) {

        currentQualification = diplome;
        return "/etudiant/editQualification?faces-redirect=true";
    }
    // ======================================
    // = Business Methods =
    // ======================================

    @PostConstruct
    public void init() {
        if (current == null) {
            sessionOuverte = false;
        } else {
            sessionOuverte = true;
        }
    }

    public void SendEmail(String email, String key, long cne, String password) {
        String response = "response?faces-redirect=true";

        try {
            mailStatus = mailerBean.sendVerificationMail(email, key, cne, password);
            this.setStatus("Envoie en cours ...(veuillez rafraishir !!!)");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        //return response;
    }

    public void sendConfirmationCandidatureMail(String email, String choix){
        try {
            mailStatus = mailerBean.sendConfirmationCandidatureMail(email, choix);
            this.setStatus("Envoie en cours ...");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
    }
    
    public List<Branche> getAllBranches() {
        return brancheService.findAll();
    }

    public List<Etudiant> getAll() {
        etudiantService.clearCache();
        return etudiantService.findAll();
    }

    public String doAddQualification() {
        logger.log(Level.INFO, "Debut de la procedure d'ajout de diplome !!");
        if (current != null) {
            newQualification.setEtudiant(current);

            try {
                qualificationService.create(newQualification);
                current.getQualificationList().add(newQualification);
                etudiantService.clearCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.log(Level.SEVERE, "Erreur de donnee the current entity is null !!");
        }
        return "/etudiant/view?faces-redirect=true";
    }

    public String doAddBranche(){
        current.setBranche(branche);
        String choix = branche.getIntitule();
        String email = current.getEmail();
        sendConfirmationCandidatureMail(email, choix);
        etudiantService.edit(current);
        return "/etudiant/view?faces-redirect=true";
    }
    
    public String doAddChoix(Sujet sujet) {
        int nbChoix = current.getChoixList().size();
        System.out.println("le nombre de choix est " + nbChoix);

        if (nbChoix > -1 && nbChoix < 3) {
            List<Choix> aTrouver = current.getChoixList();
            Iterator<Choix> iterator = aTrouver.iterator();

            while (iterator.hasNext()) {
                Choix choixExistant = iterator.next();
                Sujet sujetExistant = choixExistant.getIdSujet();
                System.out.println("le sujet de l'iteraor" + sujetExistant.getIntitule());
                if (sujetExistant.getIntitule() == null ? sujet.getIntitule() == null : sujetExistant.getIntitule().equals(sujet.getIntitule())) {
                    addMessage("update", FacesMessage.SEVERITY_ERROR, "Vous avez deja choisi ce sujet !!", "Error !!");
                    return null;
                } else {
                    System.out.println("le sujet n'est pas  dans la liste des sujet de l'etudiant");
                }
            }

            choix.setIdEtudiant(current);
            choix.setIdSujet(sujet);
            choixService.create(choix);
            current.getChoixList().add(choix);
            System.out.println("Edition en cours ajout de " + choix.getIdSujet().getIntitule());
            //etudiantService.edit(current);
            etudiantService.clearCache();
            return "/etudiant/view?faces-redirect=true";

        } else {
            addMessage("update", FacesMessage.SEVERITY_ERROR, "le maximum de choix (3) permis est atteint !!", "Error !!");
            return "/etudiant/view?faces-redirect=true";
        }

    }

    public String doCreate() {
        //creation du compte a partir des infos de l'etudiant
        newCompte.setCne(newEtudiant.getCne());
        newCompte.setEmail(newEtudiant.getEmail());
        newCompte.setActif(Boolean.FALSE);
        newCompte.setGroupe("candidat");
        
        //Creation Du compte
        String password = newCompte.getPassword();
        String hashedPassword = Hash.hash(password);
        System.out.println("the hashed password is " + hashedPassword);

        newCompte.setPassword(hashedPassword);
        compteService.create(newCompte);
        System.out.println("Compte creer");
        //Generation de la cle d'identification et envoie de mail d'activation
        final String key = UUID.randomUUID().toString();
        System.out.println("La cle generer est " + key);
        SendEmail(newEtudiant.getEmail(), key, newEtudiant.getCne(), password);        
//Definition de l'activation
        activation.setActivationKey(key);
        activation.setCompte(newCompte);
        activationService.create(activation);

        //Creation de l'etudiant
        etudiantService.create(newEtudiant);
        return "waitValidation?faces-redirect=true";
    }

    public String doEdit() {
        try {
            etudiantService.edit(current);

        } catch (Exception e) {
        }
        return showDetails();
    }

    public String doEditQualification() {
        qualificationService.edit(currentQualification);
        return "/etudiant/view?faces-redirect=true";
    }

    public void doRemoveQualification(Qualification diplome) {
        try {
            this.current.getQualificationList().remove(diplome);

            qualificationService.remove(diplome);
            etudiantService.edit(current);
            addMessage("update", FacesMessage.SEVERITY_INFO, "le Diplome a ete supprimer avec success !!", "Error !!");
        } catch (Exception e) {
        }

    }
    
    public void doRemoveChoix(Choix choix){
        try {
            this.current.getChoixList().remove(choix);
            choixService.remove(choix);
            etudiantService.edit(current);
            addMessage("update", FacesMessage.SEVERITY_INFO, "le Sujet a ete supprimer avec success !!", "Error !!");
            
        } catch (Exception e) {
        }
    }
    
    // ======================================
    // = Constructors et Helpers=
    // ======================================

    public EtudiantController() {
    }

    public void getCurrentBranche() {
        if (!current.getChoixList().isEmpty()) {

            List<Choix> choixAnciens = choixService.findByIdEtudiant(current);
            choix = choixAnciens.get(1);
            branche = choix.getIdSujet().getBranche();
            System.out.println("la nombre est : " + branche.getIntitule());
        } else {
            branche = null;
        }
    }

    private void addMessage(String key, FacesMessage.Severity severity, String message, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        Flash flash = context.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesMessage msg = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(key, msg);
    }

    public void getRightSemestre(SelectEvent event) {

        String diplome = newQualification.getDiplome();
        switch (diplome) {
            case "Bacaleaureat":
                visible = false;
                visibled = true;
                break;
            case "Licence fondamentale":
                visible = true;
                visibled = true;
                break;

            case "Licence professionelle":
                visible = true;
                visibled = true;
                break;
            case "Master recherche":
                visible = true;
                visibled = false;
                break;
            case "Master specialise":
                visible = true;
                visibled = false;
                break;
            case "Autre diplome equivalent":
                visible = true;
                visibled = true;
                break;
            case "Diplome d'ingenieur":
                visible = true;
                visibled = true;
                break;
        }

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
        if (current == null) {
            current = etudiantService.findByCne(compte.getCne());
        }
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

    public boolean isSessionOuverte() {
        return sessionOuverte;
    }

    public void setSessionOuverte(boolean sessionOuverte) {
        this.sessionOuverte = sessionOuverte;
    }

    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public Qualification getNewQualification() {
        return newQualification;
    }

    public void setNewQualification(Qualification newQualification) {
        this.newQualification = newQualification;
    }

    public boolean isVisibled() {
        return visibled;
    }

    public void setVisibled(boolean visibled) {
        this.visibled = visibled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Qualification getCurrentQualification() {
        return currentQualification;
    }

    public void setCurrentQualification(Qualification currentQualification) {
        this.currentQualification = currentQualification;
    }

}
