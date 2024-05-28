package br.com.pos.delivey.repository;

import br.com.pos.delivey.model.Categoria;
import br.com.pos.delivey.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    List<Produto> findProdutoByCategoria(Categoria categoria);

}
