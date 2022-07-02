/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author rosan
 */
public class GrowlView {
    
    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Message Content");
    }

    public static void showWarn() {
        addMessage(FacesMessage.SEVERITY_WARN, "Warn Message", "Message Content");
    }

    public static  void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Message Content");
    }

    public void showSticky() {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", "Message Content"));
    }
    
}
