package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.demo.Consts.Role;
import project.demo.Entetys.Account;
import project.demo.Entetys.Historyedit;
import project.demo.Entetys.Imeiitem;
import project.demo.repo.AccountRepository;
import project.demo.repo.HistoryeditRepository;
import project.demo.warkclass.Accounte;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AccController {

    public static String StatusUser = "Maining";
    public static String UserImya = "", userforh = "";


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryeditRepository historyeditRepository;

    @PostMapping("/")
    public String accounter(@RequestBody Accounte account) {
        UserImya = account.getUser_name();
        Account accounts = accountRepository.findByUsername(account.getUser_name());
        Accounte accounte = new Accounte(null, null);
        if(accounts != null){
            accounte.setUser_name(accounts.getUsername());
            accounte.setUser_pass(accounts.getRoles().toString());
            if(accounte.getUser_pass().equals("[ADMIN]")) {
                StatusUser = "ADMIN";
                return "ADMIN";

            }
            if(accounte.getUser_pass().equals("[USER]")) {
                StatusUser = "USER";
                return "USER";
            }
        }
        return "ERROR";
    }

    @GetMapping("/checking")
    public String checking(){
        return StatusUser;
    }


    @GetMapping("/logaut")
    public String logauting(){
        StatusUser = "Maining";
        return StatusUser;
    }


    @GetMapping("/alluser")
    public Iterable<Account> alluser(){
        Iterable<Account> accountIterable = accountRepository.findAllByUsernameNot("admin");
        return accountIterable;
    }

    @PostMapping("/userallhistory")
    public Iterable<Historyedit> userallhistory(@RequestBody Account account){
        Iterable<Historyedit> historyeditIterable = historyeditRepository.findByNameuserofedit(account.getUsername());
        return historyeditIterable;
    }

    @PostMapping("/userallhistoryparam")
    public Iterable<Historyedit> userallhistoryparam(@RequestBody Imeiitem imeiitem){
        Iterable<Historyedit> historyeditIterable = historyeditRepository.findByImeiitem_SerialnumberContainingAndNameuserofedit(
                imeiitem.getMarka(), imeiitem.getFullname() );
        return historyeditIterable;
    }

    @PostMapping("/alluserdelete")
    public String alluser(@RequestBody Account account){
        Account accountIterable = accountRepository.findById(account.getId()).orElseThrow();
        accountRepository.delete(accountIterable);
        return "1. Пользователь успешно удален";
    }



    @PostMapping("/registration")
    public String registrationOfAccount(@RequestBody Account account){
        Account accounts = accountRepository.findByUsername(account.getUsername());
        account.setRoles(Collections.singleton(Role.USER));
        if(accounts == null) {
            accountRepository.save(account);
            return "1. Регистрация пользователя прошла успешно!";
        }
        return "Пользователь с таким именем уже существует!";
    }





}