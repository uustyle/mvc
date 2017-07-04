package batch;

public class FldDto implements Cloneable{

	private String name;

	private int type;

	private String arrayflg;

	private int size;

	private int length;

	private Object value;


	@Override
    public FldDto clone(){

		FldDto dto = new FldDto();
        try {
        	dto = (FldDto)super.clone();
        	dto.setValue(new Object[2]);
//           comB.nic = this.nic.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getArrayflg() {
		return arrayflg;
	}

	public void setArrayflg(String arrayflg) {
		this.arrayflg = arrayflg;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}



	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}





}
