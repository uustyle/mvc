package sample.customer.biz.service;

import org.springframework.stereotype.Component;

@Component
public class SomeService implements ISomeService {

    public String getMessage(String param) {
    	System.out.println("aa");
        return "message";
    }

}
