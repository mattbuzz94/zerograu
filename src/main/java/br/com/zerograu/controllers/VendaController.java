package br.com.zerograu.controllers;

import br.com.zerograu.domain.Item;
import br.com.zerograu.domain.Venda;
import br.com.zerograu.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class VendaController {
    private VendaService vendaService;

    @Autowired
    public void setVendaService(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @RequestMapping("/venda")
    public String redirToList() {
        return "redirect:/venda/list";
    }

    @GetMapping(value = "/venda/list")
    public ResponseEntity<List<Venda>> listAllVendas() {
        List<Venda> vendas = vendaService.listAll();
        if (vendas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
            // HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping(value = "/venda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venda> getVenda(@PathVariable("id") Integer id) {
        System.out.println("Fetching Venda with id " + id);
        Venda venda = vendaService.getById(id);
        if (venda == null) {
            System.out.println("Venda com o id " + id + " não encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    // --------------------- CREATE --------------------------------------------------
    @PostMapping(value = "/venda/")
    public ResponseEntity<Void> createVenda(@RequestBody Venda venda, UriComponentsBuilder ucBuilder) {
        System.out.println("Criando o Venda " + venda.getIdVenda());
        Double valorTotalVenda = 0.00;
        Item itempersisted;
        for (Item item : venda.geItems()) {
            valorTotalVenda += item.getTotalPrice();
        }
        venda.setValorTotal(valorTotalVenda);
        int x = 0;
        while (x < venda.geItems().size()) {
            itempersisted = venda.geItems().get(x);
            itempersisted.setVenda(venda);
            x++;
        }

        /*
         * if (vendaService.isVendaExist(venda)) {
         * System.out.println("A Venda with name " + venda.getNomeVenda() +
         * " already exist"); return new ResponseEntity<Void>(HttpStatus.CONFLICT); }
         */
        vendaService.saveOrUpdate(venda);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/venda/{id}").buildAndExpand(venda.getIdVenda()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Venda
    @PutMapping(value = "/venda/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable("id") Integer id, @RequestBody Venda venda) {
        System.out.println("Atualizando o Venda " + id);

        Venda currentVenda = vendaService.getById(id);

        if (currentVenda == null) {
            System.out.println("Venda com o " + id + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentVenda.setIdVenda(venda.getIdVenda());

        vendaService.saveOrUpdate(currentVenda);
        return new ResponseEntity<>(currentVenda, HttpStatus.OK);
    }

    // ------------------- Delete a Venda
    // --------------------------------------------------------
    @DeleteMapping(value = "/venda/{id}")
    public ResponseEntity<Venda> deleteVenda(@PathVariable("id") Integer id) {
        System.out.println("Buscando & Deletando o Venda com o id " + id);

        Venda venda = vendaService.getById(id);
        if (venda == null) {
            System.out.println("Não foi possível deletar. Venda com o id " + id + " não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vendaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}