package com.rms.menu_backend.controller;

import com.rms.menu_backend.model.MenuItem;
import com.rms.menu_backend.repository.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuItemController {

    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);
    private final MenuItemRepository repo;

    public MenuItemController(MenuItemRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<MenuItem> addItem(@RequestBody MenuItem item) {
        log.info("POST /api/menu called with data: {}", item);
        MenuItem saved = repo.save(item);
        return ResponseEntity.created(URI.create("/api/menu/" + saved.getId())).body(saved);
    }

    // READ (ALL)
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllItems() {
        List<MenuItem> list = repo.findAll();
        return ResponseEntity.ok(list);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateItem(@PathVariable Long id, @RequestBody MenuItem item) {
        log.info("PUT /api/menu/{} called with data: {}", id, item);
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        MenuItem updated = repo.save(item);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
