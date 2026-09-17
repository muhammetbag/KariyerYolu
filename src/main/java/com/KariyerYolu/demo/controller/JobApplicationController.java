package com.KariyerYolu.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KariyerYolu.demo.dto.ApplicationJob.ApplicationRequest;
import com.KariyerYolu.demo.service.JobApplicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin (origins = "*") 
@RestController 
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService applicationService;

        @PostMapping
    public ResponseEntity<String> apply(@RequestBody ApplicationRequest request) {
        
        String result = applicationService.applyForJob(request);
         return ResponseEntity.ok(result);
    }
    
}
