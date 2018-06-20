/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@ManagedBean
@RequestScoped
public class UniqueCinValidator implements Validator{
    
    @Inject
    private EtudiantFacade etudiantService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        if (value == null) {
            return; // Let required="true" handle, if any.
        }

        String cin = (String) value;

        if (etudiantService.findByCin(cin) != null) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Ce Code d'identite national est deja enregistrer !!", null));
        }
    }
    
    
}
