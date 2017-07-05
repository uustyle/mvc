package batch;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

public class TestCls3 {

	public static void main(String[] args) throws Exception{

		//バッファーインスタンスを生成する
		ByteBuffer bb = ByteBuffer.allocate(10);
		bb.put((byte)0x01);
		bb.put((byte)0x02);
		bb.put((byte)0x03);


		bb.put((byte)0x04);
		bb.put((byte)0x05);
		bb.put((byte)0x06);
		bb.put((byte)0x07);
		bb.put((byte)0x08);
		bb.put((byte)0x09);
		bb.put((byte)0x0a);

		byte[] bytedt = bb.array();
	    for (int i=0;i<bytedt.length;++i) {
	    	System.out.println("16進数表現2桁:"+String.format("%02x", bytedt[i]));
	    }

		byte[] bytedt2 = new byte[2];
		bb.position(0);
		bb.get(bytedt2,0,2);
	    for (int i=0;i<bytedt2.length;++i) {
	    	System.out.println("16進数表現2桁:"+String.format("%02x", bytedt2[i]));
	    }



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

//		Object bb[] = (Object[])dto2.getValue();
//		bb[0] = "11";
//		bb[1] = "22";


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
