package it.amora.webfluxtest.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Product {

    @Id
    private Long id;
    private String name;
    private double price;
}
