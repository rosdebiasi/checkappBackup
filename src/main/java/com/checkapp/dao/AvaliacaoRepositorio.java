package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import com.checkapp.entidade.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AvaliacaoRepositorio extends JpaRepository<Avaliacao, Long>{
    
    @Query("from Avaliacao a Where a.resposta = :resposta")
    List<Avaliacao> procurarPorResposta(String resposta);

//    @Query("from Item i Where i.nome like %:nome%")
//    List<Item> findByNomeContaining(String nome);
//    
//    @Query("from Item i join fetch i.categoria Where nome like %:nome%")
//    List<Item> procurarItemPorCategoria(String nome);
//    
//    @Query("from Item i join fetch i.categoria Where nome like %:nome%")
//    List<Item> procurarItemPorCategoria(String nome);
//    
//    @Query("select i from Item i join fetch i.categoria c Where i.nome like %:nome%")
//    List<Item> findByNome(String nome);
//    
//    @Query("select i from Item i join fetch i.categoria c Where c.nome like %:nome%")
//    List<Item> findByNomeCategoria(String nome);
//       
//    @Query("select max(i.numero) from Item i join i.categoria c where c.id = :idCategoria")
//    Integer pesquisarUltimoNumeroItem(Long idCategoria);
//    
}
