package com.omaru.employee.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name="employee")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @NotNull
    @Column(nullable = false,name="fist_name")
    private String firstName;
    @Column(name="middle_initial")
    private Character middleInitial;
    @NotNull
    @Column(nullable = false,name="last_name")
    private String lastName;
    @NotNull
    @Column(nullable=false,name="date_of_birth")
    private Timestamp dateOfBirth;
    @Column(nullable = false,name="date_of_employment")
    private Timestamp dateOfEmployment = new Timestamp(Calendar.getInstance().getTimeInMillis());
    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean active=true;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    public Employee(String firstName){
        this.firstName = firstName;
    }
}