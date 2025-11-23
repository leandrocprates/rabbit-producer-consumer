package com.example.producer.controller;


import com.example.producer.dto.UsuarioDto;
import com.example.producer.sender.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

    @Autowired
    private QueueSender queueSender ;

    /**
     * {
     *     "id":10,
     *     "nome":"Leandro" ,
     *     "telefone":"11999999999"
     * }
     * @param usuarioDto
     */
    @PostMapping("/enviar")
    public void enviarMensagemMq(@RequestBody UsuarioDto usuarioDto ){
        queueSender.send(usuarioDto);
    }

}
