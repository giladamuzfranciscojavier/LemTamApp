package controllers;

import jakarta.persistence.Query;
import java.sql.Date;
import java.util.List;
import models.Prestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import controllers.PrendaController;

public class PrestamoController {
    
    
    public static int createPrestamo(int socio, int prenda, Date fechaFin){
        try{
            Session session = getSession();
            Prestamo p = new Prestamo(socio,prenda,fechaFin);
            
            Prestamo temp = getPrestamo(p.getId());
            if(temp!=null){
                return 0;
            }
            
            session.beginTransaction();
            session.persist(p);            
            if(!PrendaController.prestarPrenda(prenda)){
                session.getTransaction().rollback();
                session.close();
                return -1;
            }
            session.getTransaction().commit();
            session.close();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    
    public static boolean borrarPrestamo(int id){
        try{
            Session session = getSession();
            session.beginTransaction();
            int p = getPrestamo(id).getPrenda();
            session.remove(getPrestamo(id));
            if(!PrendaController.devolverPrenda(p)){
                session.getTransaction().rollback();
                session.close();
                return false;
            }
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static boolean updatePrestamo(int id, Date fechafin){
        try{
            Session session = getSession();
            Prestamo p = session.get(Prestamo.class, id);
            if(fechafin!=null){
                p.setFechaFin(fechafin);
            }
            else{
                p.setFechaFin(null);
            }
            session.beginTransaction();
            session.persist(p);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    //Para comprobar repes
    private static Prestamo getPrestamo(int id){
        Session session = getSession();  
        Prestamo p = session.get(Prestamo.class, id);
        session.close();
        return p;
    }
    
    public static List<Prestamo> getPrestamosPrenda(int id){
        Session session = getSession();
        Query q = session.createQuery("from Prestamo p where p.prenda = ?1", Prestamo.class);
        q.setParameter(1, id);   
        List<Prestamo> list = q.getResultList();
        session.close();
        return list;
    }
    
    
    public static List<Prestamo> getPrestamosSocio(int id){
        Session session = getSession();
        Query q = session.createQuery("from Prestamo p where p.socio = ?1", Prestamo.class);
        q.setParameter(1, id);   
        List<Prestamo> list= q.getResultList();
        session.close();
        return list;
    }
    
    
    public static List<Prestamo> getAll(){
        Session session = getSession();
        List<Prestamo> list = session.createQuery("from Prestamo",Prestamo.class).getResultList();
        session.close();
        return list;
    }
    
    
    public static int getPrendaPrestadaCount(int id){
        Session session = getSession();
        Query q = session.createQuery("from Prestamo p where p.prenda = ?1", Prestamo.class);
        q.setParameter(1, id);
        int res = q.getResultList().size();
        session.close();
        return res;
    }
    
    
    private static Session getSession(){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();
	return sf.openSession();
    }
}
