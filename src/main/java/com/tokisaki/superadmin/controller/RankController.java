package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokisaki.superadmin.entity.UseScoreEntity;
import com.tokisaki.superadmin.service.RankService;

@RestController
@RequestMapping("/api/v1/rank")
public class RankController {

	@Autowired
    private RankService rankService;
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }


    @GetMapping("/groupRank")
    public ResponseEntity<UseScoreEntity>  rankForGroup() {
    	
        return ok(this.rankService.rankForGroupAndLimit());
    }
    @GetMapping("/groupRankforTask/{taskId}")
    public ResponseEntity<UseScoreEntity>  rankForGroupByTaskId(@PathVariable("taskId") String taskId) {
    	
        return ok(this.rankService.rankForGroupByTaskId(taskId));
    }
    @GetMapping("/groupRankforTask/{taskId}/{groupId}")
    public ResponseEntity<UseScoreEntity>  rankForGroupByTaskIdAndGroupId(@PathVariable("taskId") String taskId,@PathVariable("groupId") String groupId) {
    	
        return ok(this.rankService.rankForGroupByTaskIdAndGroupId(taskId,groupId));
    }
    
    @GetMapping("/groupRank/{groupId}")
    public ResponseEntity<UseScoreEntity>  rankForGroupByGroupId(@PathVariable("groupId") String groupId) {
    	
        return ok(this.rankService.rankForGroupAndLimitByGroupId(groupId));
    }
}
