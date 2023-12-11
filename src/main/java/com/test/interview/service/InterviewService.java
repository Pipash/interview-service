package com.test.interview.service;

import com.test.interview.model.Interview;
import com.test.interview.payload.InterviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    public List<Interview> getAllSlot();

    public Interview createInterviewSlot(InterviewRequest request);

    public Optional<Interview> getSingleSlot(Integer id);

    public Interview updateInterviewSlot(Integer id, InterviewRequest request);

    public void deleteInterviewSlot(Integer id);
}
