package com.assignment.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.model.User;

@Repository
public class UserDaoImp implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	   public Integer save(User user)  throws Exception{
		try{
			 user = (User) sessionFactory.getCurrentSession().save(user);
			 if(null != user)
		      return user.getId();
			 return 0;
		}catch(Exception e){
			e.printStackTrace();
			 return 0;
		}
	   }

	@Override
	   public User get(Integer id)  throws Exception{
			return sessionFactory.getCurrentSession().get(User.class, id);
	   }

	@Override
	   public List<User> list()  throws Exception{
	      Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<User> cq = cb.createQuery(User.class);
	      Root<User> root = cq.from(User.class);
	      cq.select(root);
	      Query<User> query = session.createQuery(cq);
	      List<User> users = query.getResultList();
	      return users;
	    		  
	   }

	@Override
	   public void update(Integer id, User user)  throws Exception{
	      Session session = sessionFactory.getCurrentSession();
	      User user2 = session.byId(User.class).load(id);
	      user2.setUserName(user2.getUserName());
	      user2.setPassword(user.getPassword());
	      user2.setStatus(user.getStatus());
	      session.flush();
	   }

	@Override
	   public void delete(Integer id) {
	      Session session = sessionFactory.getCurrentSession();
	      User user = session.byId(User.class).load(id);
	      session.delete(user);
	}
}
