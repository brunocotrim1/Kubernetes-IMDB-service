package com.computacao.nuvem.tittlesmicroservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "custom_logging")
@Data
@Builder
public class CustomLogs {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "nivelLog")
    private String logLevel;

    @Field(type = FieldType.Text, name = "instante")
    private String timestamp;

    @Field(type = FieldType.Text, name = "mensagem")
    private String message;
}
