package com.iftm.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.apache.juli.logging.Log;

import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="quantidade_item", nullable = false)
    private Integer quantidadeItem;
    @ManyToOne
    @JsonBackReference
    private Produto produto;

    public Pedido() {
    }

    public Pedido(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && Objects.equals(quantidadeItem, pedido.quantidadeItem) && Objects.equals(produto, pedido.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidadeItem, produto);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", quantidadeItem=" + quantidadeItem +
                ", produto=" + produto +
                '}';
    }
}


