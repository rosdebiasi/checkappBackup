package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkapp.entidade.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
    
    @Query("select distinct(c) from Categoria c join fetch c.itens")
    List<Categoria>carregarTodasComItens();
    
    @Query("from Categoria c Where c.nome like %:nome%")
    List<Categoria> findByNomeContaining(String nome);
    
    @Query("from Categoria c Where c.nomeEspecifico like %:nomeEspecifico%")
    List<Categoria> findByNomeEspecificoContaining(String nomeEspecifico);
  
}
