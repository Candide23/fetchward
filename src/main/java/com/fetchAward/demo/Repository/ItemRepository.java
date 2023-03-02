package com.fetchAward.demo.Repository;

import com.fetchAward.demo.Model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(int index) {
        return items.get(index);
    }


    public void saveItem(Item item) {
        items.add(item);
    }


}
