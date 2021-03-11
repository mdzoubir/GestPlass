package org.example.DAO;

import org.example.Model.RoleEntity;

import java.util.List;

public interface RoleDAO {
    public  void addRole(RoleEntity role);
    public RoleEntity getRoleById(int id);
    public List<RoleEntity> getAllRoles();
    public void deleteRole(int id);
    public RoleEntity updateRole(RoleEntity role);
}
