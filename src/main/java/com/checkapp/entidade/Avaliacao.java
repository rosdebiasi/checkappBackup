package com.checkapp.entidade;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "Avaliacao")
@Table(name = "avaliacao")
public class Avaliacao {

    @EmbeddedId
    private AvaliacaoID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inspecaoId")
    private Inspecao inspecao;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

//    @ManyToOne
//    @JoinColumn(name = "id_item")
//    private Item item;
//
//    @ManyToOne
//    @JoinColumn(name = "id_inspecao")
//    private Inspecao inspecao;
    @Column
    private String resposta;

    public Avaliacao() {
    }

    public Avaliacao(Inspecao inspecao, Item item) {
        this.inspecao = inspecao;
        this.item = item;
        this.id = new AvaliacaoID(inspecao.getId(), item.getId());
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

//    public String getObservacao() {
//        return observacao;
//    }
//
//    public void setObservacao(String observacao) {
//        this.observacao = observacao;
//    }
    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.inspecao);
        hash = 19 * hash + Objects.hashCode(this.item);
        return hash;
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
        final Avaliacao other = (Avaliacao) obj;
        if (!Objects.equals(this.inspecao, other.inspecao)) {
            return false;
        }
        return Objects.equals(this.item, other.item);
    }

}
