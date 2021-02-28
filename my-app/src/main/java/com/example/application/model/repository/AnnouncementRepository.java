package com.example.application.model.repository;

//import com.example.application.model.entity.Application;
//import com.example.application.model.entity.ApplicationPK;
import com.example.application.model.entity.Candidate;
import com.example.application.model.entity.JobAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<JobAnnouncement, Long> {
    public Collection<JobAnnouncement> findAllByDescription(String description);
    public Collection<JobAnnouncement> findAllByDescriptionContaining(String description);
}