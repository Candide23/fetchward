package com.fetchAward.demo.service;

import com.fetchAward.demo.Model.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    Item getItem(UUID id);
    Item saveItem(Item item);
    void deleteItem(UUID id);
    List<Item> getItems();
}
