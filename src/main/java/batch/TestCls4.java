package batch;


public class TestCls4 {

	public Object a = 1;

	public static void main(String[] args) throws Exception{

		System.out.println(1/3);
		System.out.println(1%3);

		System.out.println("-----");

		System.out.println(2/3);
		System.out.println(2%3);

		System.out.println("-----");

		System.out.println(3/3);
		System.out.println(3%3);

		System.out.println("-----");

		System.out.println(4/3);
		System.out.println(4%3);

		System.out.println("-----");

		System.out.println(5/3);
		System.out.println(5%3);

		System.out.println("-----");

		System.out.println(6/3);
		System.out.println(6%3);

		System.out.println("-----");

		TestCls4 obj = new TestCls4();

		Object[] b = {1,2};

		obj.sub();


	}

	void sub() {

		a = 1;
		RowClass rowobj = new RowClass();
		rowobj.param = a;
		System.out.println(a);
		sub1(rowobj);
		System.out.println(a);


	}

	void sub1(RowClass rowobj) {

		rowobj.param = "aa";

	}





}
