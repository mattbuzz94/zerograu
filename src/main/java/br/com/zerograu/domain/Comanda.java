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
public class Comanda {
    @Id
    @Column(name = "id_comanda")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idComanda;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Venda venda;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "comanda")
    @Fetch(FetchMode.JOIN)
    private List<Item> itens = new ArrayList<>();

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void addItem(Item item) {
        addItem(item, true);
    }

    void addItem(Item item, boolean set) {
        if (item != null) {
            if (getItens().contains(item)) {
                getItens().set(getItens().indexOf(item), item);
            } else {
                getItens().add(item);
            }
            if (set) {
                item.setComanda(this, false);
            }
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Comanda))
            return false;

        final Comanda comanda = (Comanda) object;

        if (idComanda != null && comanda.getIdComanda() != null) {
            return idComanda.equals(comanda.getIdComanda());
        }
        return false;
    }

}
