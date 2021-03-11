package org.example.Model;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class RoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String roleName;

    public RoleEntity() {
    }

    public RoleEntity(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public void showRole() {
        System.out.println("RoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}');
    }
}
