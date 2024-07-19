package com.example.elasticsearch_api.controller;

import com.example.elasticsearch_api.service.CarMasterService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarMasterController {

    private final CarMasterService carMasterService;

    @GetMapping("/creat-index")
    public void create(@RequestParam String indexName) throws IOException {
        carMasterService.createIndex(indexName);
    }


}
