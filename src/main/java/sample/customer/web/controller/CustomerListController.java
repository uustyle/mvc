package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sample.customer.biz.domain.Customer;
import sample.customer.biz.domain.Logging;
import sample.customer.biz.dto.Student;
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

	private static final Marker MARKER1 = MarkerFactory.getMarker("ExceptionInterceptor1");

	private static final Marker MARKER2 = MarkerFactory.getMarker("ExceptionInterceptor2");

    @RequestMapping(value = "/", method = GET)
    public String home(HttpSession session, HttpServletRequest request) {

    	logger.info("greeting def");

    	logger.info(MARKER1, "greeting MARKER1");

    	logger.info(MARKER2, "greeting MARKER2");


        Student student = new Student();

        //Create the Proxy Factory
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(student);

        //Add Aspect class to the factory
        proxyFactory.addAspect(Logging.class);

        //Get the proxy object
        Student proxyStudent = proxyFactory.getProxy();

        //Invoke the proxied method.
        proxyStudent.getAge();


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


    @RequestMapping("/uploadForm")
    public String uploadForm() {
        return "customer/uploadForm";
    }

    @RequestMapping(value = "/uploadRecv", method = RequestMethod.POST)
    public String uploadRecv(@RequestParam String test,
            @RequestParam MultipartFile file, Model model) throws IOException {
        model.addAttribute("test", test);
        Path path = Paths.get(System.getProperty("java.io.tmpdir"),
                file.getOriginalFilename());
        file.transferTo(path.toFile());
        model.addAttribute("fileName", path);

        return "customer/uploadRecv";
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
