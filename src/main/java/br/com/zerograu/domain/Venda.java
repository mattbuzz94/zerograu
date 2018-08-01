package br.com.zerograu.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_venda")
    private Integer idVenda;

    @Column (name = "valor_total")
    private double valorTotal;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
