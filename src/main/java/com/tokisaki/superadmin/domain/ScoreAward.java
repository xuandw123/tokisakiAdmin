package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@JsonManagedReference
	@OneToMany(mappedBy = "scoreAward", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ScoreAwardAttachment> scoreAwardAttachment;
}
