package com.brandao.pagamento.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.brandao.pagamento.data.vo.ProdutoExternoVO;

@Component
@FeignClient(name = "produto",url = "localhost:8082" , path = "/produto")
public interface ProdutoFeignClient {

	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	ProdutoExternoVO findById(@PathVariable("id")  Long id);
}
