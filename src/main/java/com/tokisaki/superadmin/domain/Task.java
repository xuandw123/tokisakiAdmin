package com.tokisaki.superadmin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_TASK")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task extends AbstractLifecycleEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
    private String taskName;
	/**
	 * groupStatus.
	 */
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Normal', 'Frozen')")
    private StatusEnum taskStatus;
	/**
	 * type
	 * 0 短期
	 * 1 长期
	 */
	@Column(columnDefinition = "ENUM('ShortTerm', 'LongTerm')")
	@Enumerated(EnumType.STRING)
    private TaskTypeEnum taskType;
	
	@Column(columnDefinition="TEXT")
    private String taskDetail;
	 /**
   	 * userGroup.
   	 */
    @ManyToOne
    @JoinColumn(name="CREATE_USER_ID")
	private User createUser;
	@Column
	@NotNull
    private Date startDate;
	@Column
	@NotNull
    private Date endDate;
	/**
	 * type
	 * 0 短期
	 * 1 长期
	 */
	@Column
    private BigDecimal taskScore;
	/**
	 * type
	 * 0 短期
	 * 1 长期
	 */
	@Column
    private long taskOrder;
}
