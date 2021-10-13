package feedapp.controllers;

import feedapp.entities.Account;
import feedapp.service.AccountDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    AccountDao accountDao;

    Account ACCOUNT_1;
    Account ACCOUNT_2;
    Account ACCOUNT_3;

    @BeforeEach
    void setUp() {
        ACCOUNT_1.setEmail("JohnDoe@gmail.com");
        ACCOUNT_1.setFirstname("John");
        ACCOUNT_1.setLastname("Doe");
        ACCOUNT_1.setPassword("SecretPassword");

        ACCOUNT_2.setEmail("RayvenYor@gmail.com");
        ACCOUNT_2.setFirstname("Rayven");
        ACCOUNT_2.setLastname("Yor");
        ACCOUNT_2.setPassword("SecretPa$$word");

        ACCOUNT_3.setEmail("DavidLandrup@gmail.com");
        ACCOUNT_3.setFirstname("David");
        ACCOUNT_3.setLastname("Landrup");
        ACCOUNT_3.setPassword("S@cretPassw0rd");

        accountDao.persist(ACCOUNT_1);
        accountDao.persist(ACCOUNT_2);
        accountDao.persist(ACCOUNT_3);
    }

    @AfterEach
    void tearDown() {
        accountDao.remove(ACCOUNT_1);
        accountDao.remove(ACCOUNT_2);
        accountDao.remove(ACCOUNT_3);
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