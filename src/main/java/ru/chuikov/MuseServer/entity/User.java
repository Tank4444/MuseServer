package ru.chuikov.MuseServer.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
     public Long id;

}
