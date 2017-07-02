package batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class TestCls2 {

	public static void main(String[] args) throws Exception{

		List<LinkedHashMap<String, FldDto>> fldList = new ArrayList<LinkedHashMap<String, FldDto>>();
		LinkedHashMap<String, FldDto> fldMap= new LinkedHashMap<String, FldDto>();
		FldDto dto = new FldDto();
		dto.setName("fld1");
		dto.setType(1);
		dto.setSize(4);
		dto.setArrayflg("4");

		fldMap.put("struct1", dto);
		fldList.add(fldMap);

		FileUtils.writeLines(new File("d:/test1.txt"), fldList);


		Object a = null;
		System.out.println(Arrays.asList(a));

//		Object b = 100L;
//		System.out.println(NumberUtils.toLong(ObjectUtils.toString(b)));
//
//		Object c = new Double(100);
//		System.out.println(NumberUtils.toDouble(ObjectUtils.toString(c)));
//
//		Object d = (byte)1;



	}


}
