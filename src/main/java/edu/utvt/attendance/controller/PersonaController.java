package edu.utvt.attendance.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.utvt.attendance.model.Persona;
import edu.utvt.attendance.model.Item;
import edu.utvt.attendance.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Personas", description = "API para la gestión de personas")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }

    @Operation(summary = "Obtener una persona por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable UUID id) {
        return personaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva persona")
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        for (Item item : persona.getItems()) {
            item.setPersona(persona);
        }
        return personaService.save(persona);
    }

    @Operation(summary = "Actualizar una persona existente")
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable UUID id, @RequestBody Persona persona) {
        if (!personaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        for (Item item : persona.getItems()) {
            item.setPersona(persona);
        }
        return ResponseEntity.ok(personaService.save(persona));
    }

    @Operation(summary = "Eliminar una persona por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable UUID id) {
        if (!personaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener personas por nombre")
    @GetMapping("/nombre/{nombre}")
    public List<Persona> getPersonaByNombre(@PathVariable String nombre) {
        return personaService.findByNombre(nombre);
    }
}
