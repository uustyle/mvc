package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.customer.biz.domain.Customer;
import sample.customer.biz.service.CustomerService;
import sample.customer.biz.service.DataNotFoundException;
import sample.customer.biz.thread.PrintTask;

@Controller
public class CustomerListController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

	private static Logger logger = LoggerFactory.getLogger(CustomerListController.class);

    @RequestMapping(value = "/", method = GET)
    public String home(HttpSession session, HttpServletRequest request) {

    	logger.info("greeting");
//    	if (true)
//    	throw new RuntimeException();

    	session.setAttribute("test", "test1");
    	return "forward:/customer";
    }

    @RequestMapping(value = "/customer", method = GET)
    public String showAllCustomers(Model model) {

    	Notify notify = new Notify();
    	beanFactory.autowireBean(notify);
    	String ret = notify.getMessage();
    	System.out.println(ret);

    	List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }


    @RequestMapping(value = "/threadTest", method = GET)
    public String threadTest(Model model) {

    	taskExecutor.execute(new PrintTask("Thread 1"));
        taskExecutor.execute(new PrintTask("Thread 2"));
        taskExecutor.execute(new PrintTask("Thread 3"));
        taskExecutor.execute(new PrintTask("Thread 4"));
        taskExecutor.execute(new PrintTask("Thread 5"));

    	Notify notify = new Notify();
    	beanFactory.autowireBean(notify);
    	String ret = notify.getMessage();
    	System.out.println(ret);

    	List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }



    @RequestMapping(value = "/customer/{customerId}", method = GET)
    public String showCustomerDetail(@PathVariable int customerId, Model model)
                                        throws DataNotFoundException{
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customer/detail";
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String handleException() {
        return "customer/notfound";
    }

//    @ExceptionHandler
//    public String handleException(Exception e) {
//        return "error/system";
//    }
}
