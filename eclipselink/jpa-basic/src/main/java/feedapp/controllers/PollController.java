package feedapp.controllers;

import feedapp.entities.Poll;
import feedapp.service.PollDao;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PollController {

    static PollDao pollsDao = new PollDao();

    @GetMapping("/polls")
    public List<Poll> all(HttpServletResponse response) {
        List<Poll> polls = pollsDao.getAll();
        if(polls.isEmpty())
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return polls;
    }

    @GetMapping("/polls/{id}")
    public Poll get(@PathVariable Long id, HttpServletResponse response) {
        Poll poll = pollsDao.find(id);
        if(poll == null)
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return poll;
    }

    @PostMapping("/polls")
    public String create(@RequestBody Poll data, HttpServletResponse response) {
        try {
            pollsDao.persist(data);
        } catch (Exception e) {
            System.out.println("Failed creating poll entity. Got exception: " + e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Failed creating poll.";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "Poll created successfully";
    }

    @DeleteMapping("/polls/{id}")
    public String delete(@PathVariable Long id, HttpServletResponse response) {
        try {
            Poll pollToDelete = pollsDao.find(id);

            if(pollToDelete == null) {
                return "No poll with id: " + id + " was found";
            }

            pollsDao.remove(pollToDelete);
            return "Poll deleted successfully";

        } catch (Exception e) {
            System.out.println("Something went wrong trying to delete poll: " + id);
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }

    @PutMapping("/polls/{id}")
    public String update(@PathVariable Long id, @RequestBody Poll data, HttpServletResponse response) {
        try {
            Poll currentPoll = pollsDao.find(id);
            if(currentPoll == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "No poll with id: " + id + " was found";
            }
            currentPoll.setCreatedAt(data.getCreatedAt());
            currentPoll.setCreatedBy(data.getCreatedBy());
            currentPoll.setPublic(data.isPublic());
            currentPoll.setTitle(data.getTitle());
            currentPoll.setLastEdited(data.getLastEdited());
            pollsDao.persist(currentPoll);
            return "Poll updated successfully.";

        }
        catch (Exception e) {
            System.out.println("Something went wrong trying to update poll: " + id);
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }
}
