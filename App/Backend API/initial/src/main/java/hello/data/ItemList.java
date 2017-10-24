package hello.data;

import hello.domainclasses.Item;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stefv on 24-Oct-17.
 */
public class ItemList {
    private static List<Item> items = Arrays.asList(
            new Item("Potion", 10, 25),
            new Item("Super Potion", 20, 50)
    );

    public static Item[] getList() {

        return (Item[])items.toArray();
    }

    public static Item getItem(String itemName) {
        return items.stream().filter(item -> item.getName().toLowerCase().equals(itemName.toLowerCase())).findAny().get();
    }
}
