package org.example.Model;

import javax.persistence.*;

@Entity
@Table(name = "Reservation")
public class ResEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private UserEntity user;
    @Column(nullable = false)
    private String dateRes;
    private boolean confirmation;
    @OneToOne
    private TypeResEntity typeRes;

    public ResEntity() {
    }

    public ResEntity(int id, UserEntity user, String dateRes, boolean confirmation, TypeResEntity typeRes) {
        this.id = id;
        this.user = user;
        this.dateRes = dateRes;
        this.confirmation = confirmation;
        this.typeRes = typeRes;
    }

    public ResEntity(UserEntity user, String dateRes, boolean confirmation, TypeResEntity typeRes) {
        this.user = user;
        this.dateRes = dateRes;
        this.confirmation = confirmation;
        this.typeRes = typeRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDateRes() {
        return dateRes;
    }

    public void setDateRes(String dateRes) {
        this.dateRes = dateRes;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public TypeResEntity getTypeRes() {
        return typeRes;
    }

    public void setTypeRes(TypeResEntity typeRes) {
        this.typeRes = typeRes;
    }


    public void showRes() {
        System.out.println("Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", dateRes='" + dateRes + '\'' +
                ", confirmation=" + confirmation +
                ", typeRes=" + typeRes +
                '}');
    }
}
