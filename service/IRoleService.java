/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.service;

import java.util.List;
import tz.go.tcra.lims.dto.Role;
import tz.go.tcra.lims.dto.RolePermissions;
import tz.go.tcra.lims.dto.RolePermissionsRequest;
import tz.go.tcra.lims.dto.RoleRequest;
import tz.go.tcra.licensing.dto.SaveResponse;
import tz.go.tcra.licensing.dto.role.Role2;

/**
 *
 * @author emmanuel.mfikwa
 */
public interface IRoleService {
    
    public List<Role2> listAll();
    public List<Role2> listByStatus(int status);
    public List<RolePermissions> listPermissions(int id);
    public Role details(int id);
    public SaveResponse save(RoleRequest data,int id);
    public SaveResponse savePermissions(RolePermissionsRequest data);
}
