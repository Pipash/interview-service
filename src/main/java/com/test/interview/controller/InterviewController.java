package com.test.interview.controller;

import com.test.interview.component.ServiceResponse;
import com.test.interview.model.Interview;
import com.test.interview.payload.InterviewRequest;
import com.test.interview.service.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/interview")
public class InterviewController {
    private final static Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/booking-list")
    public ResponseEntity<ServiceResponse> getSlotList() {
        logger.info("start of getSlotList inside controller InterviewController:");
        List<Interview> interviews = interviewService.getAllSlot();
        if (interviews.isEmpty()) {
            return new ResponseEntity<>(new ServiceResponse("data not found", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ServiceResponse("data found", interviews), HttpStatus.OK);
    }

    @PostMapping("/book-slot")
    public ResponseEntity<ServiceResponse> createSlot(@Valid @RequestBody InterviewRequest request) {
        logger.info("start of createSlot inside controller InterviewController:");
        Interview interview = interviewService.createInterviewSlot(request);
        return new ResponseEntity<>(new ServiceResponse("created", interview), HttpStatus.CREATED);
    }

    @GetMapping("/view-booking/{id}")
    public ResponseEntity<ServiceResponse> getSlot(@PathVariable Integer id) {
        logger.info("start of getSlot inside controller InterviewController:");
        Optional<Interview> interview = interviewService.getSingleSlot(id);
        return new ResponseEntity<>(new ServiceResponse("single data", interview), HttpStatus.OK);
    }

    @PutMapping("/edit-booking/{id}")
    public ResponseEntity<ServiceResponse> updateSlot(@PathVariable Integer id, @Valid @RequestBody InterviewRequest request) {
        logger.info("start of updateSlot inside controller InterviewController:");
        Interview interview = interviewService.updateInterviewSlot(id, request);
        return new ResponseEntity<>(new ServiceResponse("update data", interview), HttpStatus.OK);
    }

    @DeleteMapping("/delete-booking/{id}")
    public ResponseEntity<ServiceResponse> deleteSlot(@PathVariable Integer id) {
        logger.info("start of deleteSlot inside controller InterviewController:");
        interviewService.deleteInterviewSlot(id);
        return new ResponseEntity<>(new ServiceResponse("data deleted", null), HttpStatus.OK);
    }

}
