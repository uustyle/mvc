package sub;

public class ParamRowInfoShelfIterator implements Iterator{

	private ParamRowInfoShelf paramRowInfoShelf;
	private int index;

	public ParamRowInfoShelfIterator(ParamRowInfoShelf paramRowInfoShelf) {
		this.paramRowInfoShelf = paramRowInfoShelf;
		this.index = 0;
	}


	public boolean hasNext(){
		if (index < this.paramRowInfoShelf.getLength()) {
			return true;
		} else {
			return false;
		}
	}


	public Object next(){
		ParamRowInfo paramRowInfo = this.paramRowInfoShelf.getParamRowInfoAt(this.index);
		this.index ++;
		return paramRowInfo;
	}

}
