package br.com.fiap.demo.produto.service;

import br.com.fiap.demo.dto.ProdutoDTO;
import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.repository.IProdutoRepository;
import br.com.fiap.demo.produto.service.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository repo;

    public Page<ProdutoDTO> findAll(PageRequest pagina ){
        var produtos = repo.findAll(pagina);
        return produtos.map(prod -> new ProdutoDTO(prod));

    }

    public Produto findById(UUID id){
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return produto;
    }

    public ProdutoDTO save(ProdutoDTO produto){
        Produto entidade = new Produto();
        entidade.setNome(produto.getNome());
        entidade.setPreco(produto.getPreco());
        entidade.setDescricao(produto.getDescricao());
        entidade.setUrlImagem(produto.getUrlImagem());
        var produtoSalvo = repo.save(entidade);
        return new ProdutoDTO(produtoSalvo);
    }


    public ProdutoDTO update(UUID id, ProdutoDTO produto){
        try{
            Produto buscaProduto = repo.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repo.save(buscaProduto);
            return new ProdutoDTO(buscaProduto);
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
