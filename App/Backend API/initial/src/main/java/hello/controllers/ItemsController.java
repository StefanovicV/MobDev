package hello.controllers;

import hello.data.ItemList;
import hello.domainclasses.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stefv on 24-Oct-17.
 */
@RestController
public class ItemsController {
    @RequestMapping("/items")
    public Item[] items(@RequestParam(value = "name", defaultValue = "") String name) {
        if (name.equals("")) {
            return ItemList.getList();
        } else {
            return new Item[] {ItemList.getItem(name)};
        }
    }

}
