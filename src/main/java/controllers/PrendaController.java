package controllers;

import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import com.spire.barcode.QRCodeECL;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.LemTamApp;
import models.Prenda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class PrendaController {
        
    /*
    1:Correcto    
    -1:Incorrecto
    0:Repetido
    */
    public static boolean createPrenda(String tipo, byte[] foto, String color, String talla, int cantidad){
        try{
            Session session = getSession();                  
            
            int max = getMaxID();
            int lim = max+cantidad;     
            
            //Reinicio del auto incremento para que ocupe las ids más bajas posibles (a continuación de la más alta existente)
            session.beginTransaction();
            MutationQuery q = session.createNativeMutationQuery("ALTER TABLE Prendas AUTO_INCREMENT=1");
            q.executeUpdate();
            session.getTransaction().commit();
            
            for(;max<lim;max++){
                Prenda temp = new Prenda(tipo,foto,color,talla);
                temp.setId(max);
                session.beginTransaction(); 
                session.persist(temp);
                session.getTransaction().commit(); 
            }    
            session.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean borrarPrenda(int id){
        try{
            Session session = getSession();
            session.beginTransaction();
            session.remove(getPrenda(id));
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    
    public static boolean updatePrenda(int id, String tipo, String color, String talla, byte[] foto){
        try{
            Session session = getSession();
        
            Prenda s = session.get(Prenda.class, id);

            s.setTipo(tipo);
            s.setColor(color);
            s.setTalla(talla);
            s.setFoto(foto);
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
    
    public static boolean prestarPrenda(int id){
        try{
            Session session = getSession();
        
            Prenda s = session.get(Prenda.class, id);

            s.setDisponible(false);
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
    
    public static boolean devolverPrenda(int id){
        try{
            Session session = getSession();
            System.out.println(id);
        
            Prenda s = session.get(Prenda.class, id);

            s.setDisponible(true);
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
    
    
    public static Prenda getPrenda(int id){
        Session session = getSession();
        Prenda p = session.get(Prenda.class, id);
        session.close();
        return p;
    }  

    public static List<Prenda> getPrendaSearch(String str){
        Session session = getSession();
        
        
        Query q = session.createQuery("from Prenda c where c.tipo like ?1 or c.color like ?1 or c.talla like ?1", Prenda.class);
        q.setParameter(1, "%"+str+"%");   
        List<Prenda> list = q.getResultList();
        //session.close();
        return list;
    }
    
    public static List<Prenda> getAll(){
        Session session = getSession();
        List<Prenda> list = session.createQuery("from Prenda",Prenda.class).getResultList();
        session.close();
        return list;
    } 
    
    public static boolean generateQR(int id, File f){      
                
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("GALLA.TTF")));
            
            BarcodeSettings settings = new BarcodeSettings();
        
            settings.setType(BarCodeType.QR_Code);                

            settings.setData(String.valueOf("lemtam:"+id));
        
        
            //Set barcode module width
            settings.setX(3);
            //Set error correction level
            settings.setQRCodeECL(QRCodeECL.M);

            //Set bottom text
            settings.setBottomTextFont(new Font("GALLAECIA", Font.PLAIN, 15));
            settings.setBottomText("Lembranzas do Tambre");     

            //Set text visibility
            settings.setShowText(false);
            settings.setShowTopText(false);
            settings.setShowBottomText(true);

            //Set border visibility
            settings.hasBorder(false);

            BarCodeGenerator gen = new BarCodeGenerator(settings);

            BufferedImage img = gen.generateImage();
            ImageIO.write(img, "png", f);
            return true;
        
        } catch (Exception ex) {
            Logger.getLogger(LemTamApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }    
    
    
    private static int getMaxID(){
        List<Prenda> list = getAll();
        
        int max = 0;
        
        for(Prenda p:list){
            if(p.getId()>max){
                max=p.getId();
            }
        }
        
        return max+1;
    }
    
    private static Session getSession(){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prenda.class).buildSessionFactory();
	return sf.openSession();
    }
}
