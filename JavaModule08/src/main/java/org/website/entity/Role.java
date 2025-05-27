package org.website.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role() {}

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public void addUser(User user) {
        users.add(user);
        user.setRole(this); // Устанавливаем связь обратно
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRole(null); // Убираем связь
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }
}
