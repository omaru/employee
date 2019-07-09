package com.omaru.employee.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="department")
@Getter@Setter@NoArgsConstructor@EqualsAndHashCode
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;
}