package dam.saruman;

import dam.saruman.dao.PersonajeDAO;
import dam.saruman.dao.RazaDAO;
import dam.saruman.model.Personaje;
import dam.saruman.model.Raza;
import dam.saruman.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final RazaDAO razaDAO = new RazaDAO();
    private static final PersonajeDAO personajeDAO = new PersonajeDAO();

    public static void main(String[] args) {
        Raza hobbit = new Raza("Hobbit", "Sigilo y resistencia", "La Comarca", 100);
        Raza elfo = new Raza("Elfo", "Inmortalidad y visión aguda", "Rivendel", 999999);
        Raza humano = new Raza("Humano", "Adaptabilidad y coraje", "Gondor", 80);
        Raza enano = new Raza("Enano", "Fuerza y artesanía", "Erebor", 250);
        Raza mago = new Raza("Mago", "Magia arcana", "Maiar", 999999);

        Personaje frodo = new Personaje("Frodo Bolsón", 50, "Dardo", 45.0, LocalDate.of(2968, 9, 22), hobbit);
        Personaje gandalf = new Personaje("Gandalf el Gris", 2019, "Glamdring", 98.0, LocalDate.of(1000, 1, 1), mago);
        Personaje aragorn = new Personaje("Aragorn", 87, "Andúril", 85.0, LocalDate.of(2931, 3, 1), humano);
        Personaje legolas = new Personaje("Legolas", 2931, "Arco élfico", 80.0, LocalDate.of(87, 1, 1), elfo);
        Personaje gimli = new Personaje("Gimli", 139, "Hacha de batalla", 75.0, LocalDate.of(2879, 1, 1), enano);
        Personaje sam = new Personaje("Sam Gamyi", 38, "Dardo", 40.0, LocalDate.of(2980, 4, 6), hobbit);
        Personaje boromir = new Personaje("Boromir", 41, "Espada", 70.0, LocalDate.of(2978, 1, 1), humano);
        Personaje galadriel = new Personaje("Galadriel", 7000, "Nenya", 95.0, LocalDate.of(-5000, 1, 1), elfo);

        System.out.println("PERSONAJES: ");
        System.out.println(personajeDAO.obtenerTodos()+"\n");

        System.out.println("PERSONAJES POR RAZA (Elfos): ");
        System.out.println(personajeDAO.buscarPorRaza(2L)+"\n"); // 1=Hobbit, 2=Elfo, 3=Humano, 4=Enano, 5=Mago

        System.out.println("PERSONAJES POR RANGO DE NIVEL DE PODER: ");
        System.out.println(personajeDAO.buscarPorRangoNivelPoder(5000.0,10000.0)+"\n");

        System.out.println("ACTUALIZACION DEL NIVEL DE PODER DE UN PERSONAJE: ");
        aragorn.setNivel_poder(aragorn.getNivel_poder()+100);
        System.out.println(personajeDAO.obtenerPorId(13L)+"\n");

        System.out.println("ELIMINACION DE UN PERSONAJE: ");
        //personajeDAO.eliminar(3L);
        //System.out.println(personajeDAO.obtenerTodos()+"\n");

        System.out.println("LISTADO DE PERSONAJES DE UNA RAZA ESPECIFICA: ");
        System.out.println(personajeDAO.buscarPorRaza(2L)+"\n");

        System.out.println("MOSTRAR LOS 5 PERSONAJES MAS PODEROSOS: ");
        System.out.println(personajeDAO.obtenerMasPoderosos(5));
    }
}
