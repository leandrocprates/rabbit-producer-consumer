package com.example.consumer.model;

import java.io.Serializable;

public record UserRecord(Integer id ,
                         String nome ,
                         String telefone ) implements Serializable {
}
