package org.hibernate.bugs;

import org.hibernate.bugs.entities.Post;
import org.hibernate.bugs.entities.PostDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void testOneToOneWithInheritanceJoined() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Post post = new Post();
		PostDetails postDetails = new PostDetails();
		postDetails.setPost(post);

		entityManager.persist(post);
		entityManager.persist(postDetails);

		// Do stuff...
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
