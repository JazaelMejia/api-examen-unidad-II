package edu.utvt.attendance.repository;

import edu.utvt.attendance.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {
    List<Persona> findByNombre(String nombre);
}
