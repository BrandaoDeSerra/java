package com.brandao.catalogo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brandao.catalogo.collection.Catalogo;
import com.brandao.catalogo.data.vo.CatalogoVO;
import com.brandao.catalogo.exception.ResourceNotFoundException;
import com.brandao.catalogo.repository.CatalogoRepository;

@Service
public class CatalogoService {

	private final CatalogoRepository catalogoRepository;

	@Autowired
	public CatalogoService(CatalogoRepository catalogoRepository) {
		this.catalogoRepository = catalogoRepository;
	}
	
	public CatalogoVO create(CatalogoVO objVO) {
	 	CatalogoVO objVoRetorno = CatalogoVO.create(catalogoRepository.save(Catalogo.create(objVO)));
		return objVoRetorno;
	}
	
	public Page<CatalogoVO> findAll(Pageable pageable) {
		var page = catalogoRepository.findAll(pageable);
		return page.map(this::convertToCatalogoVO);
	}

	private CatalogoVO convertToCatalogoVO(Catalogo catalogo) {
		return CatalogoVO.create(catalogo);
	}

	public CatalogoVO findById(String id) {
		var entity = catalogoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return CatalogoVO.create(entity);
	}
	
	public CatalogoVO update(CatalogoVO catalogoVO) {
		final Optional<Catalogo> optionalCatalogo = catalogoRepository.findById(catalogoVO.getId());
		
		if(!optionalCatalogo.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return CatalogoVO.create(catalogoRepository.save(Catalogo.create(catalogoVO)));
	}
	
	public void delete(String id) {
		var entity = catalogoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		catalogoRepository.delete(entity);
	}
}
