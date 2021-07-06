/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tz.go.tcra.lims.entity.RoleEntity;

/**
 *
 * @author emmanuel.mfikwa
 */
public interface RoleRepository extends JpaRepository<RoleEntity,Integer>{
    
    public List<RoleEntity> findByOrderByCodeDesc();
    
    public List<RoleEntity> findByStatus(int status);
}
