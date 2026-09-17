package com.KariyerYolu.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KariyerYolu.demo.entity.JobAdvertisement;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Long> {

        List<JobAdvertisement> findByEmployerId(Long employerId);

}
