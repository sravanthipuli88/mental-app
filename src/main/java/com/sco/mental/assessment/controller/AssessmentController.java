package com.sco.mental.assessment.controller;

import com.sco.mental.assessment.dto.*;
import com.sco.mental.assessment.entity.AssessmentType;
import com.sco.mental.assessment.service.AssessmentService;
import com.sco.mental.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.sco.mental.assessment.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 
 * Mental Health Assessment

[ Depression Test ]
[ Anxiety Test ]
[ Stress Test ]
[ Well Being Test ]

PHQ9- test
Over the last 2 weeks:

1. Little interest or pleasure
   0= Not at all
   1= Several days
   2= More than half
   3= Nearly every day


2. Feeling down
   0= Not at all
   1= Several days
   2= More than half
   3= Nearly every day
 * 
 * PSS-10
 * 0 = Never
1 = Almost Never
2 = Sometimes
3 = Fairly Often
4 = Very Often

GAD-7
0 = Not at all
1 = Several days
2 = More than half the days
3 = Nearly every day
 * 
 * 
 * WHO-5 Well-Being Assessment
 * 0 = At no time
1 = Some of the time
2 = Less than half
3 = More than half
4 = Most of the time
5 = All of the time
 * 
 * 
 * 
 * 
 * 
 */




@RestController
@RequestMapping("/api/v1/assessment")
@RequiredArgsConstructor
public class AssessmentController {


private final AssessmentService service;
	

/**
 * GET /api/v1/assessment/questions/PHQ9
GET /api/v1/assessment/questions/GAD7
GET /api/v1/assessment/questions/PSS10
GET /api/v1/assessment/questions/WHO5
 * @param type
 * @return
 */
@GetMapping("/questions/{type}")
public List<QuestionResponse> questions( @PathVariable AssessmentType type){
	return service.getQuestions(type);
}




@PostMapping
public AssessmentResponse submit(@AuthenticationPrincipal User user,@RequestBody AssessmentRequest request){
	return service.submit(user, request);
}




//@PreAuthorize("hasAnyRole('USER','ADMIN')")
@GetMapping("/history")
public List<AssessmentResponse> history(@AuthenticationPrincipal User user){
	System.out.println(" inside the controller");
return service.getAllAssessments(user);
}


}
