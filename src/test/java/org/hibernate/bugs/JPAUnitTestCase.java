package org.hibernate.bugs;

import org.hibernate.bugs.entities.Post;
import org.hibernate.bugs.entities.PostDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	@Test
	public void testOneToOneWithInheritanceJoined() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Post post = new Post();
		PostDetails postDetails = new PostDetails();
		postDetails.setPost(post);

		entityManager.persist(post);
		entityManager.persist(postDetails);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
