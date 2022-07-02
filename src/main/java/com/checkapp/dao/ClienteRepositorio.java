package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkapp.entidade.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
    
    @Query("from Cliente c Where c.nome like %:nome%")
    List<Cliente> findByNomeContaining(String nome);
    
    //método para procurar por cpf --está correto a query?? outra pergunta quero salvar o cpf para ninguém ver no banco? usar hash. Posso fazer um comando lá no banco que fica??
    @Query("from Cliente c Where c.cpf = :cpf")
    List<Cliente> findByCpfContaining(String cpf);
    
    
}
