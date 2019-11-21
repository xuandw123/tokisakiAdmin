package com.tokisaki.superadmin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_TASK_Attachment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskAttachment extends AbstractLifecycleEntity  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * taskName.
	 */
	@JsonBackReference
	@ManyToOne
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
