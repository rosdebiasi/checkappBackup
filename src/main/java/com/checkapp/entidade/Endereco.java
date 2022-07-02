//package com.checkapp.entidade;
//
//import java.io.Serializable;
//
//import javax.persistence.*;
////
//
//
//public class Endereco implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String logradouro;
//
//    @Column(nullable = false, length = 5)
//    private String numero;
//
//    @Column(nullable = false)
//    private String cep;
//
//    @Column(nullable = false)
//    private String bairro;
//
//    @Column(nullable = false)
//    private String localidade;
//
//    @Column(nullable = false)
//    private String uf;
//
//    @Column(nullable = false)
//    private String complemento;
//
//    @Column
//    private String gia;
//
//    @Column
//    private String ibge;
//
//    @Column
//    private String ddd;
//
//    @Column
//    private String siafe;
//
//    @OneToOne
//    @JoinColumn(name = "id_lugar")
//    private Empreendimento lugar;
//
//    public Endereco() {
//    }
//
//    public Endereco(Long id, String logradouro, String numero, String cep, String bairro, String localidade, String uf, String complemento, String gia, String ibge, String ddd, String siafe) {
//        this.id = id;
//        this.logradouro = logradouro;
//        this.numero = numero;
//        this.cep = cep;
//        this.bairro = bairro;
//        this.localidade = localidade;
//        this.uf = uf;
//        this.complemento = complemento;
//        this.gia = gia;
//        this.ibge = ibge;
//        this.ddd = ddd;
//        this.siafe = siafe;
//    }
//
//    public Empreendimento getLugar() {
//        return lugar;
//    }
//
//    public void setLugar(Empreendimento lugar) {
//        this.lugar = lugar;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLogradouro() {
//        return logradouro;
//    }
//
//    public void setLogradouro(String logradouro) {
//        this.logradouro = logradouro;
//    }
//
//    public String getNumero() {
//        return numero;
//    }
//
//    public void setNumero(String numero) {
//        this.numero = numero;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public void setCep(String cep) {
//        this.cep = cep;
//    }
//
//    public String getBairro() {
//        return bairro;
//    }
//
//    public void setBairro(String bairro) {
//        this.bairro = bairro;
//    }
//
//    public String getLocalidade() {
//        return localidade;
//    }
//
//    public void setLocalidade(String localidade) {
//        this.localidade = localidade;
//    }
//
//    public String getUf() {
//        return uf;
//    }
//
//    public void setUf(String uf) {
//        this.uf = uf;
//    }
//
//    public String getComplemento() {
//        return complemento;
//    }
//
//    public void setComplemento(String complemento) {
//        this.complemento = complemento;
//    }
//
//    public String getGia() {
//        return gia;
//    }
//
//    public void setGia(String gia) {
//        this.gia = gia;
//    }
//
//    public String getIbge() {
//        return ibge;
//    }
//
//    public void setIbge(String ibge) {
//        this.ibge = ibge;
//    }
//
//    public String getDdd() {
//        return ddd;
//    }
//
//    public void setDdd(String ddd) {
//        this.ddd = ddd;
//    }
//
//    public String getSiafe() {
//        return siafe;
//    }
//
//    public void setSiafe(String siafe) {
//        this.siafe = siafe;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Endereco other = (Endereco) obj;
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
//
//}
