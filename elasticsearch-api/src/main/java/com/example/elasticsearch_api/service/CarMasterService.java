package com.example.elasticsearch_api.service;

import com.example.elasticsearch_api.indexer.FileUtil;
import com.example.elasticsearch_api.indexer.IndexerHelper;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMasterService {
    private final IndexerHelper indexerHelper;

    public void createIndex(String indexName) throws IOException {
        Map<String, Object> indexTemplate = new FileUtil().getFileContent("/index/car-master.json");
        indexerHelper.createIndex(indexName, indexTemplate);
    }
}
