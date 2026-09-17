package com.KariyerYolu.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.KariyerYolu.demo.document.JobDocument;

public interface JobElasticRepository extends ElasticsearchRepository<JobDocument, String> {
    
    List<JobDocument> findByTitleContainingIgnoreCase(String keyword);

}
