package com.checkapp.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "inspecao")
public class Inspecao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_empreendimento")
    private Empreendimento empreendimento;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataEhora;

    private String observacao;
    
    private String responsavelTecnico;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicioPesquisa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinalPesquisa;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(
            mappedBy = "inspecao",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<Avaliacao> avaliacoes = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "inspecao_item",
//            joinColumns = @JoinColumn(name = "inspecao_codigo"),
//            inverseJoinColumns = @JoinColumn(name = "item_codigo"))
//    @Column(name = "resposta")
//    private Set<Item> itens = new HashSet<>();    
//    @OneToOne 
//    @JoinColumn(name="id_usuario")
//    private Usuario usuario;
    
    public Inspecao() {
    }

    public Inspecao(Date dataEhora, String observacao, String responsavelTecnico) {
        this.dataEhora = dataEhora;
        this.observacao = observacao;
        this.responsavelTecnico = responsavelTecnico;
    }

    public void addAvaliacao(Item item) {
        Avaliacao avaliacao = new Avaliacao(this, item);
        avaliacoes.add(avaliacao);
//        item.getAvaliacoes().add(avaliacao);
    }

    public void removeAvaliacao(Item item) {
        for (Iterator<Avaliacao> iterator = avaliacoes.iterator();
                iterator.hasNext();) {
            Avaliacao avaliacao = iterator.next();

            if (avaliacao.getInspecao().equals(this)
                    && avaliacao.getItem().equals(item)) {
                iterator.remove();
//                avaliacao.getItem().getAvaliacoes().remove(avaliacao);
                avaliacao.setInspecao(null);
                avaliacao.setItem(null);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEhora() {
        return dataEhora;
    }

    public void setDataEhora(Date dataEhora) {
        this.dataEhora = dataEhora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getResponsavelTecnico() {
        return responsavelTecnico;
    }

    public void setResponsavelTecnico(String responsavelTecnico) {
        this.responsavelTecnico = responsavelTecnico;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public String getNome() {
        return getEmpreendimento().getNome();
    }

    public void setNome(String nome) {
        getEmpreendimento().setNome(nome);
    }

    public Date getDataInicioPesquisa() {
        return dataInicioPesquisa;
    }

    public void setDataInicioPesquisa(Date dataInicioPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
    }

    public Date getDataFinalPesquisa() {
        return dataFinalPesquisa;
    }

    public void setDataFinalPesquisa(Date dataFinalPesquisa) {
        this.dataFinalPesquisa = dataFinalPesquisa;
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
        Inspecao other = (Inspecao) obj;
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
