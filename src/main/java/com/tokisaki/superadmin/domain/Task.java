package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import com.tokisaki.superadmin.enums.StatusEnum;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_TASK")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task extends AbstractLifecycleEntity {

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
    @JoinColumn(updatable = false, name="CREATE_USER_ID")
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
	@Fetch(FetchMode.SELECT)
	@JsonManagedReference
	@OneToMany(mappedBy = "task",fetch=FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaskAttachment> taskAttachment= new ArrayList<>();
	/**
	 * type
	 * 0 短期
	 * 1 长期
	 */
	@Column
    private long taskOrder;
}
