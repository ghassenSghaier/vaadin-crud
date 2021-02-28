package com.example.application.controller;

import com.example.application.model.dto.CandidateDTO;
import com.example.application.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/api/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @PostMapping
    public void addCan(@RequestBody CandidateDTO CandidateDTO){
        candidateService.addCandidate(CandidateDTO);
    }
    @GetMapping
    public Collection<CandidateDTO> getCandidates (){
        return candidateService.findCandidates();
    }

    @GetMapping(value="/cin/{id}")
    public CandidateDTO getCandidate(@PathVariable("id") String numero){
        return candidateService.getCandidate(numero);
    }
    @GetMapping(value="/{page}/{size}")
    public Collection<CandidateDTO> getCandidate(@PathVariable("page") int page, @PathVariable("size") int size){
        return candidateService.findCandidatesAllPaginated(page,size);
    }
    @PutMapping
    public void updateCandidate(@RequestBody CandidateDTO candidateDTO){
        candidateService.updateCandidate(candidateDTO);
    }
    @DeleteMapping("/{cin}")
    public void deleteCandidate (@PathVariable String cin){
        candidateService.deleteCandidate(cin);
    }
}

