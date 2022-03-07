package ru.itis.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
class Users {
    @Id
    Long id;
    @Column
    String name;
    @Column
    String password;
    @Column
    String role;
    @ManyToOne
    Groups group;
}
