package com.example.demo.repository;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

import jakarta.transaction.Transactional;

@Repository
public class UserCustomRepositoryImpl implements IUserCustomRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Optional<User> getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User user where user.username = :username",User.class);
		query.setParameter("username", username);
		query.getResultList();
		return Optional.ofNullable(query.getSingleResult());
	}
}
