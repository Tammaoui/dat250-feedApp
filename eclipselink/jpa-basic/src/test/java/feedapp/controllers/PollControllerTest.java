package feedapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import feedapp.entities.Account;
import feedapp.entities.Poll;
import feedapp.service.AccountDao;
import feedapp.service.PollDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Calendar;


class PollControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    PollDao pollDao;
    AccountDao accountDao;

    Account ACCOUNT_1;
    Poll POLL_1;

    @BeforeEach
    void setUp() {
        ACCOUNT_1.setEmail("RayenYor@mail.com");
        ACCOUNT_1.setFirstname("Rayven");
        ACCOUNT_1.setLastname("Yor");
        ACCOUNT_1.setPassword("Password");
        accountDao.persist(ACCOUNT_1);

        POLL_1.setTitle("Poll 1 Test");
        POLL_1.setActive(true);
        POLL_1.setPublic(true);
        POLL_1.setCreatedBy(ACCOUNT_1);
        POLL_1.setLastEdited(new Date(2021, Calendar.OCTOBER, 11));
        POLL_1.setCreatedAt(new Date(2021, Calendar.SEPTEMBER, 28));
        pollDao.persist(POLL_1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void all() {
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}