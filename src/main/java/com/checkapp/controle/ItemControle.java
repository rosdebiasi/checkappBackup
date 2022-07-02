/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Categoria;
import com.checkapp.dao.CategoriaRepositorio;
import com.checkapp.dao.ItemRepositorio;
//import com.checkapp.entidade.Avaliacao;
import com.checkapp.entidade.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "itemC")
@Scope("view")
public class ItemControle implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Item item;
    private DataModel<Item> modelItens;
    private int aba;
    
    private List<Item> itens;
    private List<SelectItem> comboCategoria;
    private Categoria categoria;


    @Autowired
    private ItemRepositorio itemRepositorio;
    
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    
    @PostConstruct
    public void iniciar() {
        carregarComboBoxCategoria();
        //modelItens = new ListDataModel<>(itemRepositorio.findAll()); 
        //nesse vou deixar vazio pois vai ficar muitos dados, mas pergunto ao usuário isso também!
    }
    
    public List<Item> pesquisarTodo(){
        return itemRepositorio.findAll(); 
    }

    public void pesquisarPorNome(){      
        List<Item> itens = itemRepositorio.findByNome(item.getNome());
        modelItens = new ListDataModel<>(itens);
        item.setNome(null);
    }
    
    public void pesquisarPorNomeCategoria(){      
        List<Item> itens = itemRepositorio.findByNomeCategoria(categoria.getNome());
        modelItens = new ListDataModel<>(itens);
        item.setCategoria(null);
    }
    
     //adicionado --será que funciona???
    public void pesquisarPorId(){      
        Optional<Item> itens = itemRepositorio.findById(item.getId());
    }  
    
    private void carregarComboBoxCategoria(){
       List<Categoria> categorias = categoriaRepositorio.findAll();
       comboCategoria = new ArrayList<>();
       for(Categoria cat : categorias){
           comboCategoria.add(new SelectItem(cat.getId(), cat.getNome()));
       }   
   }
    
    public void salvar(){
        try {
            item.setCategoria(categoria);
            itemRepositorio.save(item);
            Mensagem.mensagemSucesso(item.getNome());
            item = null;
            categoria = null;
            aba =0;
        } catch (Exception e) {
            Mensagem.mensagemErro(item.getNome());
        }
    }
    
    //utilizar lá no questionário- teste
    public void adicionarItem(long id){
        try {
            Item temp_item = itemRepositorio.getById(id);
            temp_item.setId(null);  
            //prepararAlterar2(temp_item.getId());
            itemRepositorio.save(temp_item);
            Mensagem.mensagemSucesso(temp_item.getNome());
        } catch (Exception e) {
            Mensagem.mensagemErro("não foi possivel salvar");
        }
    }
    
    public void prepararAlterar2(long id){
       Item temp_item = itemRepositorio.getById(id);
       temp_item = modelItens.getRowData();
       categoria = temp_item.getCategoria();
       carregarComboBoxCategoria();
       aba=0;
       modelItens = null;
   }
    
//            temp_item = modelItens.getRowData();
//            categoria = temp_item.getCategoria();
//            carregarComboBoxCategoria();
//            aba=1;
            
//            carregarComboBoxCategoria();
//            aba=1;
    
    
    //utilizar lá no questionário- base professor
    public void duplicarItem(long id){
        try {
            Item temp_item = itemRepositorio.getById(id);
            temp_item.setId(null);       
            itemRepositorio.save(temp_item);
            Mensagem.mensagemSucesso("sucesso");
        } catch (Exception e) {
            Mensagem.mensagemErro("não foi possivel duplicar");
        }
    }
        
    public void excluir(){
        try {
            item = modelItens.getRowData();
            itemRepositorio.delete(item);
            Mensagem.mensagemSucessoExcluir(item.getNome());
            item = null;
            modelItens = null;
        } catch (Exception e) {
            Mensagem.mensagemErroExcluir(item.getNome());
        }
    }
        
   public void prepararAlterar(){
       item = modelItens.getRowData();
       categoria = item.getCategoria();
       carregarComboBoxCategoria();
       modelItens = null;
       aba=0;
       
   }
   
   public void onTabChange(TabChangeEvent event){   
        if (event.getTab().getTitle().equals("Novo")) {
            if (comboCategoria == null){
            carregarComboBoxCategoria();
            }
        }
   }
   
//    public void onTabChange(TabChangeEvent event){   
//        if (event.getTab().getTitle().equals("Novo")) {
//            carregarComboBoxCategoria();
//        }
//   }
//   
   public void onTabClose(TabCloseEvent event){   
   }

//    getters e setters

    public Item getItem() {
        if (item ==null){
            item = new Item();
        }
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
  
    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Categoria getCategoria() {
        if (categoria ==null){
            categoria = new Categoria();
        }
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public DataModel<Item> getModelItens() {
        return modelItens;
    }

    public void setModelItens(DataModel<Item> modelItens) {
        this.modelItens = modelItens;
    }

    public List<SelectItem> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(List<SelectItem> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public ItemRepositorio getItemRepositorio() {
        return itemRepositorio;
    }

    public void setItemRepositorio(ItemRepositorio itemRepositorio) {
        this.itemRepositorio = itemRepositorio;
    }

    public CategoriaRepositorio getCategoriaRepositorio() {
        return categoriaRepositorio;
    }

    public void setCategoriaRepositorio(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }
    
}
