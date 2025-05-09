package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.UserEntity;
import org.acme.repository.UseRepository;
import java.util.List;
import java.util.NoSuchElementException;


@ApplicationScoped
public class UserService {

    private final UseRepository userRepository;

    @Inject
    public UserService(UseRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userRepository.persist(userEntity);
        return userEntity;
    }



    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(Integer userID) {
        return userRepository.findByIdOptional(String.valueOf(userID))
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com email: " + userID));
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public UserEntity updateUser(Integer userId, UserEntity userEntity) {
        UserEntity user = findById(userId);

        user.setNome(userEntity.getNome());
        user.setEmail(userEntity.getEmail());
        user.setSenha(userEntity.getSenha());

        userRepository.persist(user);
        return user;
    }


    public void deleteById(Integer userId) {
        UserEntity user = findById(userId);
        userRepository.deleteById(user.getId());
    }
}

