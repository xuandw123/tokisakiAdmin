package com.tokisaki.superadmin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_TASK_Attachment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskAttachment extends AbstractLifecycleEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * taskName.
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="TASK_ID")
    private Task task;
	/**
	 * taskName.
	 */
	@ManyToOne
    @JoinColumn(name="Attachment_ID")
    private Attachment attachment;
	/**
	 * type
	 * 0 短期
	 * 1 长期
	 */
	@Column
    private long taskOrder;
}
