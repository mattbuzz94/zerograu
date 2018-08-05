package br.com.zerograu.domain;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount")
    private double discount;

    @Column(name = "tax")
    private double tax;

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Produto product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda")
    private Venda venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Produto getProduct() {
        return product;
    }

    public void setProduct(Produto product) {
        this.product = product;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        setVenda(venda, true);
    }

    void setVenda(Venda venda1, boolean add) {
        this.venda = venda1;
        if (venda1 != null && add) {
            venda1.addItem(this, false);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Item))
            return false;

        final Item item = (Item) object;

        if (id != null && item.getId() != null) {
            return id.equals(item.getId());
        }
        return false;
    }
}
