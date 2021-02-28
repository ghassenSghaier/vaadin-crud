package com.example.application.component;

import com.example.application.model.dto.CandidateDTO;
import com.example.application.model.dto.JobAnnouncementDTO;
import com.example.application.model.entity.Candidate;
import com.example.application.model.entity.JobAnnouncement;
import com.example.application.model.repository.AnnouncementRepository;
import com.example.application.model.repository.CandidateRepository;
import com.example.application.service.AnnouncementService;
import com.example.application.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.Date;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = Logger.getLogger(SetupDataLoader.class.getName());
    CandidateService repoA;
    AnnouncementService repoB;

    private boolean alreadySetup = false;

    public SetupDataLoader(CandidateService repoA, AnnouncementService repoB) {
        this.repoA = repoA;
        this.repoB = repoB;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        // save a couple of customers
        repoA.addCandidate(new CandidateDTO("08856975","ghassen","sghaier","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08856974","salah","moussa","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08856971","ali","ss","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08856900","romthane","ben mohamed","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08856955","ghassen","sghaier","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08856174","salah","moussa","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08851971","ali","ss","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08846900","romthane","ben mohamed","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("08356975","ghassen","sghaier","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("02856974","salah","moussa","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("18856971","ali","ss","medghassen.sghaier@hotmail.com","96393116"));
        repoA.addCandidate(new CandidateDTO("58856900","romthane","ben mohamed","medghassen.sghaier@hotmail.com","96393116"));

        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));
        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));
        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));
        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));
        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));
        repoB.addAnnouncement(new JobAnnouncementDTO("umanlink","software developer",LocalDate.of(2020,01,01),LocalDate.of(2020,01,01)," description"));



//        // fetch all customers
//        log.info("Customers found with findAll():");
//        log.info("-------------------------------");
//        for (Candidate candidate : repoA.findAll()) {
//            log.info(candidate.toString());
//        }
//        log.info("");
//
//        // fetch an individual customer by ID
//        Candidate candidate = repoA.findById("08856975").get();
//        log.info("Customer found with findOne(1L):");
//        log.info("--------------------------------");
//        log.info(candidate.toString());
//        log.info("");
//
//        // fetch customers by last name
//        log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//        log.info("--------------------------------------------");
//        for (Candidate bauer : repoA
//                .findByLastNameStartsWithIgnoreCase("Bauer")) {
//            log.info(bauer.toString());
//        }
//        log.info("");

        alreadySetup = true;
    }
}

