package com.checkapp.controle;

import com.checkapp.dao.AvaliacaoRepositorio;
import com.checkapp.dao.CategoriaRepositorio;
import com.checkapp.dao.InspecaoRepositorio;
import com.checkapp.dao.ItemRepositorio;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Empreendimento;
import java.util.List;
import java.util.Optional;
import javax.faces.model.DataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import com.checkapp.entidade.Categoria;
import com.checkapp.entidade.Inspecao;
import com.checkapp.entidade.Item;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import com.checkapp.dao.EmpreendimentoRepositorio;
import com.checkapp.entidade.Avaliacao;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

@Component(value = "inspecaoC")
@Scope("view")
public class InspecaoControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Inspecao inspecao;
    private DataModel<Inspecao> modelInspecoes;
    private int aba;

    private Item item;
    private Empreendimento empreendimento;
    private Categoria categoria;

    private List<Categoria> categorias;
    private List<SelectItem> comboEmpreendimentos;
    private List<Categoria> listaDeCategoria;
    private Map<String, List<Avaliacao>> listaAvaliacoesPorCategoria;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private EmpreendimentoRepositorio lugarRepositorio;

    @Autowired
    private InspecaoRepositorio inspecaoRepositorio;

    @Autowired
    private AvaliacaoRepositorio avaliacaoRepositorio;

    //para pesquisar no banco antes de carregar a tela- como um construtor de uma classe de Entidade, mas tem em todas as classes
    @PostConstruct
    public void iniciar() {
        aba = 0;
        inspecao = new Inspecao();
        empreendimento = new Empreendimento();
        modelInspecoes = null;
        carregarComboBoxEmpreendimentos();
        listaDeCategoria = categoriaRepositorio.carregarTodasComItens();

        List<Avaliacao> avaliacoes = new ArrayList<>();
        for (Categoria cat : listaDeCategoria) {
            for (Item it : cat.getItens()) {
                //System.out.println("nome: " + iten.getNome());
//                Avaliacao avaliacao = new Avaliacao();
//                avaliacao.setItem(it);
//                System.out.println("resposta: " + iten.getAvaliacao().getResposta());
//                avaliacoes.add(avaliacao);

//                inspecao.addAvaliacao(it);
                avaliacoes.add(new Avaliacao(inspecao, it));
            }
        }

        listaAvaliacoesPorCategoria = avaliacoes
                .stream()
                .collect(Collectors.groupingBy(av -> av.getItem().getCategoria().getNome()));

    }

       private void carregarComboBoxEmpreendimentos() {
        List<Empreendimento> lugares = lugarRepositorio.findAll();
        comboEmpreendimentos = new ArrayList<>();
        for (Empreendimento lug : lugares) {
            comboEmpreendimentos.add(new SelectItem(lug.getId(), lug.getNome()));
        }
    }

    private void carregarListaItem() {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        listaDeCategoria = new ArrayList<>();
        for (Categoria it : categorias) {
            listaDeCategoria.add(it);
        }
    }

    public List<Avaliacao> getListaAvaliacao(String resposta) {
        List<Avaliacao> avaliacoes = avaliacaoRepositorio.procurarPorResposta(resposta);
        for (Avaliacao avaliac : avaliacoes) {
            System.out.println("XXXX" + avaliac.getResposta());
        }
        System.out.println("");
        return avaliacoes;
    }

