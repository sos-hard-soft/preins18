/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Filiere;
import com.sos.fso.cdoc.insc.services.FiliereFacade;
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
@Named(value = "filiereController")
@SessionScoped
public class FiliereController implements Serializable{
   // ======================================
    // = Attributes =
    // ======================================
    private static final Logger logger = Logger.getLogger(FiliereController.class.getName());
    
    @Inject
    private FiliereFacade filiereService;
    private Filiere newFiliere;
    private Filiere current;
    private List<Filiere> filieres;
    
    /**
     * Creates a new instance of MasterController
     * @return 
     */
    public String showEdit() {
        //this.current = item;
        return "/index?faces-redirect=true";
    }

    public String showDetails() {
        return "/filiere/view?faces-redirect=true";
    }

    public String showCreateFiliere() {
        newFiliere = new Filiere();
        return "/filiere/addFiliere?faces-redirect=true";
    }
    
    public List<Filiere> getAllFilieres() {
        return filiereService.findAll();
    }
    
    
    public String doCreateFiliere(){
        System.out.println("Procedure de creation " + newFiliere.getIntitule());
        filiereService.create(newFiliere);
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "/index?faces-redirect=true";
    }
    
    public FiliereController() {
        
       
    }

    public Filiere getNewFiliere() {
        return newFiliere;
    }

    public void setNewFiliere(Filiere newFiliere) {
        this.newFiliere = newFiliere;
    }

    public Filiere getCurrent() {
        return current;
    }

    public void setCurrent(Filiere current) {
        this.current = current;
    }

    public Filiere getFiliere(java.lang.Integer id) {
        return filiereService.find(id);
    }
    
    @FacesConverter(forClass = Filiere.class)
    public static class FiliereControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FiliereController controller = (FiliereController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "filiereController");
            return controller.getFiliere(getKey(value));
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
            if (object instanceof Filiere) {
                Filiere o = (Filiere) object;
                return getStringKey(o.getIdFiliere());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Filiere.class.getName());
            }
        }
    
} 
}
