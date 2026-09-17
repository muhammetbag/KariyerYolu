package com.KariyerYolu.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.KariyerYolu.demo.document.JobDocument;

public interface JobElasticRepository extends ElasticsearchRepository<JobDocument, String> {
    
    // Containing yerine Matches kullanıyoruz ki boşluklu aramalarda ("Java Developer" gibi) Elasticsearch hata fırlatmasın.
    List<JobDocument> findByTitleMatches(String keyword);

}
