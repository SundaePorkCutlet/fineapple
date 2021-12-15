package kr.or.fineapple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
     System.out.println("index");
    	return "index/index.html";
    }

}
