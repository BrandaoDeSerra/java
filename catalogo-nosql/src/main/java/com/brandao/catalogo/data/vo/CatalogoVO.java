package com.brandao.catalogo.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.brandao.catalogo.collection.Catalogo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","marca","fabricante","preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CatalogoVO extends RepresentationModel<CatalogoVO> implements Serializable {

	private static final long serialVersionUID = 2938272643682548375L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("marca")
	private String marca;
	
	@JsonProperty("fabricante")
	private String fabricante;
	
	@JsonProperty("preco")
	private Double preco;
	
	public static CatalogoVO create(Catalogo obj) {
		return new ModelMapper().map(obj, CatalogoVO.class);
	}
}
