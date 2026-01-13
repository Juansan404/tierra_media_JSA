package dam.saruman.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Personaje")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "arma_principal", length = 100)
    private String arma_principal;

    @Column(name = "nivel_poder")
    private Double nivel_poder;

    @Column(name = "fecha_aparicion")
    private LocalDate fecha_aparicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raza_id")
    private Raza raza;

    // Constructores
    public Personaje() {
    }

    public Personaje(String nombre, Integer edad, String arma_principal, Double nivel_poder, LocalDate fecha_aparicion) {
        this.nombre = nombre;
        this.edad = edad;
        this.arma_principal = arma_principal;
        this.nivel_poder = nivel_poder;
        this.fecha_aparicion = fecha_aparicion;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getArma_principal() {
        return arma_principal;
    }

    public void setArma_principal(String arma_principal) {
        this.arma_principal = arma_principal;
    }

    public Double getNivel_poder() {
        return nivel_poder;
    }

    public void setNivel_poder(Double nivel_poder) {
        this.nivel_poder = nivel_poder;
    }

    public LocalDate getFecha_aparicion() {
        return fecha_aparicion;
    }

    public void setFecha_aparicion(LocalDate fecha_aparicion) {
        this.fecha_aparicion = fecha_aparicion;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", arma_principal='" + arma_principal + '\'' +
                ", nivel_poder=" + nivel_poder +
                ", fecha_aparicion=" + fecha_aparicion +
                ", raza=" + (raza != null ? raza.getNombre() : "null") +
                '}';
    }
}
