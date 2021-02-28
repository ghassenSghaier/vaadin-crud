package com.example.application.service;

import com.example.application.model.dto.CandidateDTO;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.model.entity.Candidate;
import com.example.application.model.entity.JobAnnouncement;
import com.example.application.model.factory.AnnouncementFactory;
import com.example.application.model.factory.CandidateFactory;
import com.example.application.model.repository.AnnouncementRepository;
import com.example.application.model.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class CandidateService {
    
    @Autowired
    CandidateRepository candidateRepository;
    private static final Logger log = Logger.getLogger(CandidateService.class.getName());

    public CandidateDTO addCandidate(CandidateDTO jobAnnouncementDTO){
        Candidate jobAnnouncement= CandidateFactory.CandidateDTOTOCandidate(jobAnnouncementDTO);
        Candidate jobAnnouncementSaved=candidateRepository.save(jobAnnouncement);
        return CandidateFactory.CandidateTOCandidateDTO(jobAnnouncementSaved);
    }
    public void addCandidates(Collection<Candidate> JobAnnouncements){
        candidateRepository.saveAll(JobAnnouncements);
    }

    public Collection<CandidateDTO> findCandidates(){
        Collection<Candidate> JobAnnouncements= candidateRepository.findAll();
        return CandidateFactory.CandidatesToCandidateDTOs(JobAnnouncements);
    }
    public Collection<CandidateDTO> findCandidatesAllPaginated(int p, int s){
        Pageable page = PageRequest.of(p, s);
        Collection<Candidate> JobAnnouncements= candidateRepository.findAll(page).getContent();
        return CandidateFactory.CandidatesToCandidateDTOs(JobAnnouncements);
    }
    public Collection<CandidateDTO> getCandidatesByFirstname (String firstName){
        Collection<Candidate> JobAnnouncements=candidateRepository.findAllByFirstName(firstName);
        return CandidateFactory.CandidatesToCandidateDTOs(JobAnnouncements);
    }
    public int countCandidates(){
        return (int)candidateRepository.count();
    }
    public boolean pageIsEmpty(int p, int s){
        Pageable page = PageRequest.of(p, s);
        return candidateRepository.findAll(page).isEmpty();
    }
    public boolean pageIsFull(int p, int s){
        Pageable page = PageRequest.of(p, s);
        log.info("number of elements in page"+p+"="+candidateRepository.findAll(page).getNumberOfElements());
        return (candidateRepository.findAll(page).getNumberOfElements()== s);
    }
    public CandidateDTO getCandidate(String id){
        Optional<Candidate> JobAnnouncementInBase= candidateRepository.findById(id);
        if(JobAnnouncementInBase.isPresent())
        {
            CandidateDTO JobAnnouncementDTO=CandidateFactory.CandidateTOCandidateDTO(JobAnnouncementInBase.get());
            return JobAnnouncementDTO;
        }
        else
        {
            System.out.println("Il n'y a aucun JobAnnouncement dans la base avec la reférence "+id+" !");
            return null;
        }
    }

    public void updateCandidate(CandidateDTO candidatedto){
        //vérification de l'existence de JobAnnouncement
        Candidate candidate=CandidateFactory.CandidateDTOTOCandidate(candidatedto);
        candidateRepository.save(candidate);
    }
    public void deleteCandidate(String id){
        Candidate JobAnnouncement=candidateRepository.findById(id).get();
//        if(JobAnnouncement.getApplications().size()>0){
//            System.out.println("Le JobAnnouncement que vous êtes en train de supprimer est en liaison avec une ou plusieurs applications !");
//        }else
            candidateRepository.deleteById(id);
    }

}

