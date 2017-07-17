package batch2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sub.Iterator;
import sub.ParamRowInfo;
import sub.ParamRowInfoShelf;

public class TestMain {

	Map<String, List<?>>saveInfoMap =new LinkedHashMap<String, List<?>>();
	StructureInfo structureInfo;
	List<ParamSetInfo> paramSetInfoList;

	public static void main(String[] args) throws Exception{

		TestMain obj = new TestMain();

		obj.init();

		obj.iteratorTest();


	}


	void iteratorTest() {

		ParamRowInfoShelf paramRowInfoShelf = new ParamRowInfoShelf(this.structureInfo, this.paramSetInfoList);
		Iterator it = paramRowInfoShelf.iterator();
		while(it.hasNext()) {

			ParamRowInfo paramRowInfo = (ParamRowInfo)it.next();
			System.out.println(paramRowInfo.toString());

		}


	}

	void init() {

		ArrayList<Object> values = new ArrayList<Object>();
		values.add(1);
		values.add(2);
		this.saveInfoMap.put("data_y", values);

		values = new ArrayList<Object>();
		values.add(1);
		values.add(2);
		values.add(11);
		values.add(22);
		values.add(111);
		values.add(222);
		this.saveInfoMap.put("data_x", values);

		values = new ArrayList<Object>();
		values.add(1);
		this.saveInfoMap.put("data_a", values);


		this.paramSetInfoList = new ArrayList<ParamSetInfo>();

		ParamSetInfo paramSetInfo = new ParamSetInfo();
		paramSetInfo.membername = "data_y";
		paramSetInfo.itemStart = 1;
		paramSetInfo.itemEnd = 2;
		paramSetInfo.start = 0;
		this.paramSetInfoList.add(paramSetInfo);

		paramSetInfo = new ParamSetInfo();
		paramSetInfo.membername = "data_x";
		paramSetInfo.itemStart = 1;
		paramSetInfo.itemEnd = 6;
		paramSetInfo.start = 0;
		this.paramSetInfoList.add(paramSetInfo);

		paramSetInfo = new ParamSetInfo();
		paramSetInfo.membername = "data_a";
		paramSetInfo.itemStart = 0;
		paramSetInfo.itemEnd = 0;
		paramSetInfo.start = 0;
		this.paramSetInfoList.add(paramSetInfo);


		this.structureInfo = new StructureInfo();

		ElementInfo elementInfo = new ElementInfo();
		elementInfo.setMembername("data_y");
		elementInfo.setDimension1(2);
		elementInfo.setDimension2(0);
		elementInfo.setValue(new Object[]{1,2});

		this.structureInfo.getElementInfoMap().put("data_y", elementInfo);

		elementInfo = new ElementInfo();
		elementInfo.setMembername("data_x");
		elementInfo.setDimension1(2);
		elementInfo.setDimension2(3);
		elementInfo.setValue(new Object[][]{{1,2,3},{11,22,33}});

		this.structureInfo.getElementInfoMap().put("data_x", elementInfo);

		elementInfo = new ElementInfo();
		elementInfo.setMembername("data_a");
		elementInfo.setDimension1(0);
		elementInfo.setDimension2(0);
		Object tmp = 1;
		elementInfo.setValue(tmp);

		this.structureInfo.getElementInfoMap().put("data_a", elementInfo);


	}





}
