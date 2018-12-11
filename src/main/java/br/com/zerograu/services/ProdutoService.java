package br.com.zerograu.services;

import br.com.zerograu.domain.Produto;

import java.util.List;

public interface ProdutoService {

    List<Produto> listAll();

    Produto getById(Integer id);

    Produto saveOrUpdate(Produto product);

    void delete(Integer id);

    List<Produto> retornaBusca(String descProduto, Integer codigoBarras);
}
