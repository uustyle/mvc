package batch;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Converter {

	public byte[] toByte(StructDto sDto){

		Map<String, FldDto> flds = sDto.getFlds();
		ByteBuffer buf = ByteBuffer.allocate(sDto.getSize());

		// 拡張for文：キーをループ
		for (String key : flds.keySet()) {
		    // keyを処理
			FldDto fldDto = flds.get(key);

			if (4 == fldDto.getType()) {

				byte[] buftmp = this.toByte((StructDto)fldDto.getValue());
				buf.put(buftmp);


			} else {

				if (StringUtils.isEmpty(fldDto.getArrayflg())) {

					this.toByte(fldDto,fldDto.getValue(),buf);

				} else {

					for(int i = 0; i< Integer.parseInt( fldDto.getArrayflg()); i++) {

						this.toByte(fldDto, Array.get(fldDto.getValue(), i) , buf);
					}
				}
			}
		}

		byte[] byteArray = new byte[buf.remaining()];
	    buf.get(byteArray);

	    byte [] bb=buf.array();
	    for (int i=0;i<bb.length;++i) System.out.printf("%02X ",bb[i]);

//	    System.out.println(new String(byteArray));
//	    System.out.println(buf.array());

	    return buf.array();
	}


	public void toByte(FldDto fldDto, Object value, ByteBuffer buf){

		if (1 == fldDto.getType()) {
			//byte
			buf.put(NumberUtils.toByte(ObjectUtils.toString(value)));

		} else if (2 == fldDto.getType()) {
			//int
			buf.putInt(NumberUtils.toInt(ObjectUtils.toString(value)));

		} else if (3 == fldDto.getType()) {
			//String
			buf.put(ObjectUtils.toString(value).getBytes());
		}
	}


	public void toObj(StructDto sDto, ByteBuffer buf){

		Map<String, FldDto> flds = sDto.getFlds();
//		ByteBuffer buf = ByteBuffer.wrap(bytedt);

		// 拡張for文：キーをループ
		for (String key : flds.keySet()) {
		    // keyを処理
			FldDto fldDto = flds.get(key);

			if (4 == fldDto.getType()) {

				this.toObj((StructDto)fldDto.getValue(), buf);

			} else {

				if (StringUtils.isEmpty(fldDto.getArrayflg())) {

					Object value = this.toObj(fldDto,buf);
					fldDto.setValue(value);
				} else {

					fldDto.setValue(new Object[Integer.parseInt( fldDto.getArrayflg())]);
					for(int i = 0; i< Integer.parseInt( fldDto.getArrayflg()); i++) {

						Object value = this.toObj(fldDto , buf);
						Array.set(fldDto.getValue(), i, value);
					}
				}
			}
		}

		dump(sDto);


	}


	public Object toObj(FldDto fldDto, ByteBuffer buf){
		Object value = null;
		if (1 == fldDto.getType()) {
			//byte
			value = buf.get();
		} else if (2 == fldDto.getType()) {
			//int
			value = buf.getInt();
		} else if (3 == fldDto.getType()) {
			//String
			byte[] byteArray = new byte[fldDto.getLength()];
		    buf.get(byteArray);
			value = new String(byteArray);
		}
		return value;
	}



	public void dump(StructDto sDto){

		Map<String, FldDto> flds = sDto.getFlds();

		// 拡張for文：キーをループ
		for (String key : flds.keySet()) {
		    // keyを処理
			FldDto fldDto = flds.get(key);

			if (4 == fldDto.getType()) {

				dump((StructDto)fldDto.getValue());

			} else {


				System.out.println(fldDto.getName() + "=" + ArrayUtils.toString(fldDto.getValue()));
//				System.out.println(fldDto.getName() + "=" + Arrays.asList(ArrayUtils.toObject(fldDto.getValue())));

//				if (StringUtils.isEmpty(fldDto.getArrayflg())) {
//
//					this.dump(fldDto,fldDto.getValue());
//
//				} else {
//
//					for(int i = 0; i< Integer.parseInt( fldDto.getArrayflg()); i++) {
//
//						this.dump(fldDto, Array.get(fldDto.getValue(), i));
//					}
//				}
			}
		}

	}


//	public void dump(FldDto fldDto, Object value){
//
//		if (1 == fldDto.getType()) {
//			//byte
//			buf.put(NumberUtils.toByte(ObjectUtils.toString(value)));
//
//		} else if (2 == fldDto.getType()) {
//			//int
//			buf.putInt(NumberUtils.toInt(ObjectUtils.toString(value)));
//
//		} else if (3 == fldDto.getType()) {
//			//String
//			buf.put(ObjectUtils.toString(value).getBytes());
//		}
//	}




}
