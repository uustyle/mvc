package batch2;

public class ParamSetInfo2 implements IParamSetInfo{

	String membername;
	int itemStart;

	public ParamSetInfo2(String membername, int itemStart) {
		this.membername = membername;
		this.itemStart = itemStart;
	}

	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public int getItemStart() {
		return itemStart;
	}
	public void setItemStart(int itemStart) {
		this.itemStart = itemStart;
	}



}
