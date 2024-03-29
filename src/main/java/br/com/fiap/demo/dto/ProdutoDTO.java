package br.com.fiap.demo.dto;

import br.com.fiap.demo.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    @NotBlank(message="Nome é obrigatório")
    private String nome;
    private String descricao;
    private String urlImagem;
    @Positive(message = "O valor do produto deve ser positivo")
    private BigDecimal preco;
    public ProdutoDTO(){

    }

    public ProdutoDTO(UUID id, String nome, String descricao, String urlImagem, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }
    public ProdutoDTO(Produto entidade){
        this.id= entidade.getId();
        this.nome = entidade.getNome();
        this.descricao = entidade.getDescricao();
        this.urlImagem = entidade.getUrlImagem();
        this.preco = entidade.getPreco();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
