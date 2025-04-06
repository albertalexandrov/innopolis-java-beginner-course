package ru.innopolis.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.CreateUpdateUserDto;
import ru.innopolis.entity.User;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.service.UserService;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(CreateUpdateUserDto data) {
        var user = modelMapper.map(data, User.class);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(long userId, CreateUpdateUserDto data) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return optionalUser;
        }
        User user = optionalUser.get();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user = userRepository.save(user);
        return Optional.of(userRepository.save(user));
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
