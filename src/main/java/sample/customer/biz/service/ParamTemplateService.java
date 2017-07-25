package sample.customer.biz.service;

import java.util.List;

public abstract class ParamTemplateService {

	public abstract boolean isSkip(String dt);

	public abstract int getParamType();

	public final void returnData(List<String> list) {

		int paramType = getParamType();
		System.out.println(paramType);

		for(String dt: list){

			if (!isSkip(dt)) {
				System.out.println(dt);
			}
		}
	}


}
