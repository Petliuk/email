package com.example.email.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long  id;
    @Column(name = "name")
    String  name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email")
    String email;

}
