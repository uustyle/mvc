package analize;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Struct1_1 struct1_1 = new Struct1_1();
		struct1_1.setFld1(1);
		struct1_1.setFld2(11);

		Struct1_2 struct1_2 = new Struct1_2();
		struct1_2.setFld1(2);
		struct1_2.setFld2(12);

		Struct1_3 struct1_3 = new Struct1_3();
		struct1_3.setFld1(3);
		struct1_3.setFld2(13);

		Struct4 struct4 = new Struct4();
		struct4.setFld1(4);
		struct4.setFld2(14);

		Struct5 struct5 = new Struct5();
		struct5.setFld1(5);
		struct5.setFld2(15);

		Struct1 struct1 = new Struct1();
		struct1.setStruct1_1(struct1_1);
		struct1.setStruct1_2(struct1_2);
		struct1.setStruct1_3(struct1_3);

		DataOffset dataOffset = new DataOffset();
		dataOffset.setDataOffset(new int[]{1,5});





	}

}
