package com.test.interview.serviceImpl;

import com.test.interview.repository.InterviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InterviewServiceImplTest {
    @Mock
    private InterviewRepository interviewRepository;

    private InterviewServiceImpl interviewService;

    @BeforeEach
    void setUp() {
        //there is some error need to fix
        //interviewService = new InterviewServiceImpl(interviewRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        //AutoClose can be replaced with @ExtendWith
        //autoCloseable.close();
    }

    @Test
    void getListByRange() {
        //when
        interviewService.getSingleSlot(1);
        //then
        Mockito.verify(interviewRepository).findById(1);
    }
}
