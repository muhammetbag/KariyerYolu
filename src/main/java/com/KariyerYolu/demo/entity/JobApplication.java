package com.KariyerYolu.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
 
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@RequiredArgsConstructor 
public class JobApplication {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne 
    @JoinColumn (name = "job_advertisement_id")
    private JobAdvertisement jobAdvertisement;

    private LocalDateTime applicationDate= LocalDateTime.now();

    private String status= "BEKLEMEDE";
    
}
