package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="T_USERTASK")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTask   extends AbstractLifecycleEntity  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -293360635473212731L;

	/**
	 * userId.
	 */
	@ManyToOne
	@JoinColumn(name="USER_ID")
    private User User;
	/**
	 * taskName.
	 */
	@ManyToOne
    @JoinColumn(name="TASK_ID")
    private Task task;
	 @Column
	 private String taskMemo;
    @Column
    @NotNull
    private Date finishedDate;
  
	@Column
    private BigDecimal taskScore;
	 /**
   	 * userGroup.
   	 */
    @ManyToOne
    @JoinColumn(name="AUDIT_USER_ID")
	private User auditUser;
   
}