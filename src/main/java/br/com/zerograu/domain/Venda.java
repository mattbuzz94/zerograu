package br.com.zerograu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_venda")
    private Integer idVenda;

    @Column (name = "valor_total")
    private double valorTotal;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "venda")
    @Fetch(FetchMode.JOIN)
    private List<Item> item = new ArrayList<Item>();

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

    public List<Item> geItems() {
        return item;
    }

    public void setItems(ArrayList<Item> items) {
        this.item = items;
    }

    public void addItem(Item item) {
        addItem(item, true);
    }

    void addItem(Item item, boolean set) {
        if (item != null) {
            if (geItems().contains(item)) {
                geItems().set(geItems().indexOf(item), item);
            } else {
                geItems().add(item);
            }
            if (set) {
                item.setVenda(this, false);
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Venda))
            return false;

        final Venda di = (Venda) object;

        if (idVenda != null && di.getIdVenda() != null) {
            return idVenda.equals(di.getIdVenda());
        }
        return false;
    }
}
