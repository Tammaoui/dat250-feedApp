import com.fasterxml.jackson.databind.ObjectMapper;
import feedapp.controllers.AccountController;
import feedapp.entities.Account;
import feedapp.service.AccountDao;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    AccountDao accountDao;

     Account ACCOUNT_1;
    {
        ACCOUNT_1.setEmail("USER_1@email.com");
        ACCOUNT_1.setFirstname("Firstname_1");
        ACCOUNT_1.setLastname("Lastname_1");
        ACCOUNT_1.setPassword("Password_1");
        accountDao.persist(ACCOUNT_1);
    }

    Account ACCOUNT_2;
    {
        ACCOUNT_2.setEmail("USER_2@email.com");
        ACCOUNT_2.setFirstname("Firstname_2");
        ACCOUNT_2.setLastname("Lastname_2");
        ACCOUNT_2.setPassword("Password_2");
        accountDao.persist(ACCOUNT_2);
    }

    Account ACCOUNT_3;
    {
        ACCOUNT_3.setEmail("USER_3@email.com");
        ACCOUNT_3.setFirstname("Firstname_3");
        ACCOUNT_3.setLastname("Lastname_3");
        ACCOUNT_3.setPassword("Password_3");
        accountDao.persist(ACCOUNT_3);
    }

    @Test
    public void getAllAccounts() throws Exception {
        List<Account> accountList = new ArrayList<>(Arrays.asList(ACCOUNT_1, ACCOUNT_2, ACCOUNT_3));
        Mockito.when(accountDao.getAll()).thenReturn(accountList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstname", is("Firstname_1")))
                .andExpect(jsonPath("$[1].firstname", is("Firstname_2")))
                .andExpect(jsonPath("$[2].firstname", is("Firstname_3")));
    }
}
