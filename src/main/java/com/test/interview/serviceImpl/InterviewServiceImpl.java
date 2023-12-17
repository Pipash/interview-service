package com.test.interview.serviceImpl;

import com.test.interview.exceptions.APIException;
import com.test.interview.model.Interview;
import com.test.interview.payload.InterviewRequest;
import com.test.interview.repository.InterviewRepository;
import com.test.interview.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    private final static Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

    private final InterviewRepository interviewRepository;

    private final RedisTemplate<String, Interview> redisTemplate;

    @Override
    public List<Interview> getAllSlot() {
        logger.info("starting getAllSlot inside InterviewServiceImpl to view list of interview slots:");
        return interviewRepository.findAll();
    }

    @Override
    public Interview createInterviewSlot(InterviewRequest request) {
        logger.info("starting createInterviewSlot inside InterviewServiceImpl to create of interview slot:");
        if (!interviewRepository.findAllByInterviewTime(request.getInterviewTime()).isEmpty()) {
            throw new APIException("Can not book a slot at the same date and time");
        }
        return interviewRepository.save(new Interview(request.getApplicantName(), request.getApplicantEmail(), request.getInterviewTime()));
    }

    @Override
    public Interview getSingleSlot(Integer id) {
        logger.info("starting getSingleSlot inside InterviewServiceImpl to get single interview slot:");
        String key = "slot_"+id;
        final ValueOperations<String, Interview> ops = redisTemplate.opsForValue();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            logger.info("data fetched from redis cache");
            return ops.get(key);
        }

        logger.info("fetching data from database");
        final Interview interview = interviewRepository.findById(id).orElseThrow(()-> new APIException("data not found"));

        ops.set(key, interview);
        logger.info("data added to redis");

        return interview;
    }

    @Override
    public Interview updateInterviewSlot(Integer id, InterviewRequest request) {
        final String key = "slot_"+id;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.delete(key);
            logger.info("redis data removed due to update");
        }
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
        final String key = "slot_"+id;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.delete(key);
            logger.info("redis data removed due to deletion");
        }

        logger.info("starting deleteInterviewSlot inside InterviewServiceImpl to delete single interview slot:");
        if (interviewRepository.findById(id).isEmpty()) {
            throw new APIException("data not found with given id");
        }
        interviewRepository.deleteById(id);
    }
}
