/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.service;

import java.util.List;
import tz.go.tcra.lims.dto.Permission;
import tz.go.tcra.lims.dto.Permission2;
import tz.go.tcra.lims.dto.PermissionRequest;
import tz.go.tcra.licensing.dto.SaveResponse;

/**
 *
 * @author emmanuel.mfikwa
 */
public interface IPermissionService {
    
    public List<Permission2> listAll();
    public List<Permission2> listByStatus(int status);
    public Permission details(int id);
    public SaveResponse save(PermissionRequest data,int id);
}
