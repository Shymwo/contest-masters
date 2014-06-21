package put.poznan.ai.common;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	private static Session session;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration config = new Configuration().configure();
		Properties properties = config.getProperties();

		serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(properties).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public static Session getSession() throws HibernateException {
		if (session == null) {
			configureSessionFactory();
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		if (session != null) {
			session.close();
		}
	}
}
