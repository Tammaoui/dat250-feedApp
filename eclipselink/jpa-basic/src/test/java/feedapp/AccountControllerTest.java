package feedapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import feedapp.controllers.AccountController;
import feedapp.entities.Account;
import feedapp.service.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    AccountDao accountDao;

    @Test
    public void getAllAccounts_Empty_list() throws Exception {
        ArrayList<Account> accounts = new ArrayList<>();

        Mockito.when(accountDao.getAll()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAccount_by_ID() throws Exception {

        Account ACCOUNT_1 = new Account();
        {
            ACCOUNT_1.setEmail("User1@mail.com");
            ACCOUNT_1.setFirstname("Firstname 1");
            ACCOUNT_1.setLastname("Lastname 1");
            ACCOUNT_1.setPassword("Password");
            accountDao.persist(ACCOUNT_1);
        }

        System.out.println(ACCOUNT_1);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(ACCOUNT_1));

        Mockito.when(accountDao.find(ACCOUNT_1.getId())).thenReturn(accounts.get(1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts/%d", ACCOUNT_1.getId())
                .contentType(MediaType.APPLICATION_JSON));

        accountDao.remove(ACCOUNT_1);
    }


}
