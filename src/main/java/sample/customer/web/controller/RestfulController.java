package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.customer.biz.service.ParamImplService;
import sample.customer.biz.service.SomeService;

@Controller
public class RestfulController {

	@Autowired
	private ParamImplService paramImplService;

	@Autowired
	private SomeService someService;

//    @RequestMapping(value = "/", method = GET)
    @RequestMapping(value = "/api/text/", method = GET)
    @ResponseBody
    public String getTestMember() {

    	try{
        	Thread.sleep(5000);
    	}catch(Exception e) {

    	}

        return "aaaa";
    }


//  @RequestMapping(value = "/", method = GET)
  @RequestMapping(value = "/api/text2/", method = GET)
  @ResponseBody
  public String getTestMember2() {

//	  paramImplService.globalMessage();

      return paramImplService.globalMessage();
  }


//@RequestMapping(value = "/", method = GET)
@RequestMapping(value = "/api/text3/", method = GET)
@ResponseBody
public String getTestMember3() {

//	  paramImplService.globalMessage();
    return someService.message();

}



}