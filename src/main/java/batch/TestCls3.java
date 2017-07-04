package batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

public class TestCls3 {

	public static void main(String[] args) throws Exception{

		List<LinkedHashMap<String, FldDto>> fldList = new ArrayList<LinkedHashMap<String, FldDto>>();
		LinkedHashMap<String, FldDto> fldMap= new LinkedHashMap<String, FldDto>();
		FldDto dto = new FldDto();
		dto.setName("fld1");
		dto.setType(1);
		dto.setSize(4);
		dto.setArrayflg("4");

		Object a[] = new Object[2];
		a[0] = "1";
		a[1] = "2";
		dto.setValue(a);


		FldDto dto2 = dto.clone();
//		Object b[] = new Object[2];
//		b[0] = "11";
//		b[1] = "22";
//		dto2.setValue(b);

		Object bb[] = (Object[])dto2.getValue();
		bb[0] = "11";
		bb[1] = "22";


		System.out.println(ArrayUtils.toString(dto.getValue()));
		System.out.println(ArrayUtils.toString(dto2.getValue()));





		fldList.add(fldMap);

		FileUtils.writeLines(new File("d:/test1.txt"), fldList);



//		Object b = 100L;
//		System.out.println(NumberUtils.toLong(ObjectUtils.toString(b)));
//
//		Object c = new Double(100);
//		System.out.println(NumberUtils.toDouble(ObjectUtils.toString(c)));
//
//		Object d = (byte)1;



	}


}
