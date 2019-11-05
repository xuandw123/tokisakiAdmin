package com.tokisaki.superadmin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public abstract  class AbstractPersistableEntity<ID> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1568851656740741822L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @Version
    private Long version;
}
