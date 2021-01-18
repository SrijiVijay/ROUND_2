package com.kgisl.twofa.service;

import java.util.List;
import java.util.Optional;

import com.kgisl.twofa.model.User;

/**
 * The Interface IUserService.
 */
public interface IUserService {

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 */
	void createUser(User user);
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	void updateUser(User user);
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	void deleteUser(Integer id);
	
	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	List<User>getAllUsers();
	
	/**
	 * Gets the one user.
	 *
	 * @param id the id
	 * @return the one user
	 */
	Optional<User> getOneUser(Integer id);
}
