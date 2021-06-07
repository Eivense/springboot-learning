package eivense.springboot.learning.controller;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitController implements BaseController {



    public AmqpTemplate amqpTemplate;

    public AmqpAdmin amqpAdmin;


    @Autowired
    public RabbitController(AmqpTemplate amqpTemplate, AmqpAdmin amqpAdmin) {
        this.amqpTemplate = amqpTemplate;
        this.amqpAdmin = amqpAdmin;
    }



}
