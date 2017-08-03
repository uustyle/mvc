package batch2;

public class ParamSaveInfo {

	String membername;
	String key;
	Object value;

	public ParamSaveInfo(String membername, String key, Object value) {
		this.membername = membername;
		this.key = key;
		this.value = value;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}




}
