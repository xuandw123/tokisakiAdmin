package com.tokisaki.superadmin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_USERGROUP")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup extends AbstractLifecycleEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
    private String groupName;

	/**
	 * groupStatus.
	 */
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Normal', 'Frozen')")
    private StatusEnum groupStatus;
	@Column
    private String groupInviteCode;
}
