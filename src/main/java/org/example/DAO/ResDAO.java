package org.example.DAO;

import org.example.Model.ResEntity;
import org.example.Model.UserEntity;

import java.util.List;

public interface ResDAO {
    public  void addRes(ResEntity ResEntity);
    public ResEntity getResById(int id);
    public List<ResEntity> getAllRes();
    public void deleteRes(int id);
    public ResEntity updateRes(ResEntity ResEntity);
}
