package controllers;

import jakarta.persistence.Query;
import java.util.List;
import models.Socio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SocioController {
        
    public static boolean borrarSocio(int id){
        try{
            Session session = getSession();
            session.beginTransaction();
            session.remove(getSocio(id));
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    /*
    1:Correcto    
    -1:Incorrecto
    0:Repetido
    */
    public static int createSocio(String nombre){
        try{
            
            Session session = getSession(); 
            
            Query q = session.createQuery("from Socio c where c.nombre = ?1", Socio.class);
            q.setParameter(1, nombre);        

            if(q.getResultList().size()>0){
                return 0;
            }
                    
            Socio s = new Socio(nombre);

            session.beginTransaction();
            session.persist(s);
            session.getTransaction().commit();
            session.close();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static Socio getSocio(int id){
        Session session = getSession();
        Socio s = session.get(Socio.class, id);
        session.close();
        return s;
    }
    
    public static List<Socio> getSociosByName(String nombre){
        Session session = getSession();
        
        
        Query q = session.createQuery("from Socio c where c.nombre like ?1", Socio.class);
        q.setParameter(1, "%"+nombre+"%");        
        List<Socio> list = q.getResultList();
        session.close();
        return list;        
    }
    
    
    public static List<Socio> getAll(){
        Session session = getSession();  
        Query q = session.createQuery("from Socio", Socio.class);             
        List<Socio> list = q.getResultList();
        session.close();
        return list;        
    }
    
    
    public static boolean updateSocio(int id, String nombre){
        try{
            Session session = getSession();
        
            Socio s = session.get(Socio.class, id);

            s.setNombre(nombre);
            session.beginTransaction();
            session.persist(s);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    private static Session getSession(){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Socio.class).buildSessionFactory();
	return sf.openSession();
    }
    
}
