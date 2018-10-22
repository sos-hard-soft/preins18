/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Sujet;
import com.sos.fso.cdoc.insc.services.SujetFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author master05
 */
@Named(value = "sujetController")
@SessionScoped
public class SujetController implements Serializable{
   // ======================================
    // = Attributes =
    // ======================================
    private static final Logger logger = Logger.getLogger(SujetController.class.getName());
    
    @Inject
    private SujetFacade sujetService;
    private Sujet newSujet;
    private Sujet current;
    private List<Sujet> sujets;
    
    /**
     * Creates a new instance of MasterController
     * @return 
     */
    public String showEdit() {
        //this.current = item;
        return "/index?faces-redirect=true";
    }

    public String showDetails() {
        return "/manage/view?faces-redirect=true";
    }

    public String showCreateSujet() {
        newSujet = new Sujet();
        return "/manage/addSujet?faces-redirect=true";
    }
    
    public List<Sujet> getAllSujets() {
        return sujetService.findAll();
    }
    
    
    public String doCreateSujet(){
        System.out.println("Procedure de creation " + newSujet.getIntitule());
        sujetService.create(newSujet);
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "/index?faces-redirect=true";
    }
    
    public SujetController() {
        
       
    }

    public Sujet getNewSujet() {
        return newSujet;
    }

    public void setNewSujet(Sujet newSujet) {
        this.newSujet = newSujet;
    }

    public Sujet getCurrent() {
        return current;
    }

    public void setCurrent(Sujet current) {
        this.current = current;
    }

    public Sujet getSujet(java.lang.Integer id) {
        return sujetService.find(id);
    }
    
    @FacesConverter(forClass = Sujet.class)
    public static class SujetControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SujetController controller = (SujetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sujetController");
            return controller.getSujet(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sujet) {
                Sujet o = (Sujet) object;
                return getStringKey(o.getIdSujet());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sujet.class.getName());
            }
        }
    
} 
}
