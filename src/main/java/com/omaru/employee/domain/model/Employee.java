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
    @Column(nullable = false,name="last_name")
    private String lastName;
    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean active=true;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    public Employee(String name){
        this.name = name;
    }
}