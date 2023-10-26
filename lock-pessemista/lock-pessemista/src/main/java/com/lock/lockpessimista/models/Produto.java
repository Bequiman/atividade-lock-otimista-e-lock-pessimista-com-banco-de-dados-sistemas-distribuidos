package com.lock.lockpessimista.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="descricao_produto", nullable = false)
    private String descricaoProduto;
    @Column(name = "valor_produto", nullable = false)
    private Double  valorProduto;
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pedido> pedidos = new ArrayList<>();

    public Produto() {
    }

    public Produto(String descricaoProduto, Double valorProduto, Integer quantidadeEstoque) {
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id && Objects.equals(descricaoProduto, produto.descricaoProduto) && Objects.equals(valorProduto, produto.valorProduto) && Objects.equals(quantidadeEstoque, produto.quantidadeEstoque) && Objects.equals(pedidos, produto.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricaoProduto, valorProduto, quantidadeEstoque, pedidos);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricaoProduto='" + descricaoProduto + '\'' +
                ", valorProduto=" + valorProduto +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", pedidos=" + pedidos +
                '}';
    }
}



