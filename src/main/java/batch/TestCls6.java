package batch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import batch2.IParamSetInfo;
import batch2.ParamSetInfo;
import batch2.ParamSetInfo2;


public class TestCls6 {
//	Map<String, Map<String, ParamSaveInfo>>

	List<ParamSetInfo> list = new ArrayList<ParamSetInfo>();
	List<ParamSetInfo2> list2 = new ArrayList<ParamSetInfo2>();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		TestCls6 obj = new TestCls6();
		obj.init();
		obj.disp(obj.list);
		obj.disp(obj.list2);

	}

	public void init() {

		ParamSetInfo info = new ParamSetInfo("member1", 1, 1, 1);
		list.add(info);

		info = new ParamSetInfo("member1", 2, 2, 2);
		list.add(info);

		info = new ParamSetInfo("member2", 2, 2, 2);
		list.add(info);

		info = new ParamSetInfo("member2", 2, 2, 2);
		list.add(info);

		info = new ParamSetInfo("member3", 3, 3, 3);
		list.add(info);

		info = new ParamSetInfo("member3", 3, 3, 3);
		list.add(info);


		ParamSetInfo2 info2 = new ParamSetInfo2("member11", 11);
		list2.add(info2);

		info2 = new ParamSetInfo2("member22", 22);
		list2.add(info2);

		info2 = new ParamSetInfo2("member33", 33);
		list2.add(info2);

	}

	public <T extends IParamSetInfo>void disp(List<T> list) {

		Map<String, List<T>> mapList = new LinkedHashMap <String, List<T>>();

		for(T info: list){

//			System.out.println(info.getMembername());

			List<T> listTmp;
			if (mapList.containsKey(info.getMembername())) {
				listTmp = mapList.get(info.getMembername());
			} else {
				listTmp = new ArrayList<T>();
				mapList.put(info.getMembername(), listTmp);
			}
			listTmp.add(info);
		}

		for (String key : mapList.keySet()) {

			System.out.println("key=" + key);

			List<T> listTmp = mapList.get(key);

			for(T info: listTmp) {
				System.out.println("itemStart=" + info.getItemStart());
			}
		}
	}

}
