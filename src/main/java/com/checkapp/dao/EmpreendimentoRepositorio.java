package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkapp.entidade.Empreendimento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmpreendimentoRepositorio extends JpaRepository<Empreendimento, Long>{
       
    @Query("select e from Empreendimento e Where e.nome like %:nome%")
    List<Empreendimento> findByNome(String nome);
    
    @Query("select e from Empreendimento e Where e.funcao like %:funcao%")
    List<Empreendimento> findByFuncao(String funcao);
    
    //@Query("select e from Empreendimento l join fetch l.endereco e Where l.nome like %:nome%")
    
    //@Query("select e from Empreendimento l join fetch l.endereco e Where l.nome like %:nome%")
    
}
