package com.tokisaki.superadmin.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
}
