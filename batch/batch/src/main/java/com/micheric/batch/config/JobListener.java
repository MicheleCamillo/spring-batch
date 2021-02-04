package com.micheric.batch.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.micheric.batch.model.Pedido;

@Component
public class JobListener extends JobExecutionListenerSupport {
	
	private final JdbcTemplate template;
	//Quando o spring tentar criar essa classe, ele é obrigado a passar o atributo "template", 
	//Entao ele tem que dar um jeito de criar => injeção de dependencia
	//Estamos forcando o spring a criar e passar o parametro
	//Mesma coisa de fazer o "@autowired" => nessa forma, o spring nao é forcado a levar um atributo com ele
	//porque ele utiliza o construtor vazio como padrão
	public JobListener(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Eu amo vc!!");
			template.query("select numero_mesa, nome_prato, quantidade, cupom, valor from Pedido",
					(rs, row) -> new Pedido(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getDouble(5)))
			.forEach(p -> System.out.println("Pedido " + p.toString()));
		}
	}
	
	
}
