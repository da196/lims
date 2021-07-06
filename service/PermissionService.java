/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tz.go.tcra.lims.dto.Permission;
import tz.go.tcra.lims.dto.Permission2;
import tz.go.tcra.lims.dto.PermissionRequest;
import tz.go.tcra.lims.entity.PermissionEntity;
import tz.go.tcra.lims.repository.PermissionRepository;
import tz.go.tcra.licensing.dto.SaveResponse;

/**
 *
 * @author emmanuel.mfikwa
 */
@Service
public class PermissionService implements IPermissionService{

    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class.getName());
    
    @Autowired
    private PermissionRepository permissionRepo;
    
    @Override
    public List<Permission2> listAll() {
        List<Permission2> response=new ArrayList();
        try{
        
            List<PermissionEntity> permissions=permissionRepo.findAll();
            
            for(PermissionEntity permission : permissions){
                Permission2 perm=new Permission2();
                perm.setId(permission.getId());
                perm.setCode(permission.getCode());
                perm.setName(permission.getName());
                perm.setDescription(permission.getDescription());
                
                response.add(perm);
                
            }
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public List<Permission2> listByStatus(int status) {
        List<Permission2> response=new ArrayList();
        try{        
            List<PermissionEntity> permissions=permissionRepo.findByStatus(status);
            
            for(PermissionEntity permission : permissions){
                Permission2 perm=new Permission2();
                perm.setId(permission.getId());
                perm.setCode(permission.getCode());
                perm.setName(permission.getName());
                perm.setDescription(permission.getDescription());
                
                response.add(perm);
                
            }
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public Permission details(int id) {
        Permission response=null;
        try{        
            PermissionEntity permission=permissionRepo.getOne(id);
            response=new Permission();
            response.setId(permission.getId());
            response.setCode(permission.getCode());
            response.setName(permission.getName());
            response.setDescription(permission.getDescription());
            response.setCreatedAt(permission.getCreatedAt());
            response.setUpdatedAt(permission.getUpdatedAt());
            response.setUniqueId(permission.getUniqueID());
            
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }

    @Override
    public SaveResponse save(PermissionRequest data, int id) {
        SaveResponse response=new SaveResponse();
        response.setStatus(HttpStatus.CREATED.value());
        response.setDescription(HttpStatus.CREATED.toString());
        response.setTimestamp(System.currentTimeMillis());        
        try{        
            
            PermissionEntity permission=new PermissionEntity();
            if(id > 0){
            
                permission=permissionRepo.getOne(id);
                permission.setUpdatedAt(LocalDateTime.now());
            }else{
            
                permission.setCreatedAt(LocalDateTime.now());
                permission.setUniqueID(UUID.randomUUID());
            }
            
            permission.setId(id);
            permission.setName(data.getName());
            permission.setDescription(data.getDescription());
            permission.setStatus(data.getStatus());
            
            permissionRepo.saveAndFlush(permission);
            
        }catch(Exception e){
        
            logger.error(e.toString());
        }
        
        return response;
    }
    
}
