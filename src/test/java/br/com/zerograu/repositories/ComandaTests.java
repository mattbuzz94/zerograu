package br.com.zerograu.repositories;

import br.com.zerograu.domain.Comanda;
import br.com.zerograu.domain.Item;
import br.com.zerograu.domain.Produto;
import br.com.zerograu.services.ComandaService;
import br.com.zerograu.services.ItemService;
import br.com.zerograu.services.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ComandaTests {

    private static final Integer idComandaPersisted = 1;

    @Autowired
    private ComandaService comandaService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProdutoService produtoService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        Comanda comanda = new Comanda();
        comanda.setNomeCliente("Matheus Maciel");
        List<Produto> produtosList = new ArrayList<>();
        produtosList = produtoService.listAll();

        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setComanda(comanda);
        item1.setProduct(produtosList.get(0));
        item1.setQuantity(3);
        double precoTotalItem1 = item1.getQuantity() * item1.getProduct().getPrecoVenda();
        item1.setTotalPrice(precoTotalItem1);
        items.add(item1);

        //itemService.saveOrUpdate(item1);

        Item item2 = new Item();
        item2.setProduct(produtosList.get(1));
        item2.setQuantity(1);
        item1.setComanda(comanda);
        double precoTotalItem2 = item2.getQuantity() * item2.getProduct().getPrecoVenda();
        item2.setTotalPrice(precoTotalItem2);
        items.add(item2);

        Item itempersisted;

        comanda.setItens(items);


        int x = 0;
        while (x < comanda.getItens().size()) {
            itempersisted = comanda.getItens().get(x);
            itempersisted.setComanda(comanda);
            x++;
        }

        comandaService.saveOrUpdate(comanda);

        //then
        Assert.assertNotNull(comanda.getIdComanda());
        Comanda comandaTested = comandaService.getById(comanda.getIdComanda());
        Assert.assertEquals((Integer) 1, comandaTested.getIdComanda());

    }
}
