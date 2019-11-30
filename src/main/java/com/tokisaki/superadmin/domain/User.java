package com.tokisaki.superadmin.domain;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="T_USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User   extends AbstractLifecycleEntity implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = -293360635473212731L;
	/**
	 * userCode.
	 */
	@Column
    private String userCode;
	/**
	 * username.
	 */
    @NotEmpty
    private String username;
	/**
	 * userStatus.
	 */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Normal', 'Frozen')")
    private StatusEnum userStatus;
	/**
	 * nickName.
	 */
    @Column
    private String nickName;
    /**
	 * qqNo.
	 */
    @Digits(integer=11, fraction=0)
    private int qqNo;
    /**
	 * 是否使用自定义头像.
	 */
    @Builder.Default
    @Column
    private boolean  selfIcon=false;
    /**
	 * 头像文件id.
	 */
    @Column
    private String  iconId;
    /**
     * password.
     */
    @NotEmpty
    @JsonIgnore
    private String password;
    @Transient
    private BigDecimal totalScore;
    @Column
    private Date registerDate;
    /**
   	 * userGroup.
   	 */
    @Fetch(FetchMode.SELECT)
    @OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="groupId",referencedColumnName="ID")
	private UserGroup userGroup;
    /**
   	 * 组的级别.
   	 */
       @Column
       private String  groupTitle;
       @Fetch(FetchMode.SELECT)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
       @Column
       private String openid;
       
       @Column(columnDefinition = "ENUM('0', '1')")
       private String ifblind;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
