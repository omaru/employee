package com.omaru.employee.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="employee")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private boolean active;
    @ManyToOne
    private Department department;
    public Employee(String name){
        this.name = name;
    }
}