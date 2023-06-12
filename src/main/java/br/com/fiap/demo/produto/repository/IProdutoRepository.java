package br.com.fiap.demo.produto.repository;

import br.com.fiap.demo.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, UUID> {


}
