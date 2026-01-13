package dam.saruman.dao;

import dam.saruman.model.Raza;
import dam.saruman.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RazaDAO {

    public void guardar(Raza raza) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(raza);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Raza obtenerPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Raza.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Raza> obtenerTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Raza", Raza.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizar(Raza raza) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(raza);
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
            Raza raza = session.find(Raza.class, id);
            if (raza != null) {
                session.remove(raza);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Raza> buscarPorReino(String reino) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Raza> query = session.createQuery(
                "FROM Raza r WHERE r.reino_origen = :reino", Raza.class);
            query.setParameter("reino", reino);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Raza obtenerConPersonajes(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Raza> query = session.createQuery(
                "SELECT r FROM Raza r LEFT JOIN FETCH r.personajes WHERE r.id = :id", Raza.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
