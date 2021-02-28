package com.example.application.service;
import com.example.application.model.dto.JobAnnouncementDTO;
//import com.example.application.model.entity.Application;
import com.example.application.model.entity.JobAnnouncement;
import com.example.application.model.factory.AnnouncementFactory;
//import com.example.application.model.factory.ApplicationFactory;
import com.example.application.model.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class AnnouncementService {
    
    @Autowired
    AnnouncementRepository announcementRepository;
    Logger log = Logger.getLogger(AnnouncementService.class.getName());

    public JobAnnouncementDTO addAnnouncement(JobAnnouncementDTO jobAnnouncementDTO){
        JobAnnouncement jobAnnouncement= AnnouncementFactory.announcementDTOToAnnouncement(jobAnnouncementDTO);
        JobAnnouncement jobAnnouncementSaved=announcementRepository.save(jobAnnouncement);
        return AnnouncementFactory.announcementToJobAnnouncementDTO(jobAnnouncementSaved);
    }
    public void addProducts(Collection<JobAnnouncement> JobAnnouncements){
        announcementRepository.saveAll(JobAnnouncements);
    }

    public Collection<JobAnnouncementDTO> findAnnouncements(){
        Collection<JobAnnouncement> JobAnnouncements= announcementRepository.findAll();
        return AnnouncementFactory.AnnouncementsToAnnouncementDTOs(JobAnnouncements);
    }
    public int countAnnouncements(){
        return (int)announcementRepository.count();
    }
    public boolean pageIsEmpty(int p, int s){
        Pageable page = PageRequest.of(p, s);
        return announcementRepository.findAll(page).isEmpty();
    }
    public boolean pageIsFull(int p, int s){
        Pageable page = PageRequest.of(p, s);
        log.info("number of elements in page"+p+"="+announcementRepository.findAll(page).getNumberOfElements());
        return (announcementRepository.findAll(page).getNumberOfElements()== s);
    }
    public Collection<JobAnnouncementDTO> findAnnouncementsPageable(int page,int size){
        Pageable p = PageRequest.of(page, size);
        Collection<JobAnnouncement> JobAnnouncements= announcementRepository.findAll(p).getContent();
        return AnnouncementFactory.AnnouncementsToAnnouncementDTOs(JobAnnouncements);
    }
    public Collection<JobAnnouncementDTO> getAnnouncementsByDes (String description){
        Collection<JobAnnouncement> JobAnnouncements=announcementRepository.findAllByDescription(description);
        return AnnouncementFactory.AnnouncementsToAnnouncementDTOs(JobAnnouncements);
    }
    public JobAnnouncementDTO getAnnouncement(Long id){
        Optional<JobAnnouncement> JobAnnouncementInBase= announcementRepository.findById(id);
        if(JobAnnouncementInBase.isPresent())
        {
            JobAnnouncementDTO JobAnnouncementDTO=AnnouncementFactory.announcementToJobAnnouncementDTO(JobAnnouncementInBase.get());
            return JobAnnouncementDTO;
        }
        else
        {
            System.out.println("Il n'y a aucun JobAnnouncement dans la base avec la reférence "+id+" !");
            return null;
        }
    }
    public Collection<JobAnnouncement> getAnnouncementsBykeyWord (String keyWord){
        return announcementRepository.findAllByDescriptionContaining(keyWord);
    }
    public void updateAnnouncement(JobAnnouncementDTO jobAnnouncement){
        //vérification de l'existence de JobAnnouncement
        JobAnnouncement announcement= AnnouncementFactory.announcementDTOToAnnouncement(jobAnnouncement);
        announcementRepository.save(announcement);
    }
    public void deleteAnnouncement(Long id){
        JobAnnouncement JobAnnouncement=announcementRepository.findById(id).get();
//        if(JobAnnouncement.getApplications().size()>0){
//            System.out.println("Le JobAnnouncement que vous êtes en train de supprimer est en liaison avec une ou plusieurs lignes de commande !");
//        }else
            announcementRepository.deleteById(id);
    }
}

