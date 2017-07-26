package batch2;

public class ParamSetInfo implements IParamSetInfo {

	String membername;
	int itemStart;
	int itemEnd;
	int start;

	public ParamSetInfo(String membername, int itemStart, int itemEnd, int start) {
		this.membername = membername;
		this.itemStart = itemStart;
		this.itemEnd = itemEnd;
		this.start = start;
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
	public int getItemEnd() {
		return itemEnd;
	}
	public void setItemEnd(int itemEnd) {
		this.itemEnd = itemEnd;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}


}
