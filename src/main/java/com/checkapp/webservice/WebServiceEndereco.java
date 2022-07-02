/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.webservice;

import com.checkapp.entidade.Empreendimento;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 *
 * @author rosan
 */
public class WebServiceEndereco {

    private Client client;
    private WebResource webResource;

    public WebServiceEndereco() {
        ClientConfig clientConfig = new DefaultClientConfig(GensonProvider.class);
        client = Client.create(clientConfig);
        //Utilizado para imprimir as operacoes no console
        client.addFilter(new LoggingFilter(System.out));
        webResource = client.resource("https://viacep.com.br/ws/");
    }
    
    public Empreendimento pesquisarCep (String cep){
        return webResource.path(cep).path("/json").get(Empreendimento.class);
    }
    
    // para teste
    
//    public static void main(String[]args){
//        WebServiceEndereco webService = new  WebServiceEndereco();
//        Empreendimento endereco = webService.pesquisarCep("88037000");
//        
//        if(endereco != null){
//            System.out.println("Logradouro: " + endereco.getLogradouro());
//            System.out.println("Bairro: " + endereco.getBairro());
//            System.out.println("Cidade: " + endereco.getLocalidade());
//        }
//    }


}
