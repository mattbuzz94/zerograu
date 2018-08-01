package br.com.zerograu.repositories;

import br.com.zerograu.domain.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,Integer> {
}