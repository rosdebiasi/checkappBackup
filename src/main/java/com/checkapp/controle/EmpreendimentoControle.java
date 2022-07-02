/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Empreendimento;
import com.checkapp.webservice.WebServiceEndereco;
import java.util.List;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import com.checkapp.dao.EmpreendimentoRepositorio;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author JavaRevolutions
 */
@Component(value = "empreendimentoC")
@Scope("view")
public class EmpreendimentoControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Empreendimento lugar;
    private DataModel<Empreendimento> modelLugares;
    private int aba;

    @Autowired
    private EmpreendimentoRepositorio localRepositorio;

    @PostConstruct
    public void iniciar() {
        modelLugares = new ListDataModel<>(localRepositorio.findAll());
    }

    public List<Empreendimento> pesquisarTodo() {
        return localRepositorio.findAll();
    }

    public void pesquisarPorNome() {
        List<Empreendimento> lugares = localRepositorio.findByNome(lugar.getNome());
        modelLugares = new ListDataModel<>(lugares);
        lugar.setNome(null);
    }

    public void pesquisarPorFuncao() {
        List<Empreendimento> locais = localRepositorio.findByFuncao(lugar.getFuncao());
        modelLugares = new ListDataModel<>(locais);
        lugar.setFuncao(null);
    }

    //adicionado --será que funciona???
    public void pesquisarPorId() {
        Optional<Empreendimento> lugares = localRepositorio.findById(lugar.getId());
    }

    public void salvar() {
        try {
            localRepositorio.save(lugar);
            //GrowlView.showInfo();
            Mensagem.mensagemSucesso(lugar.getNome());
            lugar = null;
            modelLugares = null;
            aba = 0;
        } catch (Exception e) {
            //GrowlView.showError();
            Mensagem.mensagemErro(lugar.getNome());
        }
    }

    public void excluir() {
        try {
            lugar = modelLugares.getRowData();
            localRepositorio.delete(lugar);
            GrowlView.showInfo();
            Mensagem.mensagemSucessoExcluir(lugar.getNome());
            lugar = null;
            modelLugares = null;
        } catch (Exception e) {
            GrowlView.showError();
            //Mensagem.mensagemErroExcluir(lugar.getNome());
        }
    }

    public void prepararAlterar() {
        lugar = modelLugares.getRowData();
        aba = 0;
        modelLugares = null;
    }

    public void onTabChange(TabChangeEvent event) {
    }

    public void onTabClose(TabCloseEvent event) {
    }

    public void buscarCep() {
        WebServiceEndereco webService = new WebServiceEndereco();
        lugar = webService.pesquisarCep(lugar.getCep());

        if (lugar.getCep() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Não existe CEP com esse valor!", null));
        }
    }

//    getters e setters
    public Empreendimento getLugar() {
        if (lugar == null) {
            lugar = new Empreendimento();
        }
        return lugar;
    }

    public void setLugar(Empreendimento lugar) {
        this.lugar = lugar;
    }

    public DataModel<Empreendimento> getModelLugares() {
        return modelLugares;
    }

    public void setModelLugares(DataModel<Empreendimento> modelLugares) {
        this.modelLugares = modelLugares;
    }

    public EmpreendimentoRepositorio getLocalRepositorio() {
        return localRepositorio;
    }

    public void setLocalRepositorio(EmpreendimentoRepositorio localRepositorio) {
        this.localRepositorio = localRepositorio;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
