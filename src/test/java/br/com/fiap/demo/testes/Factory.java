package br.com.fiap.demo.testes;

import br.com.fiap.demo.dto.ProdutoDTO;
import br.com.fiap.demo.produto.entity.Produto;

import java.math.BigDecimal;

public class Factory {
    public static Produto createProduto() {
        Produto produto = new Produto("bola de gato", "descricao 1", "url 1",  new BigDecimal(800.0));
        return produto;
    }
    public static ProdutoDTO createProdutoDTO() {
        Produto produto = createProduto();
        return new ProdutoDTO(produto);
    }
}
