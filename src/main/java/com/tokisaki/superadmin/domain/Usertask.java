package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="T_USERTASK")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usertask   extends AbstractLifecycleEntity  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -293360635473212731L;

	/**
	 * userId.
	 */
	@ManyToOne
	@JoinColumn(name="USER_ID")
    private User user;
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
    @Column
    private Date auditDate;
	@JsonManagedReference
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "usertask", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<UsertaskAttachment> utAttachment= new ArrayList<>();
}
