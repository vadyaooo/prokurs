package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.demo.Entetys.Historyedit;
import project.demo.Entetys.Imeiitem;
import project.demo.Entetys.Item;
import project.demo.repo.HistoryeditRepository;
import project.demo.repo.ImeiitemRepository;
import project.demo.repo.ItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static project.demo.controllers.AccController.UserImya;

@RestController
@RequestMapping("/kurs")
@CrossOrigin
public class BlogController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImeiitemRepository imeiitemRepository;

    @Autowired
    private HistoryeditRepository historyeditRepository;


    private Item idonetovarforadd;

    boolean dobavorno;
    /*@GetMapping("/items")
    public String items(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }*/

    @PostMapping("/addonemodel")
    public String addonetovar(@RequestBody Imeiitem imeiitem){
        dobavorno = true;
        if(!IfOneTovar(imeiitem)) {
            return "Данных о введеной вами модели нет " +
                    "в базе данных! Пожалуйста, давьте в базу модель " +
                    "и затем вернитесь в окно добавления единцы " +
                    "товара модели!";
        }
        if(!IfOneSerialNumber(imeiitem)){
            return "2. Товар с таким IMEI, маркой, моделью, уже существет в базе данных";
        }
        imeiitem.setItem(idonetovarforadd);
        imeiitem.setNameuserofadd(UserImya);
        Date currentDate = new Date ();
        Historyedit historyedit = new Historyedit(currentDate.toString(), UserImya, "Добавление в базу");
        historyedit.setImeiitem(imeiitem);
        imeiitem.setNalichiye(true);
        imeiitemRepository.save(imeiitem);
        historyeditRepository.save(historyedit);
        return "1.Единица товара успешно добавлена!";
    }

    public boolean IfOneSerialNumber(Imeiitem i1){
        Iterable<Imeiitem> iterable = imeiitemRepository.findAll();
        for (Imeiitem imeiitem : iterable) {
            String g = imeiitem.getFullname();
            if (imeiitem.getSerialnumber().equals(i1.getSerialnumber())) {
                return false;
            }
        }
        return true;
    }



    public boolean IfOneTovar(Imeiitem i1){
        Iterable<Item> iterable = itemRepository.findAll();
        for (Item item : iterable) {
            String g = item.getFullname();
            if (item.getMarka().equals(i1.getMarka()) &&
                    item.getFullname().equals(i1.getFullname())) {
                if(dobavorno)
                item.setHowmany(item.getHowmany() + 1);
                else
                    item.setHowmany(item.getHowmany() - 1);
                itemRepository.save(item);
                idonetovarforadd = item;
                return true;
            }
        }
        return false;
    }


    @PostMapping("/realize")
    public String realize(@RequestBody Imeiitem imeiitem){
        dobavorno = false;
        boolean t = IfOneTovar(imeiitem);
        imeiitem.setNalichiye(false);
        imeiitemRepository.save(imeiitem);
        Date currentDate = new Date ();
        Historyedit historyedit = new Historyedit(currentDate.toString(), UserImya, "Реализация товара");
        historyedit.setImeiitem(imeiitem);
        historyeditRepository.save(historyedit);
        return "1.Единица товара успешно добавлена в раздел реализованных товаров!";
    }




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


    @PostMapping("/additem")
    public String addModels(@RequestBody Item item){
        if(!ifhaveMarkaAndFullnameOnDataBase(item)) {
            return "Данная модель Введеной марки уже существует в базе";
        }
        itemRepository.save(item);
        return "1.Модель успешно добавлена";
    }

    public boolean ifhaveMarkaAndFullnameOnDataBase(Item i1){
        Iterable<Item> iterable = itemRepository.findAll();
        for (Item item : iterable) {
            String g = item.getFullname();
            if (item.getMarka().equals(i1.getMarka()) && item.getFullname().equals(i1.getFullname())
                    && item.getTypetech().equals(i1.getTypetech()))
                return false;
        }
        return true;
    }

    /*@PostMapping("/items/ider")
    public Iterable<Imeiitem> itemShow(@RequestBody Imeiitem id) {
        Iterable<Imeiitem> iterable = imeiitemRepository.findAll();
        Iterable<Imeiitem> imeiitemIterable;
        for (Imeiitem item : iterable) {
            if (item.getItem().getId() == id.getId() && item.isNalichiye()) {

            }
            else
                imeiitemIterable.iterator().remove();
        }
        return iterable;

    }*/

    @PostMapping("/items/ider")
    public List<Imeiitem> itemShow(@RequestBody Imeiitem id) {
        List<Imeiitem> list1 = imeiitemRepository.findDistinctByItem_IdAndNalichiye(id.getId(), true);
        int i = 4 + 3;

        return list1;
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