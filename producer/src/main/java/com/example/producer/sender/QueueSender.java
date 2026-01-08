package com.example.producer.sender;

import com.example.producer.dto.UsuarioDto;
import com.example.producer.model.UserRecord;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    static Integer  IdIncremento = 1 ;

    @Autowired
    @Qualifier("customRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("queueMQ")
    private Queue queue ;

    public void send(UsuarioDto message){
        UserRecord userRecord = new UserRecord(IdIncremento++, message.getNome(), message.getTelefone());
        System.out.println("Sending message  : " + userRecord.toString());
        rabbitTemplate.convertAndSend(this.queue.getName(),  userRecord);
    }

}
