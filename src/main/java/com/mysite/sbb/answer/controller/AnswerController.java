package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.question.domain.Question;
import com.mysite.sbb.question.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        // 질문만들기
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, content);
        return String.format("redirect:/question/detail/%s", id);
    }

    @PostMapping("/like/{questionId}/{answerId}")
    public String createAnswer(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId) {
        this.answerService.setLike(answerId);

        return String.format("redirect:/question/detail/%s", questionId);
    }

}