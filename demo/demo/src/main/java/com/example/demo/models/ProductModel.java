package com.example.demo.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductModel {
    
    @Id
    private String id;
    
    private String nome;
    private Integer codigo;
    private Integer quantidade;
    private Double preco;
    
}
