package br.com.fiap.demo.produto.controller;

import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.service.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController
{
    @Autowired
    private ProdutoService produtoService;
    public ResponseEntity<Collection<Produto>> findAll(){
        var produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable UUID id){
        var produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        var produtoSaved = produtoService.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoSaved);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable UUID id){
        var produtoAtualizado = produtoService.update(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity delete(@PathVariable UUID id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
