package sample.customer.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstraceParamTemplate  {

	@Autowired
	private SomeService	someService;

	public abstract String getParamType();

    public void message() {

		someService.getMessage("a");
    	System.out.println(getParamType());
    }

}
