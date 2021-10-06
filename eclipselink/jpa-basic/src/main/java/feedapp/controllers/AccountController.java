package feedapp.controllers;

import feedapp.entities.Account;
import feedapp.service.AccountDao;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AccountController {
    /*
        TODO:
        1. Legge til validering for brukerinformasjon (fornavn skal kun være bokstaver osv.)
        2. Ikke tillate duplikate emailer (begrense gjennom entity modellen)
        3.
    */

    static AccountDao accountDao = new AccountDao();

    @GetMapping("/accounts")
    public List<Account> all(HttpServletResponse response) {
        List<Account> accounts = accountDao.getAll();
        if(accounts.isEmpty())
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return accounts;
    }

    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable Long id, HttpServletResponse response) {
        Account account = accountDao.find(id);
        if(account == null)
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return account;
    }

    @PostMapping("/accounts")
    public String create(@RequestBody Account data, HttpServletResponse response) {
        try {
            accountDao.persist(data);
        } catch (Exception e) {
            System.out.println("Failed creating user entity. Got exception: " + e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Failed creating user.";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "User created successfully";
    }

    @DeleteMapping("/accounts/{id}")
    public String delete(@PathVariable Long id, HttpServletResponse response) {
        try {
            Account accountToDelete = accountDao.find(id);

            if(accountToDelete == null) {
                return "No account with id: " + id + " was found";
            }

            accountDao.remove(accountToDelete);
            return "Account deleted successfully";

        } catch (Exception e) {
            System.out.println("Something went wrong trying to delete user: " + id);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }

    @PutMapping("/accounts/{id}")
    public String update(@PathVariable Long id, @RequestBody Account data, HttpServletResponse response) {
        try {
            Account currentUser = accountDao.find(id);
            if(currentUser == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "No account with id: " + id + " was found";
            }
            currentUser.setFirstname(data.getFirstname());
            currentUser.setEmail(data.getEmail());
            currentUser.setLastname(data.getLastname());
            currentUser.setPassword(data.getPassword());
            accountDao.persist(currentUser);
            return "User updated successfully.";

        }
        catch (Exception e) {
            System.out.println("Something went wrong trying to update user: " + id);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Something went wrong";
        }
    }

}
