package hello.controllers;

import hello.data.AttackList;
import hello.domainclasses.Attack;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stefv on 24-Oct-17.
 */

@RestController
public class AttackController {

    @RequestMapping("/attacks")
    public Attack[] attacks(@RequestParam(value="name", defaultValue = "") String name) {
        if (name.equals("")) {
            return AttackList.getAttacksList();
        } else {
            return new Attack[]{AttackList.getAttackByName(name)};
        }
    }

    @RequestMapping("/attacks/{attackName}")
    public Attack getAttackByName(@PathVariable(value = "attackName")String attackName) {
        return AttackList.getAttackByName(attackName);
    }
}
