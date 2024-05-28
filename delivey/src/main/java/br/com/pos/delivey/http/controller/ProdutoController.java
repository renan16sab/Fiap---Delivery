package br.com.pos.delivey.http.controller;

import br.com.pos.delivey.model.Categoria;
import br.com.pos.delivey.model.Produto;
import br.com.pos.delivey.repository.ProdutoRepository;
import br.com.pos.delivey.service.ProdutoService;
import com.sun.jdi.VoidType;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(description = "Insere o produto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salva(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @Operation(description = "Busca o produto")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> listarProduto() {
        return produtoService.buscarTodos();
    }

    @Operation(description = "Busca o produto pelo Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto buscarProdutoPorId(@PathVariable("id") Long id) {
        return produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @Operation(description = "Busca o produto pela categoria")
    @GetMapping("/categoria/{categoria}")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> buscarProdutoPorCategoria(@PathVariable("categoria") Long categoria) {
        Categoria categoria1 = new Categoria();
        categoria1.setId(categoria);
              return  produtoService.buscarPorCategoria(categoria1);
    }

    @Operation(description = "Deleta o produto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id") Long id) {
        produtoService.buscarPorId(id)
                .map(produto -> {
                    produtoService.excluir(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @Operation(description = "Busca o produto")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable("id") Long id,@RequestBody Produto produto) {
        produtoService.buscarPorId(id)
                .map(produtoBase ->  {
                    modelMapper.map(produto, produtoBase);
                    System.out.println(produtoService.buscarPorId(id));
                    System.out.println("Atualizando produto " + produto);
                    System.out.println("Atualizando produto2 " + produtoBase);

                    produtoService.salvar(produtoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

    }
}
