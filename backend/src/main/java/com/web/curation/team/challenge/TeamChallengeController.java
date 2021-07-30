package com.web.curation.team.challenge;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.member.Member;
import com.web.curation.model.BasicResponse;
import com.web.curation.team.TeamController;
import com.web.curation.team.challenger.TeamChallengerParticipation;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController("/challenge")
public class TeamChallengeController {
	@Autowired
	private TeamChallengeService teamChallengeService;
	
	@GetMapping("/my_teamchallenge_list/{member_id}")
	@ApiOperation(value = "내 팀 챌린지 리스트")
	public Object findTeamChallenges(@Valid @RequestParam(name = "member_id") int memberId) {

		List<TeamChallenge> list = teamChallengeService.getTeamChallengeList(memberId);
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
        if(list == null) {
        	result.status =false;
        	result.data = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }else {
        	 result.status = true;
             result.data = "success";
             result.object = list;
             response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;
	}
	
	@PutMapping("/team_challenge_enroll")
	@ApiOperation(value = "팀 챌런지 생성하기")
	public Object createTeamChallenge(@Valid @RequestBody TeamChallengeCreationRequest creationRequest) {
		
		boolean ret = teamChallengeService.addTeamChallenge(creationRequest);
		
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
        if(ret) {
        	result.status = true;
            result.message = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        	
        }else {
        	result.status =false;
        	result.message = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        return response;
	}
	
	@PostMapping("/team_challenge_participate")
	@ApiOperation(value = "팀 챌런지 참여하기")
	public Object participateTeamChallenge(@Valid @RequestBody TeamChallengerParticipation participation) {
		
		boolean ret = teamChallengeService.participateTeamChallenge(participation);
		
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
        if(ret) {
        	result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        	
        }else {
        	result.status =false;
        	result.data = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        return response;
	}
	
	@PostMapping("/team_challenge_giveup")
	@ApiOperation("챌린지 포기하기")
	public Object giveupTeamChallenge(@Valid @RequestBody TeamChallengerParticipation participation) {
		
		boolean ret = teamChallengeService.giveupTeamChallenge(participation);
		
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
        if(ret) {
	    	result.status = true;
	        result.data = "success";
	        response = new ResponseEntity<>(result, HttpStatus.OK);
        }else {
        	result.status =false;
        	result.data = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        return response;
	}
	
//	@PostMapping("/updatechallenge")
//	@ApiOperation(value = "팀 챌린지 수정하기")
//	public Object updateTeamChallenge(@Valid @RequestBody TeamController updation) {
//		
//		List<TeamChallenge> list = teamChallengeService.updateTeamChallenge(updation);
//		BasicResponse result = new BasicResponse();
//		ResponseEntity response = null;
//        if(list == null) {
//        	result.status =false;
//        	result.data = "fail";
//        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }else {
//        	 result.status = true;
//             result.data = "success";
//             result.object = list;
//             response = new ResponseEntity<>(result, HttpStatus.OK);
//        }
//	}
//	

}
