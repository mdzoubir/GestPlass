package org.example.DAO;

import org.example.Model.TypeResEntity;
import org.example.Model.UserEntity;

import java.util.List;

public interface TypeResDAO {
    public  void addTypeRes(TypeResEntity typeResEntity);
    public TypeResEntity getTypeResById(int id);
    public List<TypeResEntity> getAllTypeRes();
    public void deleteTypeRes(int id);
    public TypeResEntity updateTypeRes(TypeResEntity typeResEntity);
}
