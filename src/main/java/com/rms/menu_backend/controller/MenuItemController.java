package com.rms.menu_backend.controller;

import com.rms.menu_backend.model.MenuItem;
import com.rms.menu_backend.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuItemController {

    private final MenuItemRepository repo;

    public MenuItemController(MenuItemRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @PostMapping
    public MenuItem addItem(@RequestBody MenuItem item) {
        return repo.save(item);
    }

    // READ (ALL)
    @GetMapping
    public List<MenuItem> getAllItems() {
        return repo.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public MenuItem updateItem(@PathVariable Long id, @RequestBody MenuItem item) {
        item.setId(id);
        return repo.save(item);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
