package put.poznan.ai.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import put.poznan.ai.dao.BaseDao;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Transactional
	public int insert(T object) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			session.flush();
			tx.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return -1;
		} finally {
			session.close();
		}
	}

	@Transactional
	public int update(T object) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(object);
			session.flush();
			tx.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return -1;
		} finally {
			session.close();
		}
	}

	@Transactional
	public int delete(T object) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			session.flush();
			tx.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return -1;
		} finally {
			session.close();
		}
	}

	@Transactional
	public abstract List<T> selectAll();

}
