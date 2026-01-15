package dam.saruman.dao;

import dam.saruman.model.Personaje;
import dam.saruman.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonajeDAO {

    public void guardar(Personaje personaje) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(personaje);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Personaje obtenerPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personaje> query = session.createQuery(
                "FROM Personaje p JOIN FETCH p.raza WHERE p.id = :id", Personaje.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Personaje> obtenerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Personaje p JOIN FETCH p.raza", Personaje.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizar(Personaje personaje) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(personaje);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Personaje personaje = session.find(Personaje.class, id);
            if (personaje != null) {
                session.remove(personaje);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Personaje> buscarPorRaza(Long razaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personaje> query = session.createQuery(
                "FROM Personaje p JOIN FETCH p.raza WHERE p.raza.id = :razaId", Personaje.class);
            query.setParameter("razaId", razaId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Personaje> buscarPorRangoNivelPoder(Double min, Double max) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personaje> query = session.createQuery(
                "FROM Personaje p JOIN FETCH p.raza WHERE p.nivel_poder BETWEEN :min AND :max", Personaje.class);
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Personaje> buscarPorArma(String arma) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personaje> query = session.createQuery(
                "FROM Personaje p JOIN FETCH p.raza WHERE p.arma_principal = :arma", Personaje.class);
            query.setParameter("arma", arma);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Personaje> obtenerMasPoderosos(int limite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personaje> query = session.createQuery(
                "FROM Personaje p JOIN FETCH p.raza ORDER BY p.nivel_poder DESC", Personaje.class);
            query.setMaxResults(limite);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
