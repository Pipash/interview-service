package com.test.interview.serviceImpl;

import com.test.interview.model.Interview;
import com.test.interview.repository.InterviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

@ExtendWith(MockitoExtension.class)
class InterviewServiceImplTest {
    @Mock
    private InterviewRepository interviewRepository;

    private InterviewServiceImpl interviewService;

    private RedisTemplate<String, Interview> redisTemplate;

    @BeforeEach
    void setUp() {
        //there is some error need to fix
        interviewService = new InterviewServiceImpl(interviewRepository, redisTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        //AutoClose can be replaced with @ExtendWith
        //autoCloseable.close();
    }

    /*@Test
    void getListByRange() {
        //when
        interviewService.getSingleSlot(1);
        //then
        Mockito.verify(interviewRepository).findById(1);
    }*/

    /*private StudentService studentService;
    private StudentRepository studentRepository;

    @Test
    public void findAll(){
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "person1", "person1@mail.com"));
        studentList.add(new Student(2, "person2", "person2@mail.com"));

        when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> expectedList = studentService.findAl();

        assertEquals(2, expectedList.size());
    }*/
}
