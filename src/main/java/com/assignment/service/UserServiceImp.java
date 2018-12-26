package com.assignment.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.dao.UserDao;
import com.assignment.model.User;
import com.assignment.utility.CryptAlgo;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{

	 @Autowired
	 private UserDao userDao;
	 
	 @Transactional
	 @Override
	   public Integer save(User user) throws Exception {
		 user.setPassword(CryptAlgo.encrypt(user.getPassword()));
	      return userDao.save(user);
	   }

	   @Override
	   public User get(Integer id) throws Exception {
		   User user = userDao.get(id);
		   user.setPassword(CryptAlgo.decrypt(user.getPassword()));
	      return user;
	   }

	   @Override
	   public List<User> list() throws Exception {
		   List<User> users = userDao.list();
		   for(User u : users){
			   u.setPassword(CryptAlgo.decrypt(u.getPassword()));
		   }
	      return users;
	   }

	   @Transactional
	   @Override
	   public void update(Integer id, User user) throws Exception {
		   user.setPassword(CryptAlgo.encrypt(user.getPassword()));
	      userDao.update(id, user);
	   }

	   @Transactional
	   @Override
	   public void delete(Integer id) {
	      userDao.delete(id);
	   }
}
