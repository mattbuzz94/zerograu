package br.com.zerograu.services;

import br.com.zerograu.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listAll();

    Produto getById(Integer id);

    Produto saveOrUpdate(Produto product);

    void delete(Integer id);

    List<Produto> retornaBusca(String textoBusca, String projetoBusca);
}
