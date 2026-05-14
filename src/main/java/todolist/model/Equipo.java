package todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Default constructor required by JPA (Spring Boot)
    public Equipo() {
    }

    // Constructor required by your test
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Getter required by your test's assertion
    public String getNombre() {
        return nombre;
    }
}