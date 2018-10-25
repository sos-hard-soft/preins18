/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Laboratoire;
import com.sos.fso.cdoc.insc.services.LaboratoireFacade;
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
@Named(value = "laboratoireController")
@SessionScoped
public class LaboratoireController implements Serializable{
   // ======================================
    // = Attributes =
    // ======================================
    private static final Logger logger = Logger.getLogger(LaboratoireController.class.getName());
    
    @Inject
    private LaboratoireFacade laboratoireService;
    private Laboratoire newLaboratoire;
    private Laboratoire current;
    private List<Laboratoire> laboratoires;
    
    /**
     * Creates a new instance of MasterController
     * @return 
     */
    public String showEdit() {
        //this.current = item;
        return "/index?faces-redirect=true";
    }

    public String showDetails(Laboratoire labo) {
        current = labo;
        return "/manage/viewLabo?faces-redirect=true";
    }
    public String showListLaboratoire() {
        laboratoires = laboratoireService.findAll();
        return "/manage/listLabo?faces-redirect=true";
    }
    public String showCreateLaboratoire() {
        newLaboratoire = new Laboratoire();
        return "/manage/addLabo?faces-redirect=true";
    }
    
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireService.findAll();
    }
    
    
    public String doCreateLaboratoire(){
        System.out.println("Procedure de creation " + newLaboratoire.getIntitule());
        laboratoireService.create(newLaboratoire);
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "/index?faces-redirect=true";
    }
    
    public LaboratoireController() {
        
       
    }

    public Laboratoire getNewLaboratoire() {
        return newLaboratoire;
    }

    public void setNewLaboratoire(Laboratoire newLaboratoire) {
        this.newLaboratoire = newLaboratoire;
    }

    public Laboratoire getCurrent() {
        return current;
    }

    public void setCurrent(Laboratoire current) {
        this.current = current;
    }

    public Laboratoire getLaboratoire(java.lang.Integer id) {
        return laboratoireService.find(id);
    }
    
    @FacesConverter(forClass = Laboratoire.class)
    public static class LaboratoireControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LaboratoireController controller = (LaboratoireController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "laboratoireController");
            return controller.getLaboratoire(getKey(value));
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
            if (object instanceof Laboratoire) {
                Laboratoire o = (Laboratoire) object;
                return getStringKey(o.getIdLaboratoire());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Laboratoire.class.getName());
            }
        }
    
} 
}
