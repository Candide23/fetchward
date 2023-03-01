package com.fetchAward.demo.service;

import com.fetchAward.demo.Exception.ItemNotFoundException;
import com.fetchAward.demo.Model.Item;
import com.fetchAward.demo.Repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;


    @Override
    public Item getItem(UUID id) {
        Optional<Item> item = itemRepository.findById(id);
        return unwrapItem(item, id);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }


    @Override
    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }


    @Override
    public List<Item> getItems() {
        return (List<Item>) itemRepository.findAll();
    }

    static Item unwrapItem(Optional<Item> entity, UUID id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new ItemNotFoundException(id);
        }
    }

}
