package com.checkapp.controle;

import com.checkapp.dao.InspecaoRepositorio;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Empreendimento;
import java.util.List;
import javax.faces.model.DataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import com.checkapp.entidade.Inspecao;
import com.checkapp.dao.EmpreendimentoRepositorio;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

@Component(value = "relatorioC")
@Scope("request")
public class RelatorioControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Inspecao inspecaoSelecionada;
    private List<Inspecao> inspecoes;

    private DataModel<Inspecao> modelInspecoes;
    private int aba;

    private Long pesquisaEmpreendimentoId;
    private Date pesquisaDataInicial;
    private Date pesquisaDataFinal;

    private Empreendimento lugar;
    private DataModel<Empreendimento> modelLugares;
    
    private List<Empreendimento> lugares;

    private List<Empreendimento> funcoes;
    private Empreendimento funcao;
    
    @Autowired
    private EmpreendimentoRepositorio lugarRepositorio;

    @Autowired
    private InspecaoRepositorio inspecaoRepositorio;

    private long numeroInspecoes;

    @PostConstruct
    public void iniciar() {
        aba = 0;
        inspecaoSelecionada = new Inspecao();
        pesquisaDataInicial = new Date();
        pesquisaDataFinal = new Date();
        inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
        lugares = lugarRepositorio.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        //funcoes = lugarRepositorio.findAll(Sort.by(Sort.Direction.ASC, "funcao"));
        modelLugares = new ListDataModel<>(lugarRepositorio.findAll()); //copiei de empreendimento mas não deu certo
        numeroInspecoes = inspecaoRepositorio.count();
    }

    public void pesquisarPorEmpreeendimento() {
        if (pesquisaEmpreendimentoId == -1) {
            this.inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
        } else {
            this.inspecoes = inspecaoRepositorio.findByEmpreendimento(pesquisaEmpreendimentoId);
        }
    }
    
    //isso aqui não deu certo pois a Função foi colocada manualmente no combo Box
//    public void pesquisarPorFuncao() {
//        if (pesquisaEmpreendimentoId == -1) {
//            this.inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
//        } else {
//            this.inspecoes = inspecaoRepositorio.findByEmpreendimento(pesquisaEmpreendimentoId);
//        }
//    }
    
    //se der- senão retirar- não deu para fazer com combo pois o combo das funções foi colocado manualmente - não é urgente- se der depois de login
    public void pesquisarPorFuncao() {
        List<Empreendimento> locais = lugarRepositorio.findByFuncao(lugar.getFuncao());
        modelLugares = new ListDataModel<>(locais);
        lugar.setFuncao(null);
    }

    public void pesquisarPorFaixaDeData() {
        if ((pesquisaDataInicial).after(pesquisaDataFinal)) {
            Mensagem.mensagemErroPesquisaData(" a data inicial deve ser anterior à data final");
            return;
        }

        Calendar calendarioInicio = Calendar.getInstance();
        calendarioInicio.setTime(pesquisaDataInicial);
        calendarioInicio.set(Calendar.MILLISECOND, 0);
        calendarioInicio.set(Calendar.SECOND, 0);
        calendarioInicio.set(Calendar.MINUTE, 0);
        calendarioInicio.set(Calendar.HOUR_OF_DAY, 0);

        Calendar calendarioFim = Calendar.getInstance();
        calendarioFim.setTime(pesquisaDataFinal);
        calendarioFim.set(Calendar.MILLISECOND, 999);
        calendarioFim.set(Calendar.SECOND, 59);
        calendarioFim.set(Calendar.MINUTE, 59);
        calendarioFim.set(Calendar.HOUR_OF_DAY, 23);

        inspecoes = inspecaoRepositorio.pesquisarInspecaoPorFaixaDeData(calendarioInicio.getTime(), calendarioFim.getTime());
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }

    //não vai funcionar - gerar um lancamento dao - talvez outra classe para relatório 
    public void gerarRelatorio() {
//        inspecao = modelInspecoes.getRowData();
//        empreendimento = inspecao.getEmpreendimento();
//
//        for (List<Avaliacao> avaliacaoPorCategoria : listaAvaliacoesPorCategoria.values()) {
//            for (Avaliacao avaliacao : avaliacaoPorCategoria) {
//                avaliacao.getInspecao();
//                avaliacaoRepositorio.getById(serialVersionUID);
//            }
//        }
    }

    public Inspecao getInspecaoSelecionada() {
        return inspecaoSelecionada;
    }

    public void setInspecaoSelecionada(Inspecao inspecao) {
        this.inspecaoSelecionada = inspecao;
    }

    public List<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }

    public DataModel<Inspecao> getModelInspecoes() {
        return modelInspecoes;
    }

    public void setModelInspecoes(DataModel<Inspecao> modelInspecoes) {
        this.modelInspecoes = modelInspecoes;
    }

    public List<Empreendimento> getLugares() {
        return lugares;
    }

    public void setLugares(List<Empreendimento> lugares) {
        this.lugares = lugares;
    }

    public Long getPesquisaEmpreendimentoId() {
        return pesquisaEmpreendimentoId;
    }

    public void setPesquisaEmpreendimentoId(Long pesquisaEmpreendimentoId) {
        this.pesquisaEmpreendimentoId = pesquisaEmpreendimentoId;
    }

    public Date getPesquisaDataInicial() {
        return pesquisaDataInicial;
    }

    public void setPesquisaDataInicial(Date pesquisaDataInicial) {
        this.pesquisaDataInicial = pesquisaDataInicial;
    }

    public Date getPesquisaDataFinal() {
        return pesquisaDataFinal;
    }

    public void setPesquisaDataFinal(Date pesquisaDataFinal) {
        this.pesquisaDataFinal = pesquisaDataFinal;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public long getNumeroInspecoes() {
        return numeroInspecoes;
    }

    public void setNumeroInspecoes(long numeroInspecoes) {
        this.numeroInspecoes = numeroInspecoes;
    }

    public Empreendimento getLugar() {
        return lugar;
    }

    public void setLugar(Empreendimento lugar) {
        this.lugar = lugar;
    }

    public List<Empreendimento> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Empreendimento> funcoes) {
        this.funcoes = funcoes;
    }
    
    

}
