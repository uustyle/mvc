package batch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class TestCls {

	public static void main(String[] args) throws Exception{
		// TODO 自動生成されたメソッド・スタブ

		Object a = 1;
		System.out.println(NumberUtils.toInt(ObjectUtils.toString(a)));

		Object b = 100L;
		System.out.println(NumberUtils.toLong(ObjectUtils.toString(b)));

		Object c = new Double(100);
		System.out.println(NumberUtils.toDouble(ObjectUtils.toString(c)));

		Object d = (byte)1;
		System.out.println(NumberUtils.toByte(ObjectUtils.toString(d)));

		Map<String,StructDto> structMap = new HashMap<String,StructDto>();

//		StructDto sdto = getStruct("struct1");
//		StructDto sdto2 = getStructArray("struct1");

		StructDto sdto = getStruct("struct1");
		StructDto sdto2 = getStruct("struct2");

		FldDto fdto = new FldDto();
		fdto.setName("fld4");
		fdto.setType(4);
		fdto.setSize(4);
		fdto.setArrayflg(null);
		fdto.setValue(sdto2);
		sdto.getFlds().put(fdto.getName(), fdto);
		sdto.setSize(36);

		structMap.put("struct1", sdto2);

		Converter converter = new Converter();
		byte[] bytedt = converter.toByte(sdto);

		FileUtils.writeByteArrayToFile(new File("bytefile.out"), bytedt);

		FileOutputStream out=new FileOutputStream("output.txt");
		PrintWriter writer=new PrintWriter(out);
		writer.println('a');
	    for (int i=0;i<bytedt.length;++i) {
	    	writer.printf("%02X",bytedt[i]);
			writer.println("");
	    }
		writer.flush();
		writer.close();


		ByteBuffer buf = ByteBuffer.wrap(bytedt);

		converter.toObj(sdto, buf);



	}


	private static StructDto getStruct(String name) {

		LinkedHashMap<String, FldDto> fldMap= new LinkedHashMap<String, FldDto>();
		FldDto dto = new FldDto();
		dto.setName("fld1");
		dto.setType(1);
		dto.setSize(4);
		dto.setArrayflg("4");

		byte[] array;
        array = new byte[4];
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        byteBuffer.putInt(0, 1);
        dto.setValue(byteBuffer.array());

		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld2");
		dto.setType(2);
		dto.setSize(4);
//		dto.setArrayflg(null);
		dto.setValue(1);
		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld3");
		dto.setType(3);
		dto.setSize(10);
		dto.setLength(10);
//		dto.setArrayflg(1);
		dto.setValue("1234567890");

		fldMap.put(dto.getName(), dto);

		StructDto sdto = new StructDto();
		sdto.setName(name);
		sdto.setSize(18);
		sdto.setFlds(fldMap);

		return sdto;
	}


	private static StructDto getStructinit(String name) {

		LinkedHashMap<String, FldDto> fldMap= new LinkedHashMap<String, FldDto>();
		FldDto dto = new FldDto();
		dto.setName("fld1");
		dto.setType(1);
		dto.setSize(4);
		dto.setArrayflg("4");

//		byte[] array;
//        array = new byte[4];
//        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
//        byteBuffer.putInt(0, 1);
//        dto.setValue(byteBuffer.array());

		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld2");
		dto.setType(2);
		dto.setSize(4);
//		dto.setArrayflg(null);
//		dto.setValue(1);
		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld3");
		dto.setType(3);
		dto.setSize(10);
		dto.setLength(10);
//		dto.setArrayflg(1);
//		dto.setValue("1234567890");

		fldMap.put(dto.getName(), dto);

		StructDto sdto = new StructDto();
		sdto.setName(name);
		sdto.setSize(18);
		sdto.setFlds(fldMap);

		return sdto;
	}



	private static StructDto getStructArray(String name) {

		LinkedHashMap<String, FldDto> fldMap= new LinkedHashMap<String, FldDto>();
		FldDto dto = new FldDto();
		dto.setName("fld1");
		dto.setType(1);
		dto.setSize(4);
		dto.setArrayflg("4");

		byte[] array;
        array = new byte[4];
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        byteBuffer.putInt(0, 1);
        dto.setValue(byteBuffer.array());

		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld2");
		dto.setType(2);
		dto.setSize(4);
		dto.setArrayflg("2");
		int tmp[] = {1,2};
		dto.setValue(tmp);
		fldMap.put(dto.getName(), dto);

		dto = new FldDto();
		dto.setName("fld3");
		dto.setType(3);
		dto.setSize(10);
		dto.setLength(10);
//		dto.setArrayflg(1);
		dto.setValue("1234567890");

		fldMap.put(dto.getName(), dto);

		StructDto sdto = new StructDto();
		sdto.setName(name);
		sdto.setSize(22);
		sdto.setFlds(fldMap);

		return sdto;
	}





}
