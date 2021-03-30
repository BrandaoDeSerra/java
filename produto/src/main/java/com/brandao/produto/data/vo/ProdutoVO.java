package com.brandao.produto.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.brandao.produto.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id","nome","preco","estoque"})
public class ProdutoVO implements Serializable {

    private static final long serialVersionUID = -5821955348876623164L;
	
    @JsonProperty("id")
	private Long id;
    
    @JsonProperty("nome")
    private String nome;
	
    @JsonProperty("estoque")
	private Integer estoque;
	
    @JsonProperty("preco")
	private Double preco;
    
    public static ProdutoVO transform(Produto produto){
    	return new ModelMapper().map(produto, ProdutoVO.class);
    }
}
