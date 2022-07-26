package com.nttdata.bootcamp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Client {

    @Id
    private String id;
    private String fullName; // Nombre completo
    private Long numberIdentity; // Numero de identidad
    private String typeClient; // tipo de client
    private String typeProfile;
    private Integer phoneNumber;
    private String emailClient;

}
