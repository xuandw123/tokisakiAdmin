package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tokisaki.superadmin.model.AbstractLifecycleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="T_SCOREAWARD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreAward   extends AbstractLifecycleEntity  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -293360635473212731L;
	/**
	 * awardPoint.
	 */
	@Column
    private BigDecimal awardPoint;
	/**
	 * awardTitle.
	 */
    @NotEmpty
    private String awardTitle;
    /**
   	 * userGroup.
   	 */
    @ManyToOne
    @JoinColumn(name="CREATE_USER_ID")
	private User createUser;
    
    @JsonManagedReference
	@OneToMany(mappedBy = "scoreAward",fetch=FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private Set<ScoreAwardAttachment> scoreAwardAttachment= new HashSet<>();
}
