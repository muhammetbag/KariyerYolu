package com.KariyerYolu.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KariyerYolu.demo.document.JobDocument;
import com.KariyerYolu.demo.dto.Job.JobCreateRequest;
import com.KariyerYolu.demo.entity.JobAdvertisement;
import com.KariyerYolu.demo.service.JobService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin (origins = "*")
@RestController 
@RequestMapping ("/api/jobs")
@RequiredArgsConstructor 
public class JobController {

    private final JobService jobService;

    @PostMapping 
    public ResponseEntity<JobAdvertisement> createJob(@RequestBody JobCreateRequest request) {
        JobAdvertisement createdJob = jobService.createJob(request);
        return ResponseEntity.ok(createdJob);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobDocument>> searchJobs(@RequestParam String keyword) {

        List<JobDocument> results = jobService.searchJobs(keyword);
        return ResponseEntity.ok(results);
    }
    
     @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobAdvertisement>> getEmployerJobs(@PathVariable Long employerId) {
        
        List<JobAdvertisement> jobs = jobService.getEmployerJobs(employerId);
        return ResponseEntity.ok(jobs);
    }


}
