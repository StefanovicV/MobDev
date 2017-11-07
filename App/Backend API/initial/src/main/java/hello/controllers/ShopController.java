package hello.controllers;

import hello.data.AttackList;
import hello.data.ItemList;
import hello.data.SpeciesList;
import hello.domainclasses.Shop;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 11500809 on 3/11/2017.
 */
@RestController
public class ShopController {
    Shop shop = new Shop(ItemList.getList(), AttackList.getAttacksList(), SpeciesList.getBuyableSpecies());

    @RequestMapping("/shop")
    public Shop shopList(){
        return shop;
    }
}
