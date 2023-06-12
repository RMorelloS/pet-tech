package br.com.fiap.demo.pessoa.repository;


import br.com.fiap.demo.pessoa.entity.PessoaFisica;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaFisicaRepository {

    static private Set<PessoaFisica> pessoas;
    {
        pessoas = new LinkedHashSet<>();
        PessoaFisica p1 = new PessoaFisica();
        p1.setCpf("1233212231");
        p1.setNome("benefrancis");
        p1.setNascimento(LocalDate.of(1977,3,8));
        PessoaFisica d1 = new PessoaFisica();
        d1.setCpf("2131122");
        d1.setNome("aaaa");
        d1.setNascimento(LocalDate.of(1951,1,30));
        p1.addDependente(d1);

        save(p1);
        save(d1);

    }
    public Collection findAll(){
        return pessoas;
    }

    public Optional<PessoaFisica> findById(Long id){
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public PessoaFisica save(PessoaFisica p){
        p.setId(pessoas.size() + 1L);
        pessoas.add(p);
        return p;
    }

    public Optional<PessoaFisica> update(PessoaFisica p){
        Optional<PessoaFisica> pessoa = findById(p.getId());
        if (pessoa.isPresent()){
            PessoaFisica pessoaFinal = pessoa.get();
            pessoaFinal.setCpf(p.getCpf());
            pessoaFinal.setNascimento(p.getNascimento());
            pessoaFinal.setNome(p.getNome());

            return Optional.of(pessoaFinal);
        }
        return Optional.empty();
    }
    public void delete(Long id){
        pessoas.removeIf(p -> p.getId().equals(id));
    }

}
