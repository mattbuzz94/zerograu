package br.com.zerograu.services;

import br.com.zerograu.domain.Venda;
import br.com.zerograu.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Service
public class VendaServiceImpl implements VendaService {

    private VendaRepository vendaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository= vendaRepository;
    }

    @Override
    public List<Venda> listAll() {
        List<Venda> vendas = new ArrayList<>();
        vendaRepository.findAll().forEach(vendas::add); // fun with Java 8
        return vendas;
    }

    @Override
    public Venda getById(Integer id) {
        return vendaRepository.findOne(id);
    }

    @Override
    public Venda saveOrUpdate(Venda venda) {
        vendaRepository.save(venda);
        return venda;
    }

    @Override
    public void delete(Integer id) {
        vendaRepository.delete(id);
    }
}