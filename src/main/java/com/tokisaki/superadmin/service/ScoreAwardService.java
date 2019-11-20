package com.tokisaki.superadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.ScoreAward;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.ScoreAwardRepository;

@Component
public class ScoreAwardService  {
	@Autowired
    private ScoreAwardRepository scoreAwardRepository;

    public ScoreAwardService(ScoreAwardRepository scoreAwardRepository) {
        this.scoreAwardRepository = scoreAwardRepository;
    }


    public ScoreAward insert(ScoreAward scoreAward) throws InvalidInputParamException {
    	User currentUser=CommonUtil.getCurrentUser().orElseThrow( () -> new NotFoundException("User",""));
    	scoreAward.setCreateUser(currentUser);
    	scoreAward.setDisabled(false);
    	return this.scoreAwardRepository.save(scoreAward);
    }
    public ScoreAward update(ScoreAward scoreAward) throws InvalidInputParamException {
    	ScoreAward dbScoreAward =this.scoreAwardRepository.findById(scoreAward.getId()).orElseThrow( () -> new NotFoundException("ScoreAward",scoreAward.getId()));
    	dbScoreAward.setAwardPoint(scoreAward.getAwardPoint());
    	dbScoreAward.setAwardTitle(scoreAward.getAwardTitle());
    	dbScoreAward.setScoreAwardAttachment(scoreAward.getScoreAwardAttachment());
    	return this.scoreAwardRepository.save(dbScoreAward);
    }


	public void delete(String scoreAwardId) {
		 scoreAwardRepository.deleteById(scoreAwardId);
	}
    
}
