package batch;

import java.util.Map;

public class StructDto {

	private int size;

	private String name;

	private Map<String, FldDto> flds;


	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, FldDto> getFlds() {
		return flds;
	}

	public void setFlds(Map<String, FldDto> flds) {
		this.flds = flds;
	}








}
