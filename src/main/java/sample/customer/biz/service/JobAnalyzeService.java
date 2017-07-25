package sample.customer.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobAnalyzeService extends ParamTemplateService{

    @Autowired
    private ParamService paramService;

	public boolean isSkip(String dt) {
		return this.paramService.isSkip(dt);
	}

	public int getParamType() {
		return this.paramService.getParamType();
	}

	public void edit2() {

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		this.returnData(list);
	}

}
