package org.example.DAO;

import org.example.Model.RoleEntity;
import org.example.Model.UserEntity;

import java.util.List;

public interface UserDAO {
    public  void addUser(UserEntity user);
    public UserEntity getUserById(int id);
    public List<UserEntity> getAllUsers();
    public void deleteUser(int id);
    public UserEntity updateUser(UserEntity user);
}
