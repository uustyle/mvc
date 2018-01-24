package sample.customer.biz.domain;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

   /** Following is the definition for a PointCut to select
    *  all the methods available. So advice will be called
    *  for all the methods.
    */
   @Pointcut("execution(* sample.customer.biz.dto.Student.getAge(..))")
   private void selectGetAge(){}

   /**
    * This is the method which I would like to execute
    * before a selected method execution.
    */
   @Before("selectGetAge()")
   public void beforeAdvice(){
      System.out.println("Going to setup student profile.");
   }
}