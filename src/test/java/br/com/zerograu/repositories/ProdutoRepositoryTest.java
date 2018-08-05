package br.com.zerograu.repositories;

import br.com.zerograu.domain.Item;
import br.com.zerograu.domain.Produto;
import br.com.zerograu.domain.Venda;
import br.com.zerograu.services.ItemService;
import br.com.zerograu.services.ProdutoService;
import br.com.zerograu.services.VendaService;
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
public class ProdutoRepositoryTest {

    private static final Double BIG_DECIMAL_100 = Double.valueOf(100.00);
    private static final String produto_DESCRIPTION = "Cerveja Império";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    private static final Double BIG_DECIMAL_100_2 = Double.valueOf(75.00);
    private static final Double BIG_DECIMAL_100_3 = Double.valueOf(200.00);
    private static final String produto_DESCRIPTION2 = "Cerveja Brahma";
    private static final String IMAGE_URL2 = "http://an-imageurl.com/image1.jpg";


    private static final int quantidadeItem1 = 2;
    private static final int quantidadeItem2 = 5;


    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private VendaService vendaService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Produto produto = new Produto();
        produto.setDescProduto(produto_DESCRIPTION);
        produto.setImageUrl(IMAGE_URL);
        produto.setPrecoVenda(BIG_DECIMAL_100);

        Produto produto2 = new Produto();
        produto2.setDescProduto(produto_DESCRIPTION2);
        produto2.setImageUrl(IMAGE_URL2);
        produto2.setPrecoVenda(BIG_DECIMAL_100_2);

        //when
        produtoService.saveOrUpdate(produto);
        produtoService.saveOrUpdate(produto2);

        //then
        Assert.assertNotNull(produto.getIdProduto());
        Produto produto1 = produtoService.getById(produto.getIdProduto());
        Assert.assertEquals((Integer) 1, produto1.getIdProduto());
        Assert.assertEquals(produto_DESCRIPTION, produto1.getDescProduto());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(produto1.getPrecoVenda()), 0);
        Assert.assertEquals(IMAGE_URL, produto1.getImageUrl());

    }

    @Test
    public void testVenda(){

        Produto produto = new Produto();
        produto.setDescProduto(produto_DESCRIPTION);
        produto.setImageUrl(IMAGE_URL);
        produto.setPrecoVenda(BIG_DECIMAL_100);

        Produto produto2 = new Produto();
        produto2.setDescProduto(produto_DESCRIPTION2);
        produto2.setImageUrl(IMAGE_URL2);
        produto2.setPrecoVenda(BIG_DECIMAL_100_2);

        List<Produto> produtosList = new ArrayList<>();
        produtosList.add(produto);
        produtosList.add(produto2);

        //when
        produtoService.saveOrUpdate(produto);
        produtoService.saveOrUpdate(produto2);

        double precoTotalItem1,precoTotalItem2;
        double valorTotalVenda=0;

        Venda venda = new Venda();
        ArrayList<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setProduct(produtosList.get(0));
        item1.setQuantity(quantidadeItem1);
        precoTotalItem1 = item1.getQuantity()*item1.getProduct().getPrecoVenda();
        item1.setTotalPrice(precoTotalItem1);
        items.add(item1);

        //itemService.saveOrUpdate(item1);

        Item item2 = new Item();
        item2.setProduct(produtosList.get(1));
        item2.setQuantity(quantidadeItem2);
        precoTotalItem2 = item2.getQuantity()*item2.getProduct().getPrecoVenda();
        item2.setTotalPrice(precoTotalItem2);
        items.add(item2);

        //itemService.saveOrUpdate(item2);

        for (Item item :items) {
            valorTotalVenda += item.getTotalPrice();
        }

        Item itempersisted;

        venda.setValorTotal(valorTotalVenda);
        venda.setItems(items);

        int x = 0;
        while (x < venda.geItems().size()) {
            itempersisted = venda.geItems().get(x);
            itempersisted.setVenda(venda);
            x++;
        }
        vendaService.saveOrUpdate(venda);

        //then
        Assert.assertNotNull(item1.getId());
        Item itemTested = itemService.getById(item1.getId());
        Assert.assertEquals((Integer) 1, itemTested.getId());
        Assert.assertEquals(quantidadeItem1, itemTested.getQuantity());
        Assert.assertEquals(BIG_DECIMAL_100_3.compareTo(itemTested.getTotalPrice()), 0);

    }
}