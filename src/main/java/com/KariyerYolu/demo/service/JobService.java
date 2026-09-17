package com.KariyerYolu.demo.service;

import com.KariyerYolu.demo.document.JobDocument;
import com.KariyerYolu.demo.dto.Job.JobCreateRequest;
import com.KariyerYolu.demo.entity.JobAdvertisement;
import com.KariyerYolu.demo.entity.User;
import com.KariyerYolu.demo.repository.JobAdvertisementRepository;
import com.KariyerYolu.demo.repository.JobElasticRepository;
import com.KariyerYolu.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    // Her iki veritabanını da servise dahil ediyoruz
    private final JobAdvertisementRepository jpaRepository;
    private final JobElasticRepository elasticRepository;
    private final UserRepository userRepository;

    
       public JobAdvertisement createJob(JobCreateRequest request) {
        
        // --- İşvereni veritabanından bul ---
        User employer = userRepository.findById(request.employerId())
                .orElseThrow(() -> new RuntimeException("Hata: İşveren bulunamadı!"));

        // 1. PostgreSQL'e kaydetmek için Entity'i dolduruyoruz
        JobAdvertisement job = new JobAdvertisement();
        job.setTitle(request.title());
        job.setDescription(request.description());
        job.setLocation(request.location());
        job.setActive(true);

        // ---İlanı bulduğumuz işverene bağlıyoruz ---
        job.setEmployer(employer); 

        // ÖNCE PostgreSQL'e kaydet (ID'si oluşsun)
        JobAdvertisement savedJob = jpaRepository.save(job);

        // 2. Elasticsearch'e kopyalamak için Document'i dolduruyoruz 
        JobDocument document = new JobDocument();
        document.setId(savedJob.getId().toString()); 
        document.setTitle(savedJob.getTitle());
        document.setDescription(savedJob.getDescription());
        document.setLocation(savedJob.getLocation());

       
        elasticRepository.save(document);

        return savedJob;
    }
        public List<JobDocument> searchJobs(String keyword) {
         return elasticRepository.findByTitleContainingIgnoreCase(keyword);
    }
     public List<JobAdvertisement> getEmployerJobs(Long employerId) {
        return jpaRepository.findByEmployerId(employerId);
    }

}