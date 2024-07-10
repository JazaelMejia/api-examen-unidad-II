package edu.utvt.attendance.service;

import edu.utvt.attendance.model.Item;
import edu.utvt.attendance.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public List<Item> findByNombre(String nombre) {
        return itemRepository.findByNombre(nombre);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}
