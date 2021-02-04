package com.micheric.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.micheric.batch.model.Pedido;
import com.micheric.batch.processor.PedidoItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory job;

	@Autowired
	public StepBuilderFactory step;

	@Bean
	public FlatFileItemReader<Pedido> reader() {

		return new FlatFileItemReaderBuilder<Pedido>()
				.name("readerPedido")
				.resource(new ClassPathResource("arquivo.csv")).delimited()
				.names(new String [] {"numeroMesa", "nomePrato", "quantidade", "cupom", "valor"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Pedido>() {
					{
						setTargetType(Pedido.class);
					}
				}).build();

	}

	@Bean
	public PedidoItemProcessor processor() {
		return new PedidoItemProcessor();
	}
	
	
	@Bean
	public JdbcBatchItemWriter<Pedido> writer(DataSource dataSource){
		
		return new JdbcBatchItemWriterBuilder<Pedido>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Pedido>())  //todos os parametros que estao sendo passados serao buscados na classe de pedidos
				.sql("insert into Pedido (numero_mesa, nome_prato, quantidade, cupom, valor) values (:numeroMesa, :nomePrato, :quantidade, :cupom, :valor)")
				.dataSource(dataSource)
				.build();
		
	}
	
	
	@Bean
	public Job importaPedido(JobListener listener, Step step) {
		
		return job
				.get("importaPedido")
				.listener(listener)
				.incrementer(new RunIdIncrementer())
				.flow(step)
				.end()
				.build();
	}
	
	@Bean
	public Step step(JdbcBatchItemWriter<Pedido> writer) {
		return step.get("step")
				.<Pedido,Pedido>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

}
