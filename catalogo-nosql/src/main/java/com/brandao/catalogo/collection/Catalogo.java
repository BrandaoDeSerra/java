package com.brandao.catalogo.collection;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.brandao.catalogo.data.vo.CatalogoVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document(collection = "catalogo")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Catalogo implements Serializable {

	private static final long serialVersionUID = 840917418532642260L;

	@Id
	@Field(name = "id")
	private String id;
	
	@Field(name = "nome")
	private String nome;
	
	@Field(name = "marca")
	private String marca;
	
	@Field(name = "fabricante")
	private String fabricante;
	
	@Field(name = "preco")
	private Double preco;
	
	public static Catalogo create(CatalogoVO objectVO) {
		return new ModelMapper().map(objectVO, Catalogo.class);
	}
}
