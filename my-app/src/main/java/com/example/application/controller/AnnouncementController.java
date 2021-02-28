package com.example.application.controller;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(value="/api/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @PostMapping
    public void addAnnouncement(@RequestBody JobAnnouncementDTO announcementDTO){
        announcementService.addAnnouncement(announcementDTO);
    }
    @GetMapping
    public Collection<JobAnnouncementDTO> getAnnouncements (){
        return announcementService.findAnnouncements();
    }

    @GetMapping(value="/numero/{id}")
    public JobAnnouncementDTO getAnnouncement(@PathVariable("id") Long numero){
        return announcementService.getAnnouncement(numero);
    }

    @PutMapping
    public void updateAnnouncement(@RequestBody JobAnnouncementDTO announcementDTO){
        announcementService.updateAnnouncement(announcementDTO);
    }
    @DeleteMapping("/{numero}")
    public void deleteAnnouncement (@PathVariable Long numero){
        announcementService.deleteAnnouncement(numero);
    }
}
