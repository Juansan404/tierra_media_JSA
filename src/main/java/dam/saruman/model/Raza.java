package dam.saruman.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Raza")
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "habilidad_especial", length = 150)
    private String habilidad_especial;

    @Column(name = "reino_origen", length = 100)
    private String reino_origen;

    @Column(name = "esperanza_vida")
    private Integer esperanza_vida;

    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL)
    private List<Personaje> personajes = new ArrayList<>();

    // Constructores
    public Raza() {
    }

    public Raza(String nombre, String habilidad_especial, String reino_origen, Integer esperanza_vida) {
        this.nombre = nombre;
        this.habilidad_especial = habilidad_especial;
        this.reino_origen = reino_origen;
        this.esperanza_vida = esperanza_vida;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidad_especial() {
        return habilidad_especial;
    }

    public void setHabilidad_especial(String habilidad_especial) {
        this.habilidad_especial = habilidad_especial;
    }

    public String getReino_origen() {
        return reino_origen;
    }

    public void setReino_origen(String reino_origen) {
        this.reino_origen = reino_origen;
    }

    public Integer getEsperanza_vida() {
        return esperanza_vida;
    }

    public void setEsperanza_vida(Integer esperanza_vida) {
        this.esperanza_vida = esperanza_vida;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    // Métodos de utilidad para gestionar la relación bidireccional
    public void addPersonaje(Personaje personaje) {
        personajes.add(personaje);
        personaje.setRaza(this);
    }

    public void removePersonaje(Personaje personaje) {
        personajes.remove(personaje);
        personaje.setRaza(null);
    }

    @Override
    public String toString() {
        return "Raza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", habilidad_especial='" + habilidad_especial + '\'' +
                ", reino_origen='" + reino_origen + '\'' +
                ", esperanza_vida=" + esperanza_vida +
                ", personajes=" + personajes.size() +
                '}';
    }
}
