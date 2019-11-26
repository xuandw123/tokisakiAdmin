package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tokisaki.superadmin.domain.Task;
import com.tokisaki.superadmin.enums.TaskTypeEnum;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.model.UserScore;
import com.tokisaki.superadmin.repository.TaskRepository;
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
    public ResponseEntity<Object>  rankForGroup() {
    	
        return ok(this.rankService.rankForGroup());
    }
    

}
