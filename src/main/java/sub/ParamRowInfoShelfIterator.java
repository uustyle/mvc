package sub;

public class ParamRowInfoShelfIterator<T> implements Iterator{

	private ParamRowInfoShelf<T> paramRowInfoShelf;
	private int index;

	public ParamRowInfoShelfIterator(ParamRowInfoShelf<T> paramRowInfoShelf) {
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
		T paramRowInfo = this.paramRowInfoShelf.getParamRowInfoAt(this.index);
		this.index ++;
		return paramRowInfo;
	}

}
