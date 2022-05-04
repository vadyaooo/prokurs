package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.demo.Entetys.Account;
import project.demo.repo.AccountRepository;
import project.demo.warkclass.Accounte;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AccController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/")
    public String accounter(@RequestBody Accounte account) {
        Account accounts = accountRepository.findByUsername(account.getUser_name());
        Accounte accounte = new Accounte(null, null);
        if(accounts != null){
            accounte.setUser_name(accounts.getUsername());
            accounte.setUser_pass(accounts.getRoles().toString());
            if(accounte.getUser_pass().equals("[ADMIN]"))
                return "ADMIN";
            if(accounte.getUser_pass().equals("[USER]"))
                return "USER";
        }
        return "ERROR";
    }

    @PostMapping("/registration")
    public String registrationOfAccount(@RequestBody Account account){
        Account accounts = accountRepository.findByUsername(account.getUsername());
        if(accounts == null) {
            accountRepository.save(account);
            return "Регистрация пользователя прошла успешно!";
        }
        return "Пользователь с таким именем уже существует!";
    }





}