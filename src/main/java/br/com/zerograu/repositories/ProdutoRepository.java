package br.com.zerograu.repositories;

import br.com.zerograu.domain.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface ProdutoRepository extends CrudRepository<Produto,Integer> {
}
