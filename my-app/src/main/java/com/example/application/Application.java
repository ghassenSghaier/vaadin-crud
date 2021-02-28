package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

import java.util.Date;
import java.util.logging.Logger;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = Logger.getLogger(SpringBootServletInitializer.class.getName());

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

//    @Bean
//    public CommandLineRunner loadData(CandidateRepository repoA, AnnouncementRepository repoB) {
//        return (args) -> {
//            // save a couple of customers
//            repoA.save(new Candidate("08856975","ghassen","sghaier","medghassen.sghaier@hotmail.com","96393116"));
//            repoA.save(new Candidate("08856974","salah","moussa","medghassen.sghaier@hotmail.com","96393116"));
//            repoA.save(new Candidate("08856971","ali","ss","medghassen.sghaier@hotmail.com","96393116"));
//            repoA.save(new Candidate("08856900","romthane","ben mohamed","medghassen.sghaier@hotmail.com","96393116"));
//
//            repoB.save(new JobAnnouncement(0L,"umanlink","software developer",new Date(),new Date()," description"));
//            repoB.save(new JobAnnouncement(1L,"umanlink","software developer",new Date(),new Date()," description"));
//            repoB.save(new JobAnnouncement(2L,"umanlink","software developer",new Date(),new Date()," description"));
//            repoB.save(new JobAnnouncement(3L,"umanlink","software developer",new Date(),new Date()," description"));
//
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Candidate candidate : repoA.findAll()) {
//                log.info(candidate.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Candidate candidate = repoA.findById("08856975").get();
//            log.info("Customer found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(candidate.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//            log.info("--------------------------------------------");
//            for (Candidate bauer : repoA
//                    .findByLastNameStartsWithIgnoreCase("Bauer")) {
//                log.info(bauer.toString());
//            }
//            log.info("");
//        };
//    }

}
