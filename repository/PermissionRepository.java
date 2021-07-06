/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tz.go.tcra.lims.entity.PermissionEntity;

/**
 *
 * @author emmanuel.mfikwa
 */
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer>{
    
    public List<PermissionEntity> findByOrderByCodeDesc();
    
    public List<PermissionEntity> findByStatus(int status);
}
