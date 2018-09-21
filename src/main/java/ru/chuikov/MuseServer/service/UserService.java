package ru.chuikov.MuseServer.service;

import ru.chuikov.MuseServer.entity.Uaccount;

import java.util.List;

public interface UserService {
    List<Uaccount> getAll();
    Uaccount getById(long id);
    Uaccount save(Uaccount uaccount);
    void remove(long id);
    void add(Uaccount uaccount);
}