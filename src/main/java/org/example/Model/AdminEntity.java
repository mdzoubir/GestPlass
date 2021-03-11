package org.example.Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "adminId")
@Table(name = "admin")
public class AdminEntity extends UserEntity{
}
