package com.assignment.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.dao.UserDao;
import com.assignment.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{

	 @Autowired
	 private UserDao userDao;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Transactional
	   @Override
	   public Integer save(User user) {
		 user.setPassword(Base64.getEncoder().encodeToString((user.getPassword().getBytes())));
	      return userDao.save(user);
	   }

	   @Override
	   public User get(Integer id) {
		   User user = userDao.get(id);
		   user.setPassword(Base64.getDecoder().decode((user.getPassword())).toString());
	      return user;
	   }

	   @Override
	   public List<User> list() {
	      return userDao.list();
	   }

	   @Transactional
	   @Override
	   public void update(Integer id, User user) {
	      userDao.update(id, user);
	   }

	   @Transactional
	   @Override
	   public void delete(Integer id) {
	      userDao.delete(id);
	   }
}
