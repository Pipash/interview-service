package com.test.interview.serviceImpl;

import com.test.interview.exceptions.APIException;
import com.test.interview.model.Interview;
import com.test.interview.payload.InterviewRequest;
import com.test.interview.repository.InterviewRepository;
import com.test.interview.service.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final static Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

    @Autowired
    private InterviewRepository interviewRepository;

    @Override
    public List<Interview> getAllSlot() {
        logger.info("starting getAllSlot inside InterviewServiceImpl to view list of interview slots:");
        return interviewRepository.findAll();
    }

    @Override
    public Interview createInterviewSlot(InterviewRequest request) {
        logger.info("starting createInterviewSlot inside InterviewServiceImpl to create of interview slot:");
        return interviewRepository.save(new Interview(request.getApplicantName(), request.getApplicantEmail(), request.getInterviewTime()));
    }

    @Override
    public Optional<Interview> getSingleSlot(Integer id) {
        logger.info("starting getSingleSlot inside InterviewServiceImpl to get single interview slot:");
        return interviewRepository.findById(id);
    }

    @Override
    public Interview updateInterviewSlot(Integer id, InterviewRequest request) {
        logger.info("starting updateInterviewSlot inside InterviewServiceImpl to update single interview slot:");
        return interviewRepository.findById(id).map(interview -> {
            interview.setApplicantName(request.getApplicantName());
            interview.setApplicantEmail(request.getApplicantEmail());
            interview.setInterviewTime(request.getInterviewTime());
            return interview;
        }).orElseThrow(() -> new APIException("data not found with given id"));
    }

    @Override
    public void deleteInterviewSlot(Integer id) {
        logger.info("starting deleteInterviewSlot inside InterviewServiceImpl to delete single interview slot:");
        if (interviewRepository.findById(id).isEmpty()) {
            throw new APIException("data not found with given id");
        }
        interviewRepository.deleteById(id);
    }
}
