package br.com.zerograu.repositories;

import br.com.zerograu.domain.Produto;
import br.com.zerograu.services.ItemService;
import br.com.zerograu.services.ProdutoService;
import br.com.zerograu.services.VendaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProdutoSearch {
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
    public void seachTest() {

        int x[] = new int[3];
        int tamanho = x.length;
        System.out.println(tamanho);

        /*List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        params.add(new SearchCriteria("projeto", "=", "BROKER SYS"));
        params.add(new SearchCriteria("descricaoChamado", ":", "BROKER_MAP"));
        params.add(new SearchCriteria("tituloChamado", ":", "BROKER_MAP"));
        params.add(new SearchCriteria("solucaoProposta", ":", "BROKER_MAP"));
        params.add(new SearchCriteria("comentarioInterno", ":", "BROKER_MAP"));*/

        String textoBusca = "Teste";
        Integer modulo = 123;
        String textoBuscaDecoded = null;
        try {
            textoBuscaDecoded = java.net.URLDecoder.decode(textoBusca, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(textoBuscaDecoded);
        System.out.println(modulo);
        List<Produto> results = produtoService.retornaBusca(textoBuscaDecoded, modulo);
        // List<Produto> results = produtoService.searchChamado(params);
        Produto produto;

        produto = results.get(0);

        System.out.println(produto.getDescProduto());
        System.out.println("Estoque: " + produto.getQtdEstoque());


    }
}
