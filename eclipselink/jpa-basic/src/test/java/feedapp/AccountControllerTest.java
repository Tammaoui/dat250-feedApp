package feedapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import feedapp.controllers.AccountController;
import feedapp.entities.Account;
import feedapp.entities.Poll;
import feedapp.entities.PostAccountDto;
import feedapp.service.AccountDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    AccountDao accountDao;

    Account ACCOUNT_1;
    Account ACCOUNT_2;
    Account ACCOUNT_3;



    @BeforeEach
    void setUp() {
        ACCOUNT_1.setEmail("RayenYor@mail.com");
        ACCOUNT_1.setFirstname("Rayven");
        ACCOUNT_1.setLastname("Yor");
        ACCOUNT_1.setPassword("Password");

        ACCOUNT_2.setEmail("DavidLandrup@mail.com");
        ACCOUNT_2.setFirstname("David");
        ACCOUNT_2.setLastname("Landrup");
        ACCOUNT_2.setPassword("Password");

        ACCOUNT_3.setEmail("JaneDoe@mail.com");
        ACCOUNT_3.setFirstname("Jane");
        ACCOUNT_3.setLastname("Doe");
        ACCOUNT_3.setPassword("Password");

        accountDao.persist(ACCOUNT_1);
        accountDao.persist(ACCOUNT_2);
        accountDao.persist(ACCOUNT_3);
    }

    @AfterEach
    void deleteSetUp() {
        accountDao.remove(ACCOUNT_1);
        accountDao.remove(ACCOUNT_2);
        accountDao.remove(ACCOUNT_3);

    }

    @Test
    public void create_user() throws Exception {

        PostAccountDto.PostAccountDtoBuilder account = PostAccountDto.builder()
                        .email("JohnDoe@gmail.com")
                        .firstname("John")
                        .lastname("Doe")
                        .password("SecretPassword");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/accounts/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(account));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.firstname", is("John")));
    }

    @Test
    public void getAllAccounts_Empty_list() throws Exception {
        ArrayList<Account> accounts = new ArrayList<>();

        Mockito.when(accountDao.getAll()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    /*
    @Test
    public void getAllAccounts() throws Exception {


        }

        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(ACCOUNT_1, ACCOUNT_2, ACCOUNT_3));
        System.out.println(accounts);

        Mockito.when(accountDao.getAll()).thenReturn(accounts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].firstname", is("David")))
                .andExpect(jsonPath("$[1].lastname", is("Landrup")));

        accountDao.remove(ACCOUNT_1);
        accountDao.remove(ACCOUNT_2);
        accountDao.remove(ACCOUNT_3);
    }
    */
}
