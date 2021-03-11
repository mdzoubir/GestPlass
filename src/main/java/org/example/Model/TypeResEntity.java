package org.example.Model;

import javax.persistence.*;

@Entity
@Table(name = "typeReservation")
public class TypeResEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String typeRes;
    @Column(nullable = false)
    private int nomberClass;

    public TypeResEntity() {
    }

    public TypeResEntity(String typeRes, int nomberClass) {
        this.typeRes = typeRes;
        this.nomberClass = nomberClass;
    }

    public TypeResEntity(int id, String typeRes, int nomberClass) {
        this.id = id;
        this.typeRes = typeRes;
        this.nomberClass = nomberClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeRes() {
        return typeRes;
    }

    public void setTypeRes(String typeRes) {
        this.typeRes = typeRes;
    }

    public int getNomberClass() {
        return nomberClass;
    }

    public void setNomberClass(int nomberClass) {
        this.nomberClass = nomberClass;
    }


    public void showTypeRes() {
        System.out.println("TypeRes{" +
                "id=" + id +
                ", typeRes='" + typeRes + '\'' +
                ", nomberClass=" + nomberClass +
                '}');
    }
}
