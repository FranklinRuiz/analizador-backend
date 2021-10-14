package com.als.service;

import com.als.model.Request;
import com.als.model.Response;

public interface AnalyzerSevice {
    Response lexicalAnalyzer(Request texto);
    Response syntacticAnalyzer(Request texto);
}
