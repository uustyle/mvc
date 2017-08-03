package batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestCls7 {
//	Map<String, Map<String, ParamSaveInfo>>


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		TestCls7 obj = new TestCls7();
//		obj.init();

		obj.test1();

	}


	public void test1() {

//		Object a = new Object[1][2];
		double a[][] = {{1.0, 2.0},{1.0, 2.0}};

		double b[][] = (double [][])a;


		List<String> list = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();

		map.put("1", "1");
		String value = map.get("2");
		System.out.println(value);

list.set(2, "1");

System.out.println(list.size());

for(int i=0; i< list.size(); i++){
	System.out.println(list.get(i));
}



	}



}
