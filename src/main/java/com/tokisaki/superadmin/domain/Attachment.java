package com.tokisaki.superadmin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="T_ATTACHMENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment   extends AbstractLifecycleEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -293360635473212731L;

	/**
	 * username.
	 */
    @NotEmpty
    private String attachName;
    @NotEmpty
    private String attachType;
    @NotEmpty
    private String attachUrl;
}
