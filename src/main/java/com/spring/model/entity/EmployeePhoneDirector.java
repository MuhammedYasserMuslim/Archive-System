package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_phone_director")
public class EmployeePhoneDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial" , nullable = false)
    private Integer serial;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "job")
    private String job;

    @Column(name = "department")
    private String department;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "notes")
    private String notes;
}
