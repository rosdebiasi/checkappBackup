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

import com.checkapp.dao.UsuarioRepositorio;
import com.checkapp.entidade.Usuario;
import com.checkapp.webservice.Criptografia;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;

///**
// *
// * @author JavaRevolutions
// */
@Named(value = "usuarioC")
@Scope("session")
public class UsuarioControle implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Usuario usuario;
    private DataModel<Usuario> modelUsuarios;
    private int aba;
    
    private String campoLogin;
    private String campoSenha;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
//    public List<Usuario> pesquisarTodo(){
//        return usuarioRepositorio.findAll(); 
//    }

//    public void pesquisarPorNome(){      
//        List<Usuario> usuarios = usuarioRepositorio.findByNomeContaining(usuario.getLogin());
//        modelUsuarios = new ListDataModel<>(usuarios);
//        usuario.setLogin(null);
//    }

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
    
        public void logar() {
        try {
            usuario = usuarioRepositorio.logar(campoLogin, new Criptografia().encript(campoSenha));
            if (usuario == null) {
                throw new HibernateException("erro, nenhum resultado foi encontrado no login");
            }
            
            Mensagem.mensagemSucesso(usuario.getLogin());

            FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.faces"); //mapeamento/falta front end
            campoLogin = null;
            campoSenha = null;
        } catch (IOException | NoSuchAlgorithmException | HibernateException e) {
             Mensagem.mensagemErro(usuario.getLogin());
        } finally {
            if (usuario == null) {
                usuario = new Usuario();
            }
        }     
    }
  
    public void salvar(){
        try {
            usuarioRepositorio.save(usuario);
            Mensagem.mensagemSucesso(usuario.getLogin());
//            usuario = null;
//            aba =0;
        } catch (Exception e) {
            Mensagem.mensagemErro(usuario.getLogin());
        }
    }
    
//    public void excluir(){
//        try {
//            usuario = modelUsuarios.getRowData();
//            usuarioRepositorio.delete(usuario);
//            Mensagem.mensagemSucessoExcluir(usuario.getLogin());
//            usuario = null;
//            modelUsuarios = null;
//        } catch (Exception e) {
//            Mensagem.mensagemErroExcluir(usuario.getLogin());
//        }
//    }
    

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
       usuario = modelUsuarios.getRowData();
       //aba=1;
       modelUsuarios = null;
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
   
    public Usuario getUsuario() {
         if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DataModel<Usuario> getModelUsuarios() {
        return modelUsuarios;
    }
   
    public void setModelUsuarios(DataModel<Usuario> modelUsuarios) {
        this.modelUsuarios = modelUsuarios;
    }

//    public int getAba() {
//        return aba;
//    }
//
//    public void setAba(int aba) {
//        this.aba = aba;
//    }

}
