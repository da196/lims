/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.service;

import java.util.List;
import tz.go.tcra.lims.dto.PasswordResetRequest;
import tz.go.tcra.lims.dto.Registration;
import tz.go.tcra.lims.dto.RolePermissionsRequest;
import tz.go.tcra.lims.dto.SignUpRequest;
import tz.go.tcra.lims.dto.User;
import tz.go.tcra.lims.dto.User2;
import tz.go.tcra.lims.dto.UserCreationRequest;
import tz.go.tcra.licensing.dto.SaveResponse;
import tz.go.tcra.licensing.entity.account.UserEntity;

/**
 *
 * @author emmanuel.mfikwa
 */
public interface IUserService {
    
    public List<User2> listAll();
    public List<User2> listByStatus(int status);
    public List<Registration> listRegistrations();
    public User details();
    public User composeUser(UserEntity data);
    public User2 composeUser2(UserEntity data);
    public SaveResponse saveRegistration(SignUpRequest data);
    public SaveResponse activateUser(String data);
    public SaveResponse savePasswordReset(PasswordResetRequest data);
    public SaveResponse forgotPassword(String email);
    public SaveResponse saveUser(UserCreationRequest data);
    public SaveResponse saveUserRolePermission(List<RolePermissionsRequest> data,int user_id);
}
