package ru.chuikov.MuseServer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chuikov.MuseServer.entity.Uaccount;
import ru.chuikov.MuseServer.repository.UserRepository;
import ru.chuikov.MuseServer.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Uaccount> getAll() {
        return userRepository.findAll();
    }

    public Uaccount getById(long id) {
        return userRepository.findOne(id);
    }

    public Uaccount save(Uaccount uaccount) {
        return userRepository.saveAndFlush(uaccount);
    }

    public void remove(long id) {
        userRepository.deleteById(id);
    }

    public void add(Uaccount uaccount) {
        userRepository.saveAndFlush(uaccount);
    }
}
