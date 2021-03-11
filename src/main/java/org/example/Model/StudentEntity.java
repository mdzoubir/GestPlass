package org.example.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "studentId")
@Table(name = "student")
public class StudentEntity extends UserEntity{
    @Column(nullable = false)
    private String reservationMax;

    public String getReservationMax() {
        return reservationMax;
    }

    public void setReservationMax(String reservationMax) {
        this.reservationMax = reservationMax;
    }
}
