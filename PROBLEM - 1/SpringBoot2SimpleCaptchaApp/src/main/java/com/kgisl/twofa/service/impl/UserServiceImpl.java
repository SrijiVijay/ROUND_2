package com.kgisl.twofa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.twofa.model.User;
import com.kgisl.twofa.service.IUserRepository;
import com.kgisl.twofa.service.IUserService;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements IUserService {

	/** The repo. */
	@Autowired
	private IUserRepository repo;

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 */
	@Override
	public void createUser(User user) {
		repo.save(user);
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@Override
	public List<User> getAllUsers() {

		return repo.findAll();
	}

	/**
	 * Gets the one user.
	 *
	 * @param id the id
	 * @return the one user
	 */
	@Override
	public Optional<User> getOneUser(Integer id) {
		return repo.findById(id);
	}

	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	@Override
	public void updateUser(User user) {
		repo.save(user);

	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteUser(Integer id) {
		repo.deleteById(id);
	}
}
