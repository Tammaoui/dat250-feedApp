package feedapp.controllers;

import feedapp.entities.Poll;
import feedapp.entities.PollChoice;
import feedapp.entities.PollChoiceDto;
import feedapp.service.PollChoiceDao;
import feedapp.service.PollDao;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PollChoiceController {

    static PollChoiceDao pollChoiceDao = new PollChoiceDao();
    static PollDao pollsDao = new PollDao();

    @GetMapping("/pollChoices")
    public List<PollChoice> all(HttpServletResponse response) {
        List<PollChoice> pollChoices = pollChoiceDao.getAll();
        if(pollChoices.isEmpty())
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return pollChoices;
    }

    @GetMapping("/pollChoices/{id}")
    public PollChoice get(@PathVariable Long id, HttpServletResponse response) {
        PollChoice pollChoice = pollChoiceDao.find(id);
        if(pollChoice == null)
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return pollChoice;
    }

    @PostMapping("/pollChoices")
    public String create(@RequestBody PollChoice data, HttpServletResponse response) {
        try {
            pollChoiceDao.persist(data);
        } catch (Exception e) {
            System.out.println("Failed creating poll-choice entity. Got exception: " + e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Failed creating poll-choice.";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "Poll-choice added successfully";
    }

    @DeleteMapping("/pollChoices/{id}")
    public String delete(@PathVariable Long id, HttpServletResponse response) {
        try {
            PollChoice pollChoice = pollChoiceDao.find(id);
            if(pollChoice == null) {
                return "No poll-choice with id: " + id + " was found";
            }
            pollChoiceDao.remove(pollChoice);
            return "Poll-choice deleted successfully";

        } catch (Exception e) {
            System.out.println("Something went wrong trying to poll-choice: " + id);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }

    @PutMapping("/pollChoices/{id}")
    public String update(@PathVariable Long id, @RequestBody PollChoiceDto data, HttpServletResponse response) {
        try {
            PollChoice pollChoice = pollChoiceDao.find(id);
            if(pollChoice == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "No poll-choice with id: " + id + " was found";
            }
            Poll poll = pollsDao.find(data.pollId);
            if (poll == null) {
                return "Could not find poll with poll-id:" + data.pollId;
            }
            pollChoice.setPoll(poll);
            pollChoice.setChoiceText(data.choiceText);
            pollChoice.setVotes(data.votes);
            pollChoiceDao.persist(pollChoice);
            return "Poll-choice updated successfully.";

        }
        catch (Exception e) {
            System.out.println("Something went wrong trying to update poll-choice: " + id);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }
}
