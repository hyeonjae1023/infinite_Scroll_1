package com.sbs.exam1.question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    @GetMapping("/question/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Slice<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "/question/question_list";
    }

    @GetMapping("/api/question/list")
    public ResponseEntity<List<Question>> getQuestionsApi(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "kw", defaultValue = "") String kw) {
        Slice<Question> paging = this.questionService.getList(page, kw);
        return new ResponseEntity<>(paging.getContent(), HttpStatus.OK);
    }

    @GetMapping("/question/create")
    public String questionCreate(QuestionForm questionForm) {
        return "/question/question_form";
    }
    @PostMapping("/question/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/question/question_form";
        }
        this.questionService.create(questionForm.getTitle(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
