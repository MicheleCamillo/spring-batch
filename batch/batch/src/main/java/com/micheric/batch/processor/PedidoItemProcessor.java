package com.micheric.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.micheric.batch.model.Pedido;

public class PedidoItemProcessor implements ItemProcessor<Pedido, Pedido> {

	@Override
	public Pedido process(Pedido item) throws Exception {
		
		System.out.println(item.toString());
		if (item.isCupom()) {
			item.setNomePrato(item.getNomePrato().toUpperCase());
		}
		
		return item;
	}

	
	
}
