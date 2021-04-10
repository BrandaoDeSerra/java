package com.brandao.pagamento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brandao.pagamento.data.vo.ProdutoExternoVO;
import com.brandao.pagamento.data.vo.VendaVO;
import com.brandao.pagamento.entity.ProdutoVenda;
import com.brandao.pagamento.entity.Venda;
import com.brandao.pagamento.feign.clients.ProdutoFeignClient;
import com.brandao.pagamento.repository.ProdutoVendaRepository;
import com.brandao.pagamento.repository.ResourceNotFoundException;
import com.brandao.pagamento.repository.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;
	private final ProdutoFeignClient produtoFeignClient;

	@Autowired
	public VendaService(VendaRepository vendaRepository,ProdutoVendaRepository produtoVendaRepository, ProdutoFeignClient produtoFeignClient) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository =  produtoVendaRepository;
		this.produtoFeignClient = produtoFeignClient;
	}
	
	public VendaVO create(VendaVO vendaVO) {
		Venda venda = vendaRepository.save(Venda.create(vendaVO));
		
		List<ProdutoVenda> produtosSalvos =  new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		venda.setProdutos(produtosSalvos);
		
		return VendaVO.create(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}

	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return VendaVO.create(entity);
	}
	
	/* Buscando um produto da venda usando o Feing "servico chamando servico" via rest e com hytrix*/
	public ProdutoExternoVO findProdutoExternoById(Long id) {

		ProdutoExternoVO proExtVO = produtoFeignClient.findById(id);
		
		return proExtVO;
	}
	
}
