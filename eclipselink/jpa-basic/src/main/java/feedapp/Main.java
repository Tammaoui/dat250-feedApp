package feedapp;

import entities.PollChoice;
import services.PollChoiceDao;


public class Main {

    static PollChoiceDao pollChoiceDao = new PollChoiceDao();

    public static void main(String[] args) {
        PollChoice choice = new PollChoice();
        choice.setChoiceText("Hvilken hund har du lyst på?");
        pollChoiceDao.persist(choice);

        System.out.println(choice.getId());
        System.out.println(pollChoiceDao.find(choice.getId()));

        pollChoiceDao.remove(choice);
    }
}
