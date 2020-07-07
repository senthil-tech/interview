package com.interview.template.service;

import com.interview.template.dao.UserDao;
import com.interview.template.exceptions.ReservedUserNameException;
import com.interview.template.exceptions.UserAlreadyExitException;
import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService {

	private final UserDao userDao;

	public List<UserEntity> getAllUsers() {
		return userDao.findAll();
	}

	public UserEntity getUser(long id) throws UserNotFoundException {
		return userDao.findOrDie(id);
	}

	public void checkUserExists(long id) throws UserNotFoundException {
		userDao.checkExists(id);
	}
        
        public UserEntity createUser(UserEntity userEntity) throws UserAlreadyExitException, ReservedUserNameException {
		return userDao.createUser(userEntity);
	}

	public void deleteUser(long id)
	{
		userDao.deleteUser(id);
	}

	/**
	 *
	 * @param username
	 * @return
	 */
	public List<UserEntity> searchUserByName(String username)
	{
		return userDao.searchUserByName(username);
	}
}
