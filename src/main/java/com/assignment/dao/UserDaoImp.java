package com.assignment.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
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
	   public Integer save(User user) {
//		  user.setPassword(generateHash(user.getPassword()));
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
//	@Override
//	   public User get(Integer id) {
//			User user = sessionFactory.getCurrentSession().get(User.class, id);
//			if(null != user){
//				user.setPassword(generateHash(user.getPassword()));
//			}
//			return user;
//	   }
	@Override
	   public User get(Integer id) {
			return sessionFactory.getCurrentSession().get(User.class, id);
	   }

	@Override
	   public List<User> list() {
	      Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<User> cq = cb.createQuery(User.class);
	      Root<User> root = cq.from(User.class);
	      cq.select(root);
	      Query<User> query = session.createQuery(cq);
	      List<User> users = query.getResultList();
//	      if(null != users){
//	    	  Iterator<User> userItr = users.iterator();
//	    	  while (userItr.hasNext()) {
//	    		  User user = userItr.next();
//				  if(null != user){
//					  user.setPassword(generateHash(user.getPassword()));
//				  }
//			}
//	    	  
//	      }
	      return users;
	    		  
	   }

	@Override
	   public void update(Integer id, User user) {
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
	
	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
}
