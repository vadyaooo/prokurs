package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.demo.Entetys.ReturnConnect;
import project.demo.repo.ReturnConnectRepository;

@RestController
@RequestMapping("/connect")
@CrossOrigin
public class RetConController {

    @Autowired
    ReturnConnectRepository returnConnectRepository;

    @PostMapping("newconn")
    public String newconn(@RequestBody ReturnConnect returnConnect){
        returnConnectRepository.save(returnConnect);
        return "1. Спасибо за обратную связь!";
    }

    @GetMapping("getconn")
    public Iterable<ReturnConnect> getconn(){
        Iterable<ReturnConnect> returnConnectIterable = returnConnectRepository.findAll();
        return returnConnectIterable;
    }

    @PostMapping("dellconn")
    public String dellconn(@RequestBody ReturnConnect returnConnect){
        ReturnConnect returnConnectIterable = returnConnectRepository.findById(returnConnect.getId()).orElseThrow();
        returnConnectRepository.delete(returnConnectIterable);
        return "1. Сообщение успешно удалено!";
    }
}
