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
@Table(name = "lims_user_password_reset", schema="lims")
@Data
@NoArgsConstructor
public class UserPasswordResetEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userId;
    
    @OneToOne
    @JoinColumn(name="token_id")
    private UserAccessTokenEntity token;
    
    @Column(name="password")
    private String password;
    
    @Column(name="password_reset")
    private String passwordReset;
    
    @Column(name="active")
    private int status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_updated")
    private LocalDateTime updatedAt;
    
    @Column(name="unique_id")
    private UUID uniqueID;
}
