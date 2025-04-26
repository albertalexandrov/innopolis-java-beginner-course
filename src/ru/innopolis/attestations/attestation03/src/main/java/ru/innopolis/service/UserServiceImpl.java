package ru.innopolis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.UserCreateUpdateDTO;
import ru.innopolis.entity.User;
import ru.innopolis.mappers.UserMapper;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.exceptions.UserNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserCreateUpdateDTO data) {
        var user = userMapper.map(data);
        user.setIsDeleted(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository
                .findFirstByIdAndIsDeleted(userId, false)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> listUsers() {
        return userRepository
                .findAllByIsDeleted(false);
    }

    @Override
    public User updateUser(Long userId, UserCreateUpdateDTO data) {
        var user = userRepository
                .findFirstByIdAndIsDeleted(userId, false)
                .orElseThrow(UserNotFoundException::new);
        user.setLastName(data.getLastName());
        user.setFirstName(data.getFirstName());
        user.setPhoneNumber(data.getPhoneNumber());
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        var user = userRepository
                .findFirstByIdAndIsDeleted(userId, false)
                .orElseThrow(UserNotFoundException::new);
        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
