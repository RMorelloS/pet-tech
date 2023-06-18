package br.com.fiap.demo.produto.service;

import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.repository.IProdutoRepository;
import br.com.fiap.demo.produto.service.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Produto findById(UUID id){
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return produto;
    }

    public Produto save(Produto produto){
        var produtoBuscado = repo.save(produto);
        return produtoBuscado;
    }


    public Produto update(UUID id, Produto produto){
        try{
            Produto buscaProduto = repo.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repo.save(buscaProduto);
            return buscaProduto;
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Produto não encontrado, id: " + id);
        }

    }
   public void delete(UUID id){
        try {
            repo.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }catch(DataIntegrityViolationException e){
            throw new EntityNotFoundException("Violação de integridade da base");
        }
   }
}
