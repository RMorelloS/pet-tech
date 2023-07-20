package br.com.fiap.demo.repository;

import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.repository.IProdutoRepository;
import br.com.fiap.demo.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.demo.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest

public class ProdutoRepositoryTests {
@Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private long countTotalProdutos;
    private String nomeAtualizado;
    @BeforeEach
    void setup() throws Exception {
        idExistente = UUID.fromString("3272d6ca-114a-11ee-be56-0242ac120002");
        idNaoExistente = UUID.fromString("3272d6ca-114a-11ee-be56-0242ac120050");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5L;
    }

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista(){
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertTrue(result.isPresent());
    }


    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista(){
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(this.idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        });
    }
    public void saveDeveSalvarObjetoCasoIdSejaNull(){
        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados() {
        Page produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), countTotalProdutos);

    }
    @Test
    public void saveDeveAtualizarObjetoCasoIdSejaNull(){
        Produto produto = Factory.createProduto();
        produto.setId(this.idNaoExistente);
        produto.setNome(this.nomeAtualizado);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertEquals(produtoSalvo.getNome(), this.nomeAtualizado);
    }
    @Test
    public void deleteDeveDeletarObjetoCasoExista(){
        produtoRepository.deleteById(this.idExistente);
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertFalse(result.isPresent());
    }

}
