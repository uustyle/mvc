package sample.customer.biz.service;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstraceParamTemplate  {

	@Autowired
	private SomeService	someService;

	public abstract String getParamType();

    public void message() {

		someService.message();
    	System.out.println(getParamType());
    }

}
