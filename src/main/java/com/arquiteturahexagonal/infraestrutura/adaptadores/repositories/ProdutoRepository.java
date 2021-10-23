package com.arquiteturahexagonal.infraestrutura.adaptadores.repositories;

import com.arquiteturahexagonal.dominio.Produto;
import com.arquiteturahexagonal.dominio.portas.repositories.ProdutoRepositoryPort;
import com.arquiteturahexagonal.infraestrutura.adaptadores.entidades.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoRepository implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepository(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findAll();
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscarPeloSku(String sku) {
        Optional<ProdutoEntity> produtoEntity = this.springProdutoRepository.findBySku(sku);

        if (produtoEntity.isPresent())
            return produtoEntity.get().toProduto();

        throw new RuntimeException("Produto não existe");
    }

    @Override
    public void salvar(Produto produto) {
        ProdutoEntity produtoEntity;
        if (Objects.isNull(produto.getCodigo()))
            produtoEntity = new ProdutoEntity(produto);
        else {
            produtoEntity = this.springProdutoRepository.findById(produto.getCodigo()).get();
            produtoEntity.atualizar(produto);
        }

        this.springProdutoRepository.save(produtoEntity);
    }
}
