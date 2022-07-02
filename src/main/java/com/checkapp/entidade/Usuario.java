package com.checkapp.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    private String login;

    private String email;

    @Column(nullable = false)
    private String senha;

    //@Column(nullable = false)
    //private boolean enable;
    //observação: no banco de dados trocar o tipo de variável para TINYINT(1), senão não salva o usuário no banco
    
    @Temporal(TemporalType.DATE)
    private Date ultimoAcesso;

    //desmarcar depois- para teste inicial
    @OneToMany(mappedBy = "usuario")
    private List<Inspecao> inspecao;

//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
//    private Inspecao inspecao; --uma ou várias??? fiquei em dúvida
    public Usuario() {
    }

    public Usuario(String login, String email, String senha) {
        this.login = login;
        this.email = email;
        this.senha = senha;
        //this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public boolean isEnable() {
//        return enable;
//    }
//
//    public void setEnable(boolean enable) {
//        this.enable = enable;
//    }

    public List<Inspecao> getInspecao() {
        return inspecao;
    }

    public void setInspecao(List<Inspecao> inspecao) {
        this.inspecao = inspecao;
    }

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
        Usuario other = (Usuario) obj;
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
