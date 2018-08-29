package br.com.zerograu.controllers;

import br.com.zerograu.domain.Comanda;
import br.com.zerograu.domain.Item;
import br.com.zerograu.services.ComandaService;
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
public class ComandaController {
    private ComandaService comandaService;

    @Autowired
    public void setComandaService(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @RequestMapping("/comanda")
    public String redirToList() {
        return "redirect:/comanda/list";
    }

    @GetMapping(value = "/comanda/list")
    public ResponseEntity<List<Comanda>> listAllComandas() {
        List<Comanda> comandas = comandaService.listAll();
        if (comandas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);// You many decide to return
            // HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(comandas, HttpStatus.OK);
    }

    @GetMapping(value = "/comanda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comanda> getComanda(@PathVariable("id") Integer id) {
        System.out.println("Fetching Comanda with id " + id);
        Comanda comanda = comandaService.getById(id);
        if (comanda == null) {
            System.out.println("Comanda com o id " + id + " não encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comanda, HttpStatus.OK);
    }

    // --------------------- CREATE --------------------------------------------------
    @PostMapping(value = "/comanda/")
    public ResponseEntity<Void> createComanda(@RequestBody Comanda comanda, UriComponentsBuilder ucBuilder) {
        System.out.println("Criando o Comanda " + comanda.getIdComanda());

        Item itempersisted;

        /*for (Item item : comanda.getItens()) {
            valorTotalComanda += item.getTotalPrice();
        }
        comanda.setValorTotal(valorTotalComanda);*/
        int x = 0;
        while (x < comanda.getItens().size()) {
            itempersisted = comanda.getItens().get(x);
            itempersisted.setComanda(comanda);
            x++;
        }

        /*
         * if (comandaService.isComandaExist(comanda)) {
         * System.out.println("A Comanda with name " + comanda.getNomeComanda() +
         * " already exist"); return new ResponseEntity<Void>(HttpStatus.CONFLICT); }
         */
        comandaService.saveOrUpdate(comanda);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comanda/{id}").buildAndExpand(comanda.getIdComanda()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Comanda
    @PutMapping(value = "/comanda/{id}")
    public ResponseEntity<Comanda> updateComanda(@PathVariable("id") Integer id, @RequestBody Comanda comanda) {
        System.out.println("Atualizando o Comanda " + id);

        Comanda currentComanda = comandaService.getById(id);

        if (currentComanda == null) {
            System.out.println("Comanda com o " + id + " não foi encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentComanda.setIdComanda(comanda.getIdComanda());

        comandaService.saveOrUpdate(currentComanda);
        return new ResponseEntity<>(currentComanda, HttpStatus.OK);
    }

    // ------------------- Delete a Comanda
    // --------------------------------------------------------
    @DeleteMapping(value = "/comanda/{id}")
    public ResponseEntity<Comanda> deleteComanda(@PathVariable("id") Integer id) {
        System.out.println("Buscando & Deletando o Comanda com o id " + id);

        Comanda comanda = comandaService.getById(id);
        if (comanda == null) {
            System.out.println("Não foi possível deletar. Comanda com o id " + id + " não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comandaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
