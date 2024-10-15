package org.example.models;

import jakarta.persistence.*;

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

    public Citizen() {
    }

    public Citizen(String name, City city) {
        this.name = name;
        this.city = city;
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
