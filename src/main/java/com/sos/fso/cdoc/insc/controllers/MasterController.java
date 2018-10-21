/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Filiere;
import com.sos.fso.cdoc.insc.entities.Person;
import com.sos.fso.cdoc.insc.services.FiliereFacade;
import com.sos.fso.cdoc.insc.services.PersonFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author mabsalhi.sos
 */
@Named(value = "masterController")
@SessionScoped
public class MasterController implements Serializable {
    // ======================================
    // = Attributes =
    // ======================================
    private static final Logger logger = Logger.getLogger(MasterController.class.getName());
    
    @Inject
    private PersonFacade personService;
    private Person newPerson;
    private Person current;
    private List<Person> personnes;
    
    @Inject
    private FiliereFacade filiereService;
    private Filiere newFiliere;
    private Filiere lafiliere;
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
    public String showCreatePerson() {
        newPerson = new Person();
        return "/filiere/newPerson?faces-redirect=true";
    }
    public List<Filiere> getAllFilieres() {
        return filiereService.findAll();
    }

    public List<Person> getAllPersons() {
        return personService.findAll();
    }
    
    public String doCreateFiliere(){
        System.out.println("Click sur bouton");
        System.out.println("Creation de la filiere : " + newFiliere.getIntitule());
        filiereService.create(newFiliere);
        return "/index?faces-redirect=true";
    }
    public String doCreatePerson(){
        System.out.println("Procedure de creation " + newPerson.getCin());
        personService.create(newPerson);
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "/index?faces-redirect=true";
    }
    
    public MasterController() {
        
       
    }

    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

    public Person getCurrent() {
        return current;
    }

    public void setCurrent(Person current) {
        this.current = current;
    }

    public Filiere getNewFiliere() {
        return newFiliere;
    }

    public void setNewFiliere(Filiere newFiliere) {
        this.newFiliere = newFiliere;
    }

    public Filiere getLafiliere() {
        return lafiliere;
    }

    public void setLafiliere(Filiere lafiliere) {
        this.lafiliere = lafiliere;
    }
    
    public Person getPerson(java.lang.Integer id) {
        return personService.find(id);
    }
    
    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MasterController controller = (MasterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "masterController");
            return controller.getPerson(getKey(value));
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
            if (object instanceof Person) {
                Person o = (Person) object;
                return getStringKey(o.getIdPerson());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Person.class.getName());
            }
        }
    
}
    
}
