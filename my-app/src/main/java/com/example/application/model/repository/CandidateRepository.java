package com.example.application.model.repository;
import com.example.application.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
    public Collection<Candidate> findAllByFirstName(String firstName);
    public Collection<Candidate> findAllByLastName(String lastname);
    public Collection<Candidate> findByLastNameStartsWithIgnoreCase(String lastname);
    public Collection<Candidate> findAllByFirstNameContaining(String keyWord);
    public Collection<Candidate> findAllByLastNameContaining(String keyWord);
}