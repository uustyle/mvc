package sample.customer.biz.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParamAspect {

	// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルとは変えています。
	// findByProductName ---> findProduct

//	@Before("execution(public * *(..))")
//	@Before("execution(public * *(..))")
//	@Before("execution(*  getTestMember3(..))")
//	@Before("execution(*  findAll(..))")
	@Around("execution(*  findAll(..))")
//	 @Before("execution(* sample.customer.biz.service.MockCustomerService(..))")
	public void before(JoinPoint jp) {
		// メソッド開始時にWeavingするAdvice
		System.out.println("Hello Before! *** メソッドが呼ばれる前に出てくるよ!");
		Signature sig = jp.getSignature();
		System.out.println("-----> メソッド名を取得するよ：" + sig.getName());
//		Object[] o = jp.getArgs();
//		System.out.println("-----> 仮引数の値を取得するよ：" + o[0]);
	}
//
//	@After("execution(* findProduct(String))")
//	public void after() {
//		// メソッド終了後にWeavingするAdvice
//		System.out.println("Hello After! *** メソッドが呼ばれた後に出てくるよ!");
//	}
//
//	@AfterReturning(value = "execution(* findProduct(String))", returning = "product")
//	public void afterReturning(JoinPoint jp, Product product) {
//		// メソッド呼出が例外の送出なしに終了した際に呼ばれるAdvice
//		System.out.println("Hello AfterReturning! *** メソッドを呼んだ後に出てくるよ");
//		Signature sig = jp.getSignature();
//		System.out.println("-----> メソッド名を取得するよ：" + sig.getName());
//		Object[] o = jp.getArgs();
//		System.out.println("-----> 仮引数の値を取得するよ：" + o[0]);
//	}


//	@Around("execution(* message(..))")
	@Around("execution(public String sample.customer.biz.service.SomeService.message(..))")
//	@Around("execution(* sample.customer.biz.service.ParamImplService.globalMessage(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// メソッド呼出の前後にWeavingするAdvice
		System.out.println("Hello Around! before *** メソッドを呼ぶ前に出てくるよ!");
		Signature sig = pjp.getSignature();
		System.out.println("-----> aop:around メソッド名を取得するよ：" + sig.getName());

		try {

			Object p = pjp.proceed();
			System.out.println("Hello Around! after *** メソッドを呼んだ後に出てくるよ!");
			return p;

		} catch(Exception e) {
			System.out.println("Hello Around! after *** メソッドを呼んだ後に出てくるよ!");
			e.printStackTrace();
			throw e;
		}
	}



//	@AfterThrowing(value = "execution(* findByProductName(String))", throwing = "ex")
//	public void afterThrowing(Throwable ex) {
//		// メソッド呼出が例外を送出した際に呼ばれるAdvice
//		System.out.println("Hello Throwing! *** 例外になったら出てくるよ");
//		System.out.println("exception value = " + ex.toString());
//	}
}