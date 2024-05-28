package br.com.pos.delivey.service;

import br.com.pos.delivey.model.Categoria;
import br.com.pos.delivey.model.Produto;
import br.com.pos.delivey.repository.ProdutoRepository;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarPorCategoria(Categoria categoria) {
        return produtoRepository.findProdutoByCategoria(categoria);
    }
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}

