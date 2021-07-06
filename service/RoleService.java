/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.go.tcra.licensing.dto.SaveResponse;
import tz.go.tcra.licensing.dto.role.Role2;
import tz.go.tcra.lims.dto.Role;
import tz.go.tcra.lims.dto.RolePermissions;
import tz.go.tcra.lims.dto.RolePermissionsRequest;
import tz.go.tcra.lims.dto.RoleRequest;
import tz.go.tcra.lims.entity.RoleEntity;
import tz.go.tcra.lims.repository.RoleRepository;

/**
 *
 * @author emmanuel.mfikwa
 */
@Service
public class RoleService implements IRoleService{

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class.getName());
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private IPermissionService permissionService;
    
    @Override
    public List<Role2> listAll() {
        List<Role2> response=new ArrayList();
        try{
        
            List<RoleEntity> roles=roleRepo.findAll();
            
            for(RoleEntity data : roles){
                Role2 dt=new Role2();
                dt.setId(data.getId());
                dt.setCode(data.getCode());
                dt.setName(data.getName());
                
                response.add(dt);
                
            }
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public List<Role2> listByStatus(int status) {
        List<Role2> response=new ArrayList();
        try{
        
            List<RoleEntity> roles=roleRepo.findByStatus(status);
            
            for(RoleEntity data : roles){
                Role2 dt=new Role2();
                dt.setId(data.getId());
                dt.setCode(data.getCode());
                dt.setName(data.getName());
                
                response.add(dt);
                
            }
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public List<RolePermissions> listPermissions(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role details(int id) {
        Role response=null;
        try{        
            RoleEntity role=roleRepo.getOne(id);
            response=new Role();
            response.setId(role.getId());
            response.setCode(role.getCode());
            response.setName(role.getName());
            response.setDescription(role.getDescription());
            response.setPermissions(null);
//            response.setPermissions();//
            response.setCreatedAt(role.getCreatedAt());
            response.setUpdatedAt(role.getUpdatedAt());
            response.setUniqueID(role.getUniqueID());
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public SaveResponse save(RoleRequest data, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SaveResponse savePermissions(RolePermissionsRequest data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
