package br.com.zerograu.services;

import br.com.zerograu.domain.Item;
import br.com.zerograu.domain.Venda;
import br.com.zerograu.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAll() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add); // fun with Java 8
        return items;
    }

    @Override
    public Item getById(Integer id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item saveOrUpdate(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        itemRepository.delete(id);
    }
}