/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author emmanuel.mfikwa
 */
@Entity
@Table(name="lims_user_permissions", schema="lims")
@Data
@NoArgsConstructor
public class UserPermissionEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    
    @OneToOne
    @JoinColumn(name="permission_id")
    private PermissionEntity permission;
    
    @Column(name="date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime datecreated;
    
    @Column(name="date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateupdated;
    
    @Column(name="unique_id")
    private UUID uniqueID;
}
