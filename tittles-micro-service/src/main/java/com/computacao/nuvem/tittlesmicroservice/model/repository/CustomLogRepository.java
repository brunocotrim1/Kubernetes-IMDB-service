package com.computacao.nuvem.tittlesmicroservice.model.repository;

import com.computacao.nuvem.tittlesmicroservice.model.CustomLogs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomLogRepository
        extends ElasticsearchRepository<CustomLogs, String> {

}