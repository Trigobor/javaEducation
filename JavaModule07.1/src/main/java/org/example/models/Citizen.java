package org.example.models;

import jakarta.persistence.*;
import org.example.enums.CitizenshipStatus;

@Entity
@Table(name = "citizens")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citizens_seq")
    @SequenceGenerator(name = "citizens_seq", sequenceName = "citizens_id_autoincrement_seq", allocationSize = 1)
    private Integer id;

    @Column (name = "citizen_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column (name = "salary")
    private int salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "citizenship")
    private CitizenshipStatus citizenship;

    public Citizen() {
    }

    public Citizen(String name, City city, int salary, CitizenshipStatus citizenship) {
        this.name = name;
        this.city = city;
        this.salary = salary;
        this.citizenship = citizenship;
    }

    public Integer getId() {return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
