package com.KariyerYolu.demo.service;

import org.springframework.stereotype.Service;

import com.KariyerYolu.demo.dto.ApplicationJob.ApplicationRequest;
import com.KariyerYolu.demo.entity.JobAdvertisement;
import com.KariyerYolu.demo.entity.JobApplication;
import com.KariyerYolu.demo.entity.User;
import com.KariyerYolu.demo.repository.JobAdvertisementRepository;
import com.KariyerYolu.demo.repository.JobApplicationRepository;
import com.KariyerYolu.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;


    public String applyForJob(ApplicationRequest request) {
        // Kullanıcının başvurduğu iş ilanı ve kullanıcı var mı kontrol et
        if (jobApplicationRepository.existsByUserIdAndJobAdvertisementId(request.userId(), request.jobId())) {
            throw new RuntimeException("Hata: Bu ilana zaten başvurdunuz!");
        }

        // Kural 2: Verilen ID'ye sahip bir Kullanıcı gerçekten var mı?  
         User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Hata: Kullanıcı bulunamadı!"));

        // Kural 3: Verilen ID'ye sahip bir iş ilanı gerçekten var mı?
      JobAdvertisement job = jobAdvertisementRepository.findById(request.jobId())
                .orElseThrow(() -> new RuntimeException("Hata: İlan bulunamadı!"));

        JobApplication jobApplication = new JobApplication();
        jobApplication.setUser(user);
        jobApplication.setJobAdvertisement(job);

        jobApplicationRepository.save(jobApplication);

        return "Başvuru başarılı!";


    }
}


