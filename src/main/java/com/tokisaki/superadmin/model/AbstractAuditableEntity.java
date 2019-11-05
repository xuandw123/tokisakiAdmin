package com.tokisaki.superadmin.model;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditableEntity extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2414600779413489552L;

	@CreatedDate
    private Date createdOn;
	@CreatedBy
	private String createdBy;
	@LastModifiedDate
    private Date modifiedOn;
    @LastModifiedBy
    private String modifiedBy;

 

}
