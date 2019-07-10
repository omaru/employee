package com.omaru.employee.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="department")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Department {
    @Id
    @SequenceGenerator(name="sequence", sequenceName="department_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    @Column
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;
    public Department(String name){
        this.name = name;
    }
}