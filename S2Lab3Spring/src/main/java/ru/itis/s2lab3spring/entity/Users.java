package ru.itis.s2lab3spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name= "users")
public class Users{

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "nam")
    @Getter
    @Setter
    private String name;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name ="role")
    @Getter
    @Setter
    private String role;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "group_id")
    @Getter
    @Setter
    private Groups group_id;

    public Users(){

    }

    public Users(Long id, String name, String password, String role, Groups group_id) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.group_id = group_id;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", group=" + group_id +
                '}';
    }

}
