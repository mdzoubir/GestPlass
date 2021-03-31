package org.example.Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
@Table(name = "admin")
public class AdminEntity extends UserEntity{
    public AdminEntity() {
    }

    public AdminEntity(String firstName, String lastName, String email, String password, String phone, RoleEntity role) {
        super(firstName, lastName, email, password, phone, role);
    }

    public AdminEntity(int id, String firstName, String lastName, String email, String password, String phone, RoleEntity role) {
        super(id, firstName, lastName, email, password, phone, role);
    }
}
