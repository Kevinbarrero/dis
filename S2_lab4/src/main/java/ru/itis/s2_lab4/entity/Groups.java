package ru.itis.s2_lab4.entity;


import lombok.Getter;
import lombok.Setter;
import ru.itis.s2_lab4.orm.annotations.Column;
import ru.itis.s2_lab4.orm.annotations.Entity;

@Entity
public class Groups {
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
