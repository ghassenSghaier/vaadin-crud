package com.example.application.model.factory;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.model.entity.JobAnnouncement;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class AnnouncementFactory {

    public static JobAnnouncementDTO announcementToJobAnnouncementDTO(JobAnnouncement announcement) {
        JobAnnouncementDTO JobAnnouncementDTO = new JobAnnouncementDTO();
        JobAnnouncementDTO.setAnnouncementId(announcement.getId());
        JobAnnouncementDTO.setCompany(announcement.getCompany());
        JobAnnouncementDTO.setPosition(announcement.getJobTitle());
        JobAnnouncementDTO.setDescription(announcement.getDescription());
        JobAnnouncementDTO.setStartDate(announcement.getStartDate());
        JobAnnouncementDTO.setEndDate(announcement.getEndDate());
        return JobAnnouncementDTO;

    }

    public static JobAnnouncement announcementDTOToAnnouncement(JobAnnouncementDTO JobAnnouncementDTO) {
        JobAnnouncement announcement = new JobAnnouncement();
        announcement.setId(JobAnnouncementDTO.getAnnouncementId());
        announcement.setCompany(JobAnnouncementDTO.getCompany());
        announcement.setJobTitle(JobAnnouncementDTO.getPosition());
        announcement.setDescription(JobAnnouncementDTO.getDescription());
        announcement.setStartDate(JobAnnouncementDTO.getStartDate());
        announcement.setEndDate(JobAnnouncementDTO.getEndDate());
        return announcement;

    }

    public static Collection<JobAnnouncementDTO> AnnouncementsToAnnouncementDTOs (Collection<JobAnnouncement> announcements){
        Collection<JobAnnouncementDTO> JobAnnouncementDTOs =new ArrayList<>();
        for(JobAnnouncement announcement : announcements){
            JobAnnouncementDTO JobAnnouncementDTO= announcementToJobAnnouncementDTO(announcement);
            JobAnnouncementDTOs.add(JobAnnouncementDTO);
        }
        return JobAnnouncementDTOs;
    }
}