package com.arquiteturahexagonal.dominio.adaptadores.services;

import com.arquiteturahexagonal.dominio.Produto;
import com.arquiteturahexagonal.dominio.dtos.EstoqueDTO;
import com.arquiteturahexagonal.dominio.dtos.ProdutoDTO;
import com.arquiteturahexagonal.dominio.portas.interfaces.ProdutoServicePort;
import com.arquiteturahexagonal.dominio.portas.repositories.ProdutoRepositoryPort;
import javassist.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PedidoServiceImp implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepository;

    public PedidoServiceImp(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO);
        this.produtoRepository.salvar(produto);
    }

    @Override
    public List<ProdutoDTO> buscarProdutos() {
        List<Produto> produtos = this.produtoRepository.buscarTodos();
        List<ProdutoDTO> produtoDTOS = produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
        return produtoDTOS;
    }

    @Override
    public void atualizarEstoque(String sku, EstoqueDTO estoqueDTO) throws NotFoundException {
        Produto produto = this.produtoRepository.buscarPeloSku(sku);

        if (Objects.isNull(produto))
            throw new NotFoundException("Produto não encontrado");

        produto.atualizarEstoque(estoqueDTO.getQuantidade());

        this.produtoRepository.salvar(produto);
    }
}
