package br.com.zerograu.services;

import br.com.zerograu.domain.Comanda;
import br.com.zerograu.repositories.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComandaServiceImpl implements ComandaService {
    ComandaRepository comandaRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ComandaServiceImpl(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    @Override
    public List<Comanda> listAll() {
        List<Comanda> comandas = new ArrayList<>();
        comandaRepository.findAll().forEach(comandas::add); // fun with Java 8
        return comandas;
    }

    @Override
    public Comanda getById(Integer id) {
        return comandaRepository.findOne(id);
    }

    @Override
    public Comanda saveOrUpdate(Comanda comanda) {
        comandaRepository.save(comanda);
        return comanda;
    }

    @Override
    public void delete(Integer id) {
        comandaRepository.delete(id);
    }
}