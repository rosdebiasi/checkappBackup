/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

import java.io.Serializable;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;


import com.checkapp.entidade.Cliente;
import com.checkapp.dao.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

///**
// *
// * @author JavaRevolutions
// */
@Named(value = "clienteC")
@ViewScoped
public class ClienteControle implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Cliente cliente;
    private DataModel<Cliente> modelClientes;
    private int aba;
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    public List<Cliente> pesquisarTodo(){
        return clienteRepositorio.findAll(); 
    }

    public void pesquisarPorNome(){      
        List<Cliente> clientes = clienteRepositorio.findByNomeContaining(cliente.getNome());
        modelClientes = new ListDataModel<>(clientes);
        cliente.setNome(null);
    }

////     //adicionado --será que funciona???
////    public void pesquisarPorId(){      
////        Optional<Cliente> clientes = clienteRepositorio.findById(cliente.getId());
////    }  
////    
////    //adicionado
////    public void pesquisarPorCpf(){      
////        List<Cliente> clientes = clienteRepositorio.findByCpfContaining(cliente.getCpf());
////        modelClientes = new ListDataModel<>(clientes);
////    }
//
    public void salvar(){
        try {
            clienteRepositorio.save(cliente);
            Mensagem.mensagemSucesso(cliente.getNome());
            cliente = null;
            aba =0;
        } catch (Exception e) {
            Mensagem.mensagemErro(cliente.getNome());
        }
    }
    
    public void excluir(){
        try {
            cliente = modelClientes.getRowData();
            clienteRepositorio.delete(cliente);
            Mensagem.mensagemSucessoExcluir(cliente.getNome());
            cliente = null;
            modelClientes = null;
        } catch (Exception e) {
            Mensagem.mensagemErroExcluir(cliente.getNome());
        }
    }
    

//    public void excluir (){
//        if(!clienteRepositorio.existsById(cliente.getId())){
//            Mensagem.mensagemErroExcluir(cliente.getNome());
//        } else {
//        clienteRepositorio.deleteById(cliente.getId());
//        Mensagem.mensagemSucessoExcluir(cliente.getNome());
//        } 
//    }
//    
//   public void atualizar(){
//        if(!clienteRepositorio.existsById(cliente.getId())){
//            Mensagem.mensagemErroAlterar(cliente.getNome());
//        } else {
//        cliente.setId(cliente.getId());
//        clienteRepositorio.save(cliente);
//        Mensagem.mensagemSucessoAlterar(cliente.getNome());
//        } 
//   }
    
   public void prepararAlterar(){
       cliente = modelClientes.getRowData();
       aba=1;
       modelClientes = null;
   }
   
   public void onTabChange(TabChangeEvent event){   
   }
   
   public void onTabClose(TabCloseEvent event){   
   }
    
//    public void atualizar(){
//        try {
//            cliente.getNome();
//            cliente.getCpf();
//            cliente.getEmail();
//        } catch (Exception e) {
//            Mensagem.mensagemErro(cliente.getNome());
//        } finally {
//            clienteRepositorio.save(cliente);
//        }
//    }
    
//    public Cliente excluir (long id){
//        if(!clienteRepositorio.existsById(id)){
//            Mensagem.mensagemErroExcluir(cliente.getNome());
//        } else {
//        clienteRepositorio.deleteById(id);
//        Mensagem.mensagemSucessoExcluir(cliente.getNome());
//        } 
//    }
    
    
    
//    getters e setters
    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DataModel<Cliente> getModelClientes() {
        return modelClientes;
    }   

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
