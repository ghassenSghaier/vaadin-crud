package com.example.application.model.factory;

import com.example.application.model.dto.CandidateDTO;
import com.example.application.model.entity.Candidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CandidateFactory {
        public static Candidate CandidateDTOTOCandidate(CandidateDTO CandidateDTO) {
            Candidate Candidate = new Candidate();
            Candidate.setId(CandidateDTO.getCandidateId());
            Candidate.setFirstName(CandidateDTO.getFirstName());
            Candidate.setLastName(CandidateDTO.getLastName());
            Candidate.setEmail(CandidateDTO.getEmail());
            Candidate.setPhoneNumber(CandidateDTO.getPhone());

            return Candidate;
        }

        public static CandidateDTO CandidateTOCandidateDTO(Candidate Candidate) {
            if (Candidate != null) {
                CandidateDTO CandidateDTO = new CandidateDTO();
                CandidateDTO.setCandidateId(Candidate.getId());
                CandidateDTO.setFirstName(Candidate.getFirstName());
                CandidateDTO.setLastName(Candidate.getLastName());
                CandidateDTO.setEmail(Candidate.getEmail());
                CandidateDTO.setPhone(Candidate.getPhoneNumber());

//                if(Candidate.getApplications()!=null){
//                    CandidateDTO.setApplications(ApplicationFactory.applicationToApplicationDTOs(Candidate.getApplications()));
//                }
                return CandidateDTO;
            } else {
                return null;
            }
        }

        public static CandidateDTO lazyCandidateTOCandidateDTO(Candidate Candidate) {
            if (Candidate != null) {
                CandidateDTO CandidateDTO = new CandidateDTO();
                CandidateDTO.setCandidateId(Candidate.getId());
                CandidateDTO.setFirstName(Candidate.getFirstName());
                CandidateDTO.setLastName(Candidate.getLastName());
                CandidateDTO.setEmail(Candidate.getEmail());
                CandidateDTO.setPhone(Candidate.getPhoneNumber());
                return CandidateDTO;
            } else {
                return null;
            }
        }

        public static Collection<CandidateDTO> CandidatesToCandidateDTOs(Collection<Candidate> Candidates) {
            List<CandidateDTO> CandidateDTOs = new ArrayList<>();
            for (Candidate Candidate : Candidates) {
                CandidateDTO CandidateDTO = lazyCandidateTOCandidateDTO(Candidate);
                CandidateDTOs.add(CandidateDTO);
            }
            return CandidateDTOs;
        }
        public static Collection<Candidate> candidateDTOsToCandidates(Collection<CandidateDTO> CandidateDTOs) {
            Collection<Candidate> Candidates = new ArrayList<>();
            for (CandidateDTO CandidateDTO : CandidateDTOs) {
                Candidate Candidate = CandidateDTOTOCandidate(CandidateDTO);
                Candidates.add(Candidate);
            }
            return Candidates;
        }
}
