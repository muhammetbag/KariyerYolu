package com.KariyerYolu.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.Data;

@Data
@Document(indexName = "jobs") 
public class JobDocument {

    @Id 
    private String id; 

    
    @Field(type = FieldType.Text, analyzer = "standard") 
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard") 
    private String description;

    @Field(type = FieldType.Keyword) 
    private String location;
}