package co.com.bancolombia.events.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
  @Value("${adapter.rabbit.create.queue}")
  private String createQueue;
  @Value("${adapter.rabbit.update.queue}")
  private String updateQueue;
  @Value("${adapter.rabbit.update.exchange}")
  private String updateExchange;
  @Value("${adapter.rabbit.create.exchange}")
  private String createExchange;
  @Value("${adapter.rabbit.create.routingkey}")
  private String createRoutingKey;
  @Value("${adapter.rabbit.update.routingkey}")
  private String updateRoutingKey;


  @Bean
  public Queue queueUpdate() {
    return new Queue(updateQueue, true);
  }
  
  @Bean
  public Queue queueCreate() {
    return new Queue(createQueue, true);
  }



  @Bean
  public TopicExchange topicExchangeCreate() {
    return new TopicExchange(createExchange);
  }

  @Bean
  public TopicExchange topicExchangeUpdate() {
    return new TopicExchange(updateExchange);
  }

  @Bean
  public Binding bindingUpdate(@Qualifier("queueUpdate") Queue queueUpdate, @Qualifier("topicExchangeUpdate") TopicExchange topicExchange) {
    return BindingBuilder.bind(queueUpdate).to(topicExchange).with(updateRoutingKey);
  }

  @Bean
  public Binding bindingCreate(@Qualifier("queueCreate") Queue queueCreate, @Qualifier("topicExchangeCreate") TopicExchange topicExchange) {
    return BindingBuilder.bind(queueCreate).to(topicExchange).with(createRoutingKey);
  }


  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }
}