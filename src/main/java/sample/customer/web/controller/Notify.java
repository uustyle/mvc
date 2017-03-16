package sample.customer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import sample.customer.biz.service.SomeService;

public class Notify {

	@Autowired
	private SomeService someService;

	public String getMessage() {

		String ret = someService.message();

		return ret;
	}

}
