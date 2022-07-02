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
public class Mensagem {
    
    //private static FacesContext faceContexto = FacesContext.getCurrentInstance();
    
    public static void mensagemSucesso(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    mensagem + " salvo com sucesso", null));
    }
    
    public static void mensagemErro(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar: " + mensagem, null));
    }
        
    public static void mensagemSucessoExcluir(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    mensagem + " excluído com sucesso", null));
    }
    
    public static void mensagemErroExcluir(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir: " + mensagem, null));
    }
    
    public static void mensagemSucessoAlterar(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    mensagem + " alterado com sucesso", null));
    }
    
    public static void mensagemErroAlterar(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao alterar: " + mensagem, null));
    }
    
    public static void mensagemErroPesquisaData(String mensagem){
        FacesContext faceContexto = FacesContext.getCurrentInstance();
        faceContexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao pesquisar: " + mensagem, null));
    }

    
}
