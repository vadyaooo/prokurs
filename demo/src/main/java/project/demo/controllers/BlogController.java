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
import project.demo.serverent.historymodel;

import java.util.*;

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

    @PostMapping("/alltovarysort")
    public Iterable<Imeiitem> alltovarysort(@RequestBody Imeiitem imeiitem){
        Iterable<Imeiitem> imeiitemIterable = imeiitemRepository.findByMarkaContainingOrSerialnumberContainingOrFullnameContainingAndNalichiye(
                imeiitem.getMarka(), imeiitem.getMarka(), imeiitem.getMarka(), true
        );
        return imeiitemIterable;
    }

    @GetMapping("/alltovary")
    public Iterable<Imeiitem> alltovary() {
        Iterable<Imeiitem> items = imeiitemRepository.findByNalichiye(true);
        return items;
    }

    @GetMapping("/allhistory")
    public Iterable<Historyedit> allhistory() {
        Iterable<Historyedit> historyeditIterable = historyeditRepository.findAll();
        return historyeditIterable;
    }

    @PostMapping("/allhistorysort")
    public Iterable<Historyedit> allhistorysort(@RequestBody Historyedit historyedit){
        Iterable<Historyedit> h1 = historyeditRepository.findByImeiitem_SerialnumberContainingOrImeiitem_MarkaContainingOrImeiitem_FullnameContaining(
                historyedit.getTypeofedit(), historyedit.getTypeofedit(), historyedit.getTypeofedit()
        );
        return h1;
    }


    @PostMapping("/deletemodel")
    public String deletemodel(@RequestBody Item item){
        Item i1 = itemRepository.findById(item.getId()).orElseThrow();

        itemRepository.delete(i1);
        return "1.Модель успешно удалена";
    }

    @PostMapping("/edititem")
    public String edititem(@RequestBody Imeiitem imeiitem){
        Imeiitem i2 = imeiitemRepository.findById(imeiitem.getId()).orElseThrow();
        i2 = imeiitem;Date currentDate = new Date ();
        Historyedit historyedit = new Historyedit(currentDate.toString(), UserImya, "Редактирование товара");
        historyedit.setImeiitem(imeiitem);
        historyedit.setItem(i2.getItem());
        historyeditRepository.save(historyedit);
        imeiitemRepository.save(i2);
        return "1.Товар успешно изменен";
    }


    @PostMapping("/editmodel")
    public String editmodel(@RequestBody Item item){
        Item i1 = itemRepository.findById(item.getId()).orElseThrow();
        i1 = item;
        i1.setMarka(item.getMarka());
        i1.setHowmany(item.getHowmany());
        i1.setDate(item.getDate());
        i1.setFullname(item.getFullname());
        i1.setDetail(item.getDetail());
        i1.setImageurl(item.getImageurl());
        i1.setTypetech(item.getTypetech());
        itemRepository.save(i1);
        return "1.Модель успешно изменена";
    }

    @PostMapping("/deleteitemwithhistory")
    public String deleteitemwithhistory(@RequestBody Imeiitem imeiitem){
        Imeiitem imeiitem1 = imeiitemRepository.findById(imeiitem.getId()).orElseThrow();
        Date currentDate = new Date ();
        Historyedit historyedit = new Historyedit(currentDate.toString(), UserImya, "Удаление из базы");
        historyedit.setImeiitem(imeiitem);
        historyedit.setItem(imeiitem1.getItem());
        imeiitemRepository.delete(imeiitem);
        historyeditRepository.save(historyedit);
        return "1.Единица товара успешно удалена!";
    }


    @PostMapping("/deleteelementhistory")
    public String deleteelementhistory(@RequestBody Historyedit item){
        Historyedit historyedit = historyeditRepository.findById(item.getId()).orElseThrow();
        historyeditRepository.delete(historyedit);
        return "1. Запись из истории успешно удалена";
    }

    @PostMapping("/modelhistoryparam")
    public List<Historyedit>  modelhistoryparam(@RequestBody Item item) {
        Item model = itemRepository.getById(item.getId());
        Iterable<Imeiitem> tovars = imeiitemRepository.findDistinctByItem_Id(model.getId());
        Iterator<Imeiitem> imeiitemIterator = tovars.iterator();


        List<Historyedit> historyeditIterable = historyeditRepository.findByImeiitem_SerialnumberStartingWithAndItem_Id(item.getMarka(),
                item.getId());
        return historyeditIterable;
    }


    @PostMapping("/modelitemhistory")
    public List<Historyedit>  modelitem(@RequestBody Imeiitem item) {
        Item model = itemRepository.getById(item.getId());


        List<Historyedit> historyeditIterable = historyeditRepository.findDistinctByImeiitem_Id(item.getId());
        return historyeditIterable;
    }


    @PostMapping("/modelhistory")
    public List<Historyedit>  modelhistory(@RequestBody Item item) {
        Item model = itemRepository.getById(item.getId());
        Iterable<Imeiitem> tovars = imeiitemRepository.findDistinctByItem_Id(model.getId());
        Iterator<Imeiitem> imeiitemIterator = tovars.iterator();


        List<Historyedit> historyeditIterable = historyeditRepository.findDistinctByItem_Id(item.getId());
        return historyeditIterable;
    }

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
        historyedit.setItem(imeiitem.getItem());
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
        historyedit.setItem(imeiitem.getItem());
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