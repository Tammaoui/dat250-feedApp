package feedapp;

import entities.PollChoice;
import services.JpaDao;
import services.PollChoiceDao;


public class Main {

    static PollChoiceDao pollDao = new PollChoiceDao();

    public static void main(String[] args) {

        PollChoice choice = new PollChoice();
        pollDao.persist(choice);
    }
}
