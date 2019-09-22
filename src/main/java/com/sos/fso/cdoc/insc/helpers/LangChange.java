/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author mabsalhi.sos@gmail.com
 */
@ManagedBean(name="language")
@ApplicationScoped
public class LangChange implements Serializable {
private static final long serialVersionUID = 1L;
	
//	private String localeCode;
//	private Locale locale;
//        @PostConstruct
//    public void init() {
//        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
//    }
//        
//	private static final Map<String,Object> countries;
//	static{
//		countries = new LinkedHashMap<>();
//		countries.put("Français", Locale.FRENCH); //label, value
//		countries.put("Arabe", new Locale("ar", "MA"));
//	}
//
//	public Map<String, Object> getCountriesInMap() {
//		return countries;
//	}
//
//	
//	public String getLocaleCode() {
//		return localeCode;
//	}
//
//
//	public void setLocaleCode(String localeCode) {
//		this.localeCode = localeCode;
//	}
//
//    public Locale getLocale() {
//        return locale;
//    }
//
//    public void setLocale(Locale locale) {
//        this.locale = locale;
//    }
//        
//        
//	//value change event listener
//	public void countryLocaleCodeChanged(ValueChangeEvent e){
//		
//		String newLocaleValue = e.getNewValue().toString();
//		
//		//loop country map to compare the locale code
//                for (Map.Entry<String, Object> entry : countries.entrySet()) {
//        
//        	   if(entry.getValue().toString().equals(newLocaleValue)){
//        		locale = new Locale(newLocaleValue);
//        		FacesContext.getCurrentInstance()
//        			.getViewRoot().setLocale((Locale)entry.getValue());
//        		
//        	  }
//               }
//	}

private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
