package com.onlinehotelreservations.controller.feedback;

import com.onlinehotelreservations.config.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @GetMapping("/tests")
    public void test() {
        System.out.println(SecurityUtils.getCurrentUserEmail());
    }
}
