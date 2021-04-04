package com.brandao.produto.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.brandao.produto.data.vo.ProdutoVO;

@Component
public class ProdutoSendMessage {

	@Value("${produto.rabbitmq.exchange}")
	String exchange;
	
	@Value("${produto.rabbitmq.routingkey}")
	String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public ProdutoSendMessage(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(ProdutoVO produtoVO) {
		rabbitTemplate.convertAndSend(exchange,routingkey,produtoVO);
	}
	
}
