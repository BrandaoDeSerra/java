package com.brandao.pagamento.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.brandao.pagamento.data.vo.ProdutoVO;
import com.brandao.pagamento.entity.Produto;
import com.brandao.pagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@RabbitListener(queues = {"${produto.rabbitmq.queue}"})
	public void receive (@Payload ProdutoVO produtoVO){
		produtoRepository.save(Produto.trasform(produtoVO));
	}
	
}
