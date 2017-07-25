package sample.customer.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ParamService extends ParamTemplateService{

	public boolean isSkip(String dt) {

		if ("a".equals(dt)) {
			return true;
		} else {
			return false;
		}

	}

	public int getParamType() {
		return 1;
	}

	public void edit() {

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		this.returnData(list);
	}

}
