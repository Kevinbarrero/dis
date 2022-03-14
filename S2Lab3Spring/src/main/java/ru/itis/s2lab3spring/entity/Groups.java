package ru.itis.s2lab3spring.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Groups{
    @Id
    @Column(name="id")
    @Getter
    @Setter
    Long id;

    @Column(name="number")
    @Getter
    @Setter
    String number;
    public Groups(){

    }

    public Groups(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
