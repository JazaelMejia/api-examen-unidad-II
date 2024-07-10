package edu.utvt.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.utvt.attendance.model.Item;
import edu.utvt.attendance.service.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Items", description = "API para la gestión de items")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Obtener todos los items")
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @Operation(summary = "Obtener un item por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable UUID id) {
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo item")
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @Operation(summary = "Actualizar un item existente")
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable UUID id, @RequestBody Item item) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Item existingItem = itemOptional.get();
        existingItem.setNombre(item.getNombre());
        existingItem.setPersona(item.getPersona());

        itemService.save(existingItem); 

        return ResponseEntity.ok(existingItem);
    }

    @Operation(summary = "Eliminar un item por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        if (!itemService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener items por nombre")
    @GetMapping("/nombre/{nombre}")
    public List<Item> getItemByNombre(@PathVariable String nombre) {
        return itemService.findByNombre(nombre);
    }
}