//    public List<Item> getListaItemCategoria(String nome) {
//        List<Item> itens = itemRepositorio.findByNomeCategoria(nome);
//        for (Item iten : itens) {
//            System.out.println("X X X X X " + iten.getAvaliacao().getResposta());
//        }
//        System.out.println("");
//        return itens;
//    }
//    public void trocarDeTela() {
//        try {
//            String url = "http://localhost:8080/CheckApp/novo_2.jr";
//        } catch (Exception e) {
//            System.out.println("Erro ao trocar de tela " + e.getMessage());
//        }
//    }
    public void salvar() {
        try {
            Empreendimento emp = lugarRepositorio.getById(empreendimento.getId());
            
            inspecao.setEmpreendimento(emp);
            inspecao.setDataEhora(GregorianCalendar.getInstance().getTime());
            inspecaoRepositorio.save(inspecao);

            for (List<Avaliacao> avaliacaoPorCategoria : listaAvaliacoesPorCategoria.values()) {
                for (Avaliacao avaliacao : avaliacaoPorCategoria) {
                    avaliacao.setInspecao(inspecao);
                    avaliacaoRepositorio.save(avaliacao);
                }
            }
            
            Mensagem.mensagemSucesso(inspecao.getNome() + " em " +  inspecao.getDataEhora());

            iniciar();
        } catch (Exception e) {
            System.err.println("Erro ao salvar " + e.getMessage());
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return inspecao.getAvaliacoes();
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        inspecao.setAvaliacoes(avaliacoes);
    }

    //não vai funcionar - gerar um lancamento dao - talvez outra classe para relatório 
    public void gerarRelatorio() {
        inspecao = modelInspecoes.getRowData();
        empreendimento = inspecao.getEmpreendimento();

        for (List<Avaliacao> avaliacaoPorCategoria : listaAvaliacoesPorCategoria.values()) {
            for (Avaliacao avaliacao : avaliacaoPorCategoria) {
                avaliacao.getInspecao();
                avaliacaoRepositorio.getById(serialVersionUID);
            }
        }
    }

//    getters e setters
//    public Avaliacao getAvaliacao() {
//        return avaliacao;
//    }
//
//    public void setAvaliacao(Avaliacao avaliacao) {
//        this.avaliacao = avaliacao;
//    }
//
//    public List<Avaliacao> getAvaliacoes() {
//        return avaliacoes;
//    }
//
//    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//        this.avaliacoes = avaliacoes;
//    }
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Categoria> getListaDeCategoria() {
        return listaDeCategoria;
    }

    public void setListaDeCategoria(List<Categoria> listaDeCategoria) {
        this.listaDeCategoria = listaDeCategoria;
    }

    public List<SelectItem> getComboEmpreendimentos() {
        return comboEmpreendimentos;
    }

    public void setComboEmpreendimentos(List<SelectItem> comboEmpreendimentos) {
        this.comboEmpreendimentos = comboEmpreendimentos;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public DataModel<Inspecao> getModelInspecoes() {
        return modelInspecoes;
    }

    public void setModelInspecoes(DataModel<Inspecao> modelInspecoes) {
        this.modelInspecoes = modelInspecoes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public InspecaoRepositorio getInspecaoRepositorio() {
        return inspecaoRepositorio;
    }

    public void setInspecaoRepositorio(InspecaoRepositorio inspecaoRepositorio) {
        this.inspecaoRepositorio = inspecaoRepositorio;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreencimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public Map<String, List<Avaliacao>> getListaAvaliacoesPorCategoria() {
        return listaAvaliacoesPorCategoria;
    }

    public void setListaAvaliacoesPorCategoria(Map<String, List<Avaliacao>> listaAvaliacoesPorCategoria) {
        this.listaAvaliacoesPorCategoria = listaAvaliacoesPorCategoria;
    }

}

//comentados  
//    public void excluir() {
//        try {
//            inspecao = modelInspecoes.getRowData();
//            inspecaoRepositorio.delete(inspecao);
//            Mensagem.mensagemSucessoExcluir(inspecao.getNome());
//            inspecao = null;
//            lugar = null;
//            categorias = null;
//            modelInspecoes = null;
//        } catch (Exception e) {
//            Mensagem.mensagemErroExcluir(inspecao.getNome());
//        }
//    }
//    public void prepararAlterar() {
//        inspecao = modelInspecoes.getRowData();
//        lugar = inspecao.getEmpreendimento();
//        //categorias = (List<Categoria>) inspecao.getCategorias();
//        modelInspecoes = null;
//        aba = 1;
//    }
//    public void adicionarItem(long id) {
//        try {
//            System.out.println(id);
//            Item temp_item = itemRepositorio.getById(id);
//            temp_itens.add(temp_item);
//
//        } catch (Exception e) {
//            Mensagem.mensagemErro("não foi possivel salvar");
//        }
//    }
//    public List<SelectItem> getComboItem() {
//        return comboItem;
//    }
//
//    public void setComboItem(List<SelectItem> comboItem) {
//        this.comboItem = comboItem;
//    }
//    private void carregarListaItem2(){
//        List<Item> itens = itemRepositorio.findAll();
//        comboItem = new ArrayList<>();
//        itens.add(item);
//        for (Item it : itens) {
//            comboItem.getClass();
//        }
//    }
//    @PostConstruct
//    public void init(){
//        List<Avaliacao> avaliacoes = null;
//        avaliacoes.toString().compareTo("sim");
//        avaliacoes.toString().compareTo("nao");
//        avaliacoes.toString().compareTo("N/A");
//        avaliac = new ArrayList<String>();
//        avaliac.add("Sim");
//        avaliac.add("Não");
//        avaliac.add("N/A");
//    }
//    private void carregarComboBoxItem() {  
//        inspecao.getAvaliacoes();
//        Avaliacao ava = new Avaliacao();
//        ava.getItens();
//        List<Item> itens = itemRepositorio.findAll();
//        comboItem = new ArrayList<>();
//        for (Item iti : itens) {
//            comboItem.add(new SelectItem(iti.getId(), iti.getNome()));
//        }
//    }
//}
