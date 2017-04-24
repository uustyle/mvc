package sample.customer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestfulController {

    @RequestMapping(value = "/api/text/")
    @ResponseBody
    public String getTestMember() {

    	try{
        	Thread.sleep(5000);
    	}catch(Exception e) {

    	}

        return "aaaa";
    }
}