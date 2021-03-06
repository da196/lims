/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author emmanuel.mfikwa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    
    private String firstName;
    private String middleName;
    private String lastName;
    private int adAuthentication;
    private String phone;
    private String email;
}
