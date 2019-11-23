package com.tokisaki.superadmin.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokisaki.superadmin.domain.ScoreAward;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.model.Response;
import com.tokisaki.superadmin.repository.ScoreAwardRepository;
import com.tokisaki.superadmin.service.ScoreAwardService;

@RestController
@RequestMapping("/api/v1/scoreAward")
public class ScoreAwardController {
	@Autowired
	private ScoreAwardRepository scoreAwardRepository;
	@Autowired
	private ScoreAwardService scoreAwardService;

	public ScoreAwardController(ScoreAwardRepository scoreAwardRepository) {
		this.scoreAwardRepository = scoreAwardRepository;
	}

	@GetMapping("")
	public ResponseEntity<Object> all() {
		return ok(this.scoreAwardRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") String id) {
		return ok(this.scoreAwardRepository.findById(id).orElseThrow(() -> new NotFoundException("ScoreAward", id)));
	}

	@PutMapping(name = "Update ScoreAward", value = "/{scoreAwardId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateScoreAward(@RequestBody ScoreAward scoreAwardForm,
			@PathVariable("scoreAwardId") String scoreAwardId) throws InvalidInputParamException {
		scoreAwardForm.setId(scoreAwardId);
		ScoreAward saved = this.scoreAwardService.update(scoreAwardForm);
		return ok(saved);
	}

	@PostMapping(name = "Insert ScoreAward", value = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertScoreAward(@RequestBody ScoreAward scoreAwardForm)
			throws InvalidInputParamException, URISyntaxException {
		ScoreAward saved = this.scoreAwardService.insert(scoreAwardForm);
		return created(new URI(saved.getId())).build();
	}

	@DeleteMapping(name = "Delete ScoreAward", value = "/{scoreAwardId}")
    	    public ResponseEntity<Object>  deleteScoreAward(@PathVariable("scoreAwardId") String scoreAwardId) throws InvalidInputParamException, URISyntaxException{
		Response<Void> res=new Response<>();
    	scoreAwardService.delete(scoreAwardId);
    	        return ok(res);
    	    }

}
