package sub;

import batch2.ElementInfo;


public class ParamRowInfo {

	private String membername;
	private ElementInfo element;
	private int array1index;
	private int array2index;
	private int itemno;
	private Object value;

	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public ElementInfo getElement() {
		return element;
	}
	public void setElement(ElementInfo element) {
		this.element = element;
	}
	public int getArray1index() {
		return array1index;
	}
	public void setArray1index(int array1index) {
		this.array1index = array1index;
	}
	public int getArray2index() {
		return array2index;
	}
	public void setArray2index(int array2index) {
		this.array2index = array2index;
	}
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ParamRowInfo [membername=" + membername + ", element="
				+ element + ", array1index=" + array1index + ", array2index="
				+ array2index + ", itemno=" + itemno + ", value=" + value + "]";
	}


}
