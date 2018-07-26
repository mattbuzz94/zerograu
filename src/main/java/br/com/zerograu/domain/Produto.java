package br.com.zerograu.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_produto;

    @Column
    private String desc_produto;

    @Column
    private BigDecimal preco_compra;

    @Column
    private BigDecimal preco_venda;

    @Column
    private int qtd_estoque;

    @Column
    private String imageUrl;

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getDesc_produto() {
        return desc_produto;
    }

    public void setDesc_produto(String desc_produto) {
        this.desc_produto = desc_produto;
    }

    public BigDecimal getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(BigDecimal preco_compra) {
        this.preco_compra = preco_compra;
    }

    public BigDecimal getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(BigDecimal preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
