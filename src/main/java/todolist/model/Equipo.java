package todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "equipos")
public class Equipo {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "equipo_usuario",
            joinColumns = @JoinColumn(name = "fk_equipo"),
            inverseJoinColumns = @JoinColumn(name = "fk_usuario")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {

        usuarios.add(usuario);

        usuario.getEquipos().add(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Equipo))
            return false;

        Equipo equipo = (Equipo) o;

        // Если есть id → сравниваем по id
        if (id != null && equipo.id != null)
            return id.equals(equipo.id);

        // Иначе сравниваем по nombre
        return nombre.equals(equipo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

}