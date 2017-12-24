package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sample.customer.biz.service.ISomeService;
import sample.customer.biz.service.ParamImplService;

@Controller
public class RestfulController {

	@Autowired
	private ParamImplService paramImplService;

	@Autowired
	private ISomeService someService;

//    @RequestMapping(value = "/", method = GET)
    @RequestMapping(value = "/api/text/", method = GET)
    @ResponseBody
    public String getTestMember() {

    	RequestContextHolder.getRequestAttributes().setAttribute("key", "test", RequestAttributes.SCOPE_SESSION);

    	Object aaa = RequestContextHolder.getRequestAttributes().getAttribute("key", RequestAttributes.SCOPE_SESSION);

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

	ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpSession session = req.getRequest().getSession(false);
    if (session == null) {
    	session = req.getRequest().getSession();
    }
    session.setAttribute("key1", "aaa");

    //	  paramImplService.globalMessage();
    return someService.getMessage("1");

}



}