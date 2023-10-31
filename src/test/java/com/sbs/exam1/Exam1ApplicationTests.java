package com.sbs.exam1;

import com.sbs.exam1.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Exam1ApplicationTests {
	@Autowired
	private QuestionService questionService;

	@Test
	void contextLoads() {
		for (int i = 1; i <= 300; i++) {
			String title = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(title, content);
		}
	}

}
