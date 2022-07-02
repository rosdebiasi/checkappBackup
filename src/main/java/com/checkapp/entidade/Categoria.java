package com.checkapp.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)    
    private String nome; // exemplo: circulação. Isso já estaria pré cadastrado. Nessa parte não mexeria
    
    private String nomeEspecifico; // exemplo: circulação 1, circulação 2, rampa 1, rampa 2. Aqui usuário do sistema mexe.
    
    //desmarcar depois- para teste inicial
    
    //backup`- duvida - antigo diagrama de classes
//    @OneToOne 
//    @JoinColumn(name="id_item")
//    private Item item;
    
    //esse que provavelmente vou usar
    @OneToMany (mappedBy= "categoria",cascade = CascadeType.ALL) //qqer coisa tirar o cascade
    private List<Item> itens; 
    
    public Categoria() {
    }

    public Categoria(String nome, String nomeEspecifico) {
        this.nome = nome;
        this.nomeEspecifico = nomeEspecifico;
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

    public String getNomeEspecifico() {
        return nomeEspecifico;
    }

    public void setNomeEspecifico(String nomeEspecifico) {
        this.nomeEspecifico = nomeEspecifico;
    }

    //one to many - esse que vou usar
    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    
//one to one - backup
//    public Item getItem() {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
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
        Categoria other = (Categoria) obj;
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
