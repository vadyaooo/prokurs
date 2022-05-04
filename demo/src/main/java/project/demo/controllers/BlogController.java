package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.demo.Entetys.Item;
import project.demo.repo.ItemRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/kurs")
public class BlogController {

    @Autowired
    private ItemRepository itemRepository;

    /*@GetMapping("/items")
    public String items(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }*/

    @GetMapping("/items")
    public Iterable<Item> items() {
        Iterable<Item> items = itemRepository.findAll();
        return items;
    }



    @GetMapping("/error")
    public String error(String text, Model model) {
        model.addAttribute("texterror", text);
        return "error";
    }


    @CrossOrigin
    @PostMapping("/")
    public String additemPost(@RequestBody Item item){
        itemRepository.save(item);
        return "new item is added";
    }
    /*@PostMapping("/additem")
    public String additemPost(@RequestParam String marka, @RequestParam String fullname,@RequestParam Integer howmany,Model model){
        Item item = new Item(marka, fullname, howmany);
        if(!ifhaveMarkaAndFullnameOnDataBase(marka, fullname)) {
            error("Данная модель Введеной марки уже существует в базе", model);
            return "error";
        }
        itemRepository.save(item);
        return "redirect:/";
    }*/

    public boolean ifhaveMarkaAndFullnameOnDataBase(String marka, String fullname){
        Iterable<Item> iterable = itemRepository.findAll();
        for (Item item : iterable) {
            String g = item.getFullname();
            if (item.getMarka().equals(marka) & item.getFullname().equals(fullname))
                return false;
        }
        return true;
    }

    @GetMapping("/items/{id}")
    public String itemShow(@PathVariable(value = "id") int id, Model model) {
        if(!itemRepository.existsById(id)){
            return "redirect:/items";
        }

        Optional<Item> item = itemRepository.findById(id);
        ArrayList<Item> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("items", res);
        return "item-show";
    }

    @GetMapping("/items/{id}/edit")
    public String itemEdit(@PathVariable(value = "id") int id, Model model) {
        if(!itemRepository.existsById(id)){
            return "redirect:/items";
        }

        Optional<Item> item = itemRepository.findById(id);
        ArrayList<Item> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("items", res);
        return "item-edit";
    }


    @PostMapping("/items/{id}/edit")
    public String edititemPost(@PathVariable(value = "id") int id, @RequestParam String marka, @RequestParam String fullname,@RequestParam Integer howmany,Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        item.setMarka(marka);
        item.setFullname(fullname);
        item.setHowmany(howmany);
        itemRepository.save(item);
        return "redirect:/items";
    }

    @PostMapping("/items/{id}/remove")
    public String removeitemPost(@PathVariable(value = "id") int id,Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        itemRepository.delete(item);
        return "redirect:/items";
    }

}