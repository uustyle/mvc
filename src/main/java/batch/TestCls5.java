package batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import batch2.ParamSaveInfo;


public class TestCls5 {
//	Map<String, Map<String, ParamSaveInfo>>


	List<ParamSaveInfo> list = new ArrayList<ParamSaveInfo>();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		TestCls5 obj = new TestCls5();

	}

	public void init() {

		//member 単位
		Map<String, Map<String, List<ParamSaveInfo>>> paramSaveInfo = new HashMap<String, Map<String, List<ParamSaveInfo>>>();

		//member 単位
		Map<String, List<ParamSaveInfo>> paramSaveInfoMapMember = new HashMap<String, List<ParamSaveInfo>>();

		List<ParamSaveInfo> list = new ArrayList<ParamSaveInfo>();
		ParamSaveInfo info = new ParamSaveInfo("member1", 1);
		list.add(info);
		info = new ParamSaveInfo("member2", 2);
		list.add(info);
		info = new ParamSaveInfo("member2", 3);
		list.add(info);
		paramSaveInfoGroup.put("default", list);


		list = new ArrayList<ParamSaveInfo>();
		info = new ParamSaveInfo("struct1.member11", 1);
		list.add(info);
		info = new ParamSaveInfo("struct1.member22", 2);
		list.add(info);
		info = new ParamSaveInfo("struct1.member22", 3);
		list.add(info);
		paramSaveInfoGroup.put("struct1", list);




	}

	public void init() {

		ParamSaveInfo info = new ParamSaveInfo("member1", 1);
		this.list.add(info);
		info = new ParamSaveInfo("member2", 2);
		this.list.add(info);
		info = new ParamSaveInfo("member2", 3);
		this.list.add(info);

		info = new ParamSaveInfo("struct1.member11", 1);
		this.list.add(info);
		info = new ParamSaveInfo("struct1.member22", 2);
		this.list.add(info);
		info = new ParamSaveInfo("struct1.member22", 3);
		this.list.add(info);

	}





}
