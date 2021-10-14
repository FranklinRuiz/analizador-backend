package com.als.controller;

import com.als.model.Request;
import com.als.model.Response;
import com.als.service.AnalyzerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnalyzerController {

    @Autowired
    private AnalyzerSevice analyzerService;

    @PostMapping("lexical-analyzer")
    public Response lexicalAnalyzer(@RequestBody Request text){
        return analyzerService.lexicalAnalyzer(text);
    }

    @PostMapping("syntactic-analyzer")
    public Response syntacticAnalyzer(@RequestBody Request text){
        return analyzerService.syntacticAnalyzer(text);
    }

}
