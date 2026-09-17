package com.KariyerYolu.demo.repository;
import com.KariyerYolu.demo.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;



public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
 
    boolean existsByUserIdAndJobAdvertisementId(Long userId, Long jobId);
}