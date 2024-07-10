package edu.utvt.attendance.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false, length = 100)
    private String universidad;

    @Column(nullable = false, length = 20)
    private String correo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE) // Especifica el tipo de temporalidad para fechaDeNacimiento
    private Date fechaDeNacimiento;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Item> items;

    // Constructor vacío requerido por JPA
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombre, int edad, String universidad, String correo, Date fechaDeNacimiento) {
        this.nombre = nombre;
        this.edad = edad;
        this.universidad = universidad;
        this.correo = correo;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    // Getters y Setters
    
    public UUID getId() {
        return id;
    }

    // No necesitas un setId(UUID id) explícito aquí porque JPA se encarga de eso

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
