package br.com.zerograu.repositories;

import br.com.zerograu.ZerograuApplication;
import br.com.zerograu.domain.Produto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProdutoRepositoryTest {

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String produto_DESCRIPTION = "a cool produto";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Produto produto = new Produto();
        produto.setDesc_produto(produto_DESCRIPTION);
        produto.setImageUrl(IMAGE_URL);
        produto.setPreco_venda(BIG_DECIMAL_100);

        //when
        produtoRepository.save(produto);

        //then
        Assert.assertNotNull(produto.getId_produto());
        Produto produto1 = produtoRepository.findOne(produto.getId_produto());        
        Assert.assertEquals((Integer) 1, produto1.getId_produto());
        Assert.assertEquals(produto_DESCRIPTION, produto1.getDesc_produto());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(produto1.getPreco_venda()), 0);
        Assert.assertEquals(IMAGE_URL, produto1.getImageUrl());
    }
}