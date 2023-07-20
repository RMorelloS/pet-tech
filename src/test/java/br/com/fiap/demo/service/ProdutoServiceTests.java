package br.com.fiap.demo.service;

import br.com.fiap.demo.dto.ProdutoDTO;
import br.com.fiap.demo.produto.entity.Produto;
import br.com.fiap.demo.produto.repository.IProdutoRepository;
import br.com.fiap.demo.produto.service.ProdutoService;
import br.com.fiap.demo.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.demo.testes.Factory;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {
    @InjectMocks
    private ProdutoService service;
    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;

    private ProdutoDTO produtoDTO;
    private Produto produto;
    private String nomeAtualizado;
    @BeforeEach
    public void setup(){
        idExistente = UUID.fromString("3272d6ca-114a-11ee-be56-0242ac120002");
        idNaoExistente = UUID.fromString("3272d6ca-114a-11ee-be56-0242ac120050");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO= Factory.createProdutoDTO();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado= "Produto att";
        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

    }
    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId(){
        Produto produtoDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtoDTO);
    }
    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO(){
        Page produtoDTO = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtoDTO);
    }
    @Test
    public void findByIdDeveRetornarUmaExcecaoAoBuscarPorIdQueNaoExista(){
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.findById(idNaoExistente);
        });
    }
}

