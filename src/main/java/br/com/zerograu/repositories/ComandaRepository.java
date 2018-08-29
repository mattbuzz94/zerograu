package br.com.zerograu.repositories;

import br.com.zerograu.domain.Comanda;
import org.springframework.data.repository.CrudRepository;

public interface ComandaRepository extends CrudRepository<Comanda, Integer> {
}
