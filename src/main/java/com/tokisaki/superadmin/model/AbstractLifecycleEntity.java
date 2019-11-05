package com.tokisaki.superadmin.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * Trace the times of creation, modification for the entities.
 * For simplicity we do not include the tracing of people who create, modify and delete(disable) entities.
 *
 */
@MappedSuperclass
public abstract class AbstractLifecycleEntity extends AbstractAuditableEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 865289318317276096L;
	@Column(nullable = false)
	@JsonIgnore
    private boolean disabled = false;


    public void disable() {
        this.setDisabled(true);
    }

    public void enable() {
        this.setDisabled(false);
    }


    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}