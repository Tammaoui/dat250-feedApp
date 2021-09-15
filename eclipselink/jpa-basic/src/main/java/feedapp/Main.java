package feedapp;

import entities.Account;
import entities.Poll;
import entities.PollChoice;
import service.AccountDao;
import service.PollChoiceDao;
import service.PollDao;

import java.util.ArrayList;


public class Main {

    static PollChoiceDao pollChoiceDao = new PollChoiceDao();
    static AccountDao accountDao = new AccountDao();
    static PollDao pollDao = new PollDao();


    public static void main(String[] args) {

        // Create test account
        Account account = new Account();
        account.setEmail("TestAccount@gmail.com");
        account.setFirstname("Test");
        account.setEmail("Test");
        accountDao.persist(account);

        // Create test poll
        Poll poll = new Poll();
        poll.setTitle("Test pool");
        poll.setCreatedBy(account);
        pollDao.persist(poll);

        // Create test poll choices
        String[] testOptions = {"Option 1", "Option 2", "Option 3"};
        for (String option: testOptions) {
            PollChoice choice = new PollChoice();
            choice.setChoiceText(option);
            choice.setPoll(poll);
            pollChoiceDao.persist(choice);
        }

        ArrayList<PollChoice> choices = pollChoiceDao.getAll();
        for (PollChoice choice: choices) {
            System.out.println(choice);
        }
    }
}
