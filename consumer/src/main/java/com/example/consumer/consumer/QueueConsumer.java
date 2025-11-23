package com.example.consumer.consumer;

import com.example.consumer.model.UserRecord;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"}, ackMode = "MANUAL")
    public void consumeMessage(UserRecord mensagem, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws Exception {

        try{
            if ( (mensagem.id() % 2) == 0 ){
                //throw new Exception("Erro");
            }

            channel.basicAck(deliveryTag, false);
            System.out.println("Consumindo mensagem: ");
            System.out.println(mensagem.toString());

        }catch(Exception ex){
            channel.basicNack(deliveryTag, false, true); // Requeue the message
            throw new Exception(ex);
        }

    }
}
