package br.com.zerograu.services;

import br.com.zerograu.domain.Comanda;

import java.util.List;

public interface ComandaService {
    List<Comanda> listAll();

    Comanda getById(Integer id);

    Comanda saveOrUpdate(Comanda comanda);

    void delete(Integer id);
}
