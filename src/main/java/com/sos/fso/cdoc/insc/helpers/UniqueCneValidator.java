/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@ManagedBean
@RequestScoped
public class UniqueCneValidator implements Validator{

    @Inject
    private EtudiantFacade etudiantService;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        if (value == null) {
            return; // Let required="true" handle, if any.
        }

        long cne = (long) value;

        if (etudiantService.findByCne(cne) != null) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Ce Code national est deja enregistrer !!", null));
        }
    }
    
}
