/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author https://www.jeejava.com
 */
@ManagedBean(name="language")
@ViewScoped
public class LangChange implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of LocaleSwitcher
     */
    public LangChange() {
    }
     
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
 
    public Locale getLocale() {
        return locale;
    }
 
    public String getLanguage() {
        return locale.getLanguage();
    }
 
    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
