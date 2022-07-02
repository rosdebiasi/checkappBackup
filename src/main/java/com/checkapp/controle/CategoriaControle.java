/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

import com.checkapp.dao.CategoriaRepositorio;
import java.io.Serializable;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Categoria;
import com.checkapp.dao.CategoriaRepositorio;
import com.checkapp.entidade.Item;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author JavaRevolutions
 */
@Component(value = "categoriaC")
@Scope("view")
public class CategoriaControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categoria categoria;
    private DataModel<Categoria> modelCategorias;
    private int aba;

    private List<Item> itens;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @PostConstruct
    public void iniciar() {
        modelCategorias = new ListDataModel<>(categoriaRepositorio.findAll());
    }

    public List<Categoria> pesquisarTodo() {
        return categoriaRepositorio.findAll();
    }

    public void pesquisarPorNome() {
        List<Categoria> categorias = categoriaRepositorio.findByNomeContaining(categoria.getNome());
        modelCategorias = new ListDataModel<>(categorias);
        categoria.setNome(null);
    }

    //adicionado --será que funciona???
    public void pesquisarPorId() {
        Optional<Categoria> categorias = categoriaRepositorio.findById(categoria.getId());
    }

    public void salvar() {
        try {
            //categoria.getItens();
            categoriaRepositorio.save(categoria);
            Mensagem.mensagemSucesso(categoria.getNome());
            categoria = null;
            modelCategorias = null;
            aba = 0;
        } catch (Exception e) {
            Mensagem.mensagemErro(categoria.getNome());
        }
    }

    //não é isso
    public void duplicar() {
        try {
            categoria.setItens(itens);
            categoriaRepositorio.save(categoria);
            Mensagem.mensagemSucesso(categoria.getNome());
            aba = 0;
        } catch (Exception e) {
            Mensagem.mensagemErro(categoria.getNome());
        }
    }

    public void excluir() {
        try {
            categoria = modelCategorias.getRowData();
            categoriaRepositorio.delete(categoria);
            Mensagem.mensagemSucessoExcluir(categoria.getNome());
            categoria = null;
            itens = null;
            modelCategorias = null;
        } catch (Exception e) {
            Mensagem.mensagemErroExcluir(categoria.getNome());
        }
    }

    public void prepararAlterar() {
        categoria = modelCategorias.getRowData();
        //itens = categoria.getItens();
        modelCategorias = null;
        aba = 0;
    }

    public void onTabChange(TabChangeEvent event) {
    }

    public void onTabClose(TabCloseEvent event) {
    }

//    getters e setters
    public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public DataModel<Categoria> getModelCategorias() {
        return modelCategorias;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
