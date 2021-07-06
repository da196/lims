package tz.go.tcra.lims.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "lims_user", schema="lims")
@Data
@NoArgsConstructor
public class UserEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
        
    @Column(name="firstname")
    private String firstName;
    
    @Column(name="middle_name")
    private String middleName;
    
    @Column(name="lastname")
    private String lastName;
        
    @Column(name="phone")
    private String phone;
    
    @Column(name="email")
    private String email;
    
    @Column(name="postal_code")
    private String postalCode;
    
    @Column(name="physical_address")
    private String physicalAddress;
    
    @Column(name="region_id")
    private int regionID;
    
    @Column(name="dictrict_id")
    private int districtID;
    
    @Column(name="gender_id")
    private int genderID;
    
    @Column(name="ad_authentication")
    private int adAuthentication;
    
    @Column(name="password")
    private String password;
    
    @Column(name="active")
    private int status;
    
    @OneToMany(mappedBy="user")
    private List<UserRoleEntity> userRole;
    
    @Column(name="date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    
    @Column(name="date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;
    
    @Column(name="unique_id")
    private UUID uniqueID;
}
