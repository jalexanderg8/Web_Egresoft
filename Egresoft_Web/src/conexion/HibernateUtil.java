package conexion;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static final SessionFactory sessionFactory=buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		
			try{
			
				return new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			}catch(Exception e){
			
				System.err.println("Fallo conexion con Hibernate"+ e);
			
				throw new ExceptionInInitializerError(e);
			}
			}
			public static SessionFactory getSessionFactory(){
			return sessionFactory;
		}
			public void shutdown(){
			getSessionFactory().close();
		}

}
