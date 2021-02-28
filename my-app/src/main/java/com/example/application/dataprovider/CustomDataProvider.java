package com.example.application.dataprovider;

import com.example.application.model.dto.CandidateDTO;
import com.example.application.service.CandidateService;
import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.stereotype.Service;

public interface CustomDataProvider<D,S extends Service> {

     DataProvider<D, Void> create(S s);
}
