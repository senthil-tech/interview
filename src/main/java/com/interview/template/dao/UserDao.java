package com.interview.template.dao;

import com.interview.template.exceptions.ReservedUserNameException;
import com.interview.template.exceptions.UserAlreadyExitException;
import java.util.List;
import java.util.Optional;

import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    @Value("${userblacklist}")
    private final List<String> blacklist;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> find(long id) {
        return userRepository.findById(id);
    }

    public UserEntity findOrDie(long id) throws UserNotFoundException {
        return find(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist."));
    }

    public void checkExists(long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " does not exist.");
        }
    }

    /**
     *
     * @param userEntity
     * @return
     * @throws UserAlreadyExitException
     * @throws ReservedUserNameException
     */
    public UserEntity createUser(UserEntity userEntity) throws UserAlreadyExitException, ReservedUserNameException {

        if (userEntity.getId() != null) {
            throw new UserAlreadyExitException("User already exists.");
        } else if (blacklist.contains(userEntity.getUsername().toLowerCase())) {
            throw new ReservedUserNameException("Reserved User Name Not Allowed");
        }

        return userRepository.save(userEntity);
    }

    /**
     *
     * @param id
     */
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    /**
     *
     * @param username
     * @return
     */
    public List<UserEntity> searchUserByName(String username) {
        return userRepository.searchUserByName(username);
    }
}
