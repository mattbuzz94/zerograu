package br.com.zerograu.services;

import br.com.zerograu.domain.Venda;

import java.util.List;

public interface VendaService {

    List<Venda> listAll();

    Venda getById(Integer id);

    Venda saveOrUpdate(Venda venda);

    void delete(Integer id);

}
