package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProductModel;
import com.example.demo.repository.ProductRepository;

import jakarta.annotation.Nullable;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductModel productModel;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductModel> consultarProdutos(){
        Sort sort = Sort.by("nome").ascending(); 
        return productRepository.findAll(sort);   
    }

    @Nullable
    public ProductModel cadastrarProduto(ProductModel productModel){
        if(!productRepository.findById(productModel.getId()).isPresent()){
            return productRepository.save(productModel);
        }

        return null;
    }

    public List<ProductModel> atualizarProduto(String id, ProductModel produtoAtualizado){
        ProductModel produtoExistente = productRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setCodigo(produtoAtualizado.getCodigo());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        produtoExistente.setPreco(produtoAtualizado.getPreco());

        productRepository.save(produtoExistente);

        return consultarProdutos();
    }

    public List<ProductModel> deletarProduto(String id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } return consultarProdutos();
    }
}
