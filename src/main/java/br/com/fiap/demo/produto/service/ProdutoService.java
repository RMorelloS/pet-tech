package br.com.fiap.demo.produto.service;

import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository repo;

    public Collection<Produto> findAll(){
        var produtos = repo.findAll();
        return produtos;

    }

    public Optional<Produto> findById(UUID id){
        var produto = repo.findById(id);
        return produto;
    }

    public Produto save(Produto produto){
        var produtoBuscado = repo.save(produto);
        return produtoBuscado;
    }


//    public Optional<Produto> update(UUID id, Produto produto){
//        Optional<Produto> buscaProduto = this.findById(id);
//        if(!buscaProduto.isPresent()){
//            Produto produtoUpdate = repo.update(id, produto);
//            return Optional.of(produtoUpdate);
//        }
//        return Optional.empty();
//    }
//    public void delete(UUID id){
//        repo.delete(id);
//    }
}
