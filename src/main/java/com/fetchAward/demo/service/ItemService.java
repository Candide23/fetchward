package com.fetchAward.demo.service;

import com.fetchAward.demo.Model.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    Item getItemById(UUID id);
    void saveItem(Item item);
    List<Item> getItems();
}
