package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkapp.entidade.Item;
import com.checkapp.entidade.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    @Query("from Usuario u Where u.login like %:login%")
    List<Usuario> findByNomeContaining(String login);
    
    @Query("from Usuario u Where u.email like %:email%")
    List<Usuario> findByEmailContaining(String email);
    
    @Query("from Usuario u Where u.login = :login and u.senha = :senha")
    Usuario logar (String login, String senha);
    
//    @Query("from Item i join fetch i.categoria Where nome like %:nome%")
//    List<Item> procurarItemPorCategoria(String nome);
    
//    @Query("from Usuario u join fetch u.inspecao Where nome like %:nome%")
//    List<Item> procurarUsuarioPorInspecao(String nome);
//    
//    @Query("select i from Item i join fetch i.categoria c Where i.nome like %:nome%")
//    List<Item> findByNome(String nome);
//    
//    @Query("select i from Item i join fetch i.categoria c Where c.nome like %:nome%")
//    List<Item> findByNomeCategoria(String nome);
       
//    @Query("select max(i.numero) from Item i join i.categoria c where c.id = :idCategoria")
//    Integer pesquisarUltimoNumeroItem(Long idCategoria);
    
}
