package com.checkapp.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "empreendimento")
public class Empreendimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private String nome;
    
    @Column(nullable = false) 
    private String funcao; //combobox
    
    private String responsavel;
    
    private String telefoneResponsavel;
    
    private String pavimento; 
    
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false, length = 5)
    private String numero;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String localidade;

    @Column(nullable = false)
    private String uf;

    @Column(nullable = false)
    private String complemento;

    @Column
    private String gia;

    @Column
    private String ibge;

    @Column
    private String ddd;

    @Column
    private String siafe;
  
    @OneToMany (mappedBy = "empreendimento",cascade = CascadeType.ALL) //qqer coisa tirar o cascade
    private List <Inspecao> inspecoes;

    //desmarcar depois- para teste inicial
    
//    @ManyToOne 
//    @JoinColumn (name = "id_usuario")
//    private Usuario usuario;
//    
//    @OneToMany (mappedBy= "local")
//    private List<Item> itens;

    public Empreendimento() {
    }

    public Empreendimento(String nome, String funcao, String responsavel, String telefoneResponsavel, String pavimento, String logradouro, String numero, String cep, String bairro, String localidade, String uf, String complemento, String gia, String ibge, String ddd, String siafe) {
        this.nome = nome;
        this.funcao = funcao;
        this.responsavel = responsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.pavimento = pavimento;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.complemento = complemento;
        this.gia = gia;
        this.ibge = ibge;
        this.ddd = ddd;
        this.siafe = siafe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getPavimento() {
        return pavimento;
    }

    public void setPavimento(String pavimento) {
        this.pavimento = pavimento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafe() {
        return siafe;
    }

    public void setSiafe(String siafe) {
        this.siafe = siafe;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public List<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }
     
//    public List<Inspecao> getInspecoes() {
//        return inspecoes;
//    }
//
//    public void setInspecoes(List<Inspecao> inspecoes) {
//        this.inspecoes = inspecoes;
//    }
    
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

//    public List<Item> getItens() {
//        return itens;
//    }
//
//    public void setItens(List<Item> itens) {
//        this.itens = itens;
//    }
      
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Empreendimento other = (Empreendimento) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
