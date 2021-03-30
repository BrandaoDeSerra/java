package com.brandao.produto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brandao.produto.data.vo.ProdutoVO;
import com.brandao.produto.entity.Produto;
import com.brandao.produto.exception.ResourceNotFoundException;
import com.brandao.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public ProdutoVO create(ProdutoVO produtoVO) {
		ProdutoVO pProdutoVO  = ProdutoVO.transform(produtoRepository.save(Produto.transform(produtoVO))); 
		return pProdutoVO;
	}
	
	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}

	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.transform(produto);
	}

	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
				                      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoVO.transform(entity);
	}
	
	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoVO.getId());
		
		if(!optionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return ProdutoVO.transform(produtoRepository.save(Produto.transform(produtoVO)));
	}
	
	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				                      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoRepository.delete(entity);
	}
	
	
}
