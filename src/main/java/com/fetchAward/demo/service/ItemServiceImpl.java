package com.fetchAward.demo.service;

import com.fetchAward.demo.Exception.ItemNotFoundException;
import com.fetchAward.demo.Model.Item;
import com.fetchAward.demo.Repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Item getItemById(UUID id) {
        return itemRepository.getItem(findIndexById(id));
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.saveItem(item);


    }

    @Override
    public List<Item> getItems() {
        return itemRepository.getItems();
    }


    private int findIndexById(UUID id) {
        return IntStream.range(0, itemRepository.getItems().size())
                .filter(index -> itemRepository.getItems().get(index).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
}
