package sample.customer.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamImplService extends AbstraceParamTemplate implements IParam{

	@Autowired
	private SomeService	someService;


	public String getParamType() {
		someService.getMessage("aa");
		return "global";
	}

    public String globalMessage() {
    	message();
        return getParamType();
    }

}
