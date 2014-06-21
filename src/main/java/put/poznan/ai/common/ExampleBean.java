package put.poznan.ai.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import put.poznan.ai.models.Contest;

public class ExampleBean implements Serializable {

	private static final long serialVersionUID = 8983198430517040048L;

	private Contest contest;

	public ExampleBean() {
		contest = new Contest();
	}

	public void testDB() {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// Creating Contact entity that will be save to the sqlite database
//			Contest myContact = new Contest();
//			myContact.setId(8);
//			myContact.setName("fff");

			// Saving to the database
			session.save(contest);

			// Committing the change in the database.
			session.flush();
			tx.commit();

			// Fetching saved data
			@SuppressWarnings("unchecked")
			List<Contest> contactList = session.createQuery("from Contest").list();

			for (Contest contact : contactList) {
				System.out.println("Id: " + contact.getId() + " | Name:"  + contact.getName());
			}

		} catch (Exception ex) {
			ex.printStackTrace();

			// Rolling back the changes to make the data consistent in case of any failure
			// in between multiple database write operations.
			tx.rollback();
		} finally{
			HibernateUtil.closeSession();
		}
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

}
