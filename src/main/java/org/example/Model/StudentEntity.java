package org.example.Model;


import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "studentId")
@Table(name = "student")
public class StudentEntity extends UserEntity{
    @Column(nullable = false)
    private int reservationMax;

    public StudentEntity(){

    }

    public StudentEntity(int id, String firstName, String lastName, String email, String password, String phone, RoleEntity role, int reservationMax) {
        super(id, firstName, lastName, email, password, phone, role);
        this.reservationMax = reservationMax;
    }

    public StudentEntity(String firstName, String lastName, String email, String password, String phone, RoleEntity role, int reservationMax) {
        super(firstName, lastName, email, password, phone, role);
        this.reservationMax = reservationMax;
    }

    public int getReservationMax() {
        return reservationMax;
    }

    public void setReservationMax(int reservationMax) {
        this.reservationMax = reservationMax;
    }
}
