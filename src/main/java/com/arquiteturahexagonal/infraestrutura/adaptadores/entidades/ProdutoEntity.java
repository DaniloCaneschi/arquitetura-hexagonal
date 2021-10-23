package com.arquiteturahexagonal.infraestrutura.adaptadores.entidades;

import com.arquiteturahexagonal.dominio.Produto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;
    private String sku;
    private String nome;
    private Double preco;
    private Double quantidade;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Produto produto) {
        this.sku = produto.getSku();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
    }

    public void atualizar(Produto produto) {
        this.sku = produto.getSku();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
    }

    public Produto toProduto() {
        return new Produto(this.codigo, this.sku, this.nome, this.quantidade, this.preco);
    }
}
