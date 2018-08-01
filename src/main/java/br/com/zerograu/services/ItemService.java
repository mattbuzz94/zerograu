package br.com.zerograu.services;

import br.com.zerograu.domain.Item;
import br.com.zerograu.domain.Item;

import java.util.List;

public interface ItemService {
    List<Item> listAll();

    Item getById(Integer id);

    Item saveOrUpdate(Item item);

    void delete(Integer id);

}
