package sub;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import batch2.ElementInfo;
import batch2.ParamSetInfo;
import batch2.StructureInfo;

public class ParamRowInfoShelf<T> implements Aggregate {

	private StructureInfo structureInfo;
	private List<ParamSetInfo> paramSetInfoList;
	private List<T> paramRowInfoList;

	public T create(Class<T> clazz) throws Exception {
        return clazz.newInstance();
    }

	public ParamRowInfoShelf(StructureInfo structureInfo,
			List<ParamSetInfo> paramSetInfoList) {
		this.structureInfo = structureInfo;
		this.paramSetInfoList = paramSetInfoList;
		this.paramRowInfoList = new ArrayList<T>();

		for(ParamSetInfo paramSetInfo: this.paramSetInfoList) {

			ElementInfo elementInfo =
					this.structureInfo.getElementInfoMap().get(paramSetInfo.getMembername());

			if (0 == paramSetInfo.getItemStart()) {

				ParamRowInfo paramRowInfo = new ParamRowInfo();

				paramRowInfo.setMembername(paramSetInfo.getMembername());
				paramRowInfo.setElement(elementInfo);
				paramRowInfo.setArray1index(0);
				paramRowInfo.setArray2index(0);
				paramRowInfo.setItemno(0);
				paramRowInfo.setValue(elementInfo.getValue());

				paramRowInfoList.add((T)paramRowInfo);

			} else {

				for(int itemNo = paramSetInfo.getItemStart(); itemNo <= paramSetInfo.getItemEnd(); itemNo++){

					ParamRowInfo paramRowInfo = new ParamRowInfo();

					paramRowInfo.setMembername(paramSetInfo.getMembername());
					paramRowInfo.setElement(elementInfo);
					paramRowInfo.setItemno(itemNo);

					if (0 != elementInfo.getDimension1() && 0 == elementInfo.getDimension2()){

						int array1Index = itemNo - 1;

						paramRowInfo.setArray1index(array1Index);
						paramRowInfo.setArray2index(0);
						Object value = Array.get(elementInfo.getValue(), array1Index);
						paramRowInfo.setValue(value);

						paramRowInfoList.add((T)paramRowInfo);

					} else if (0 != elementInfo.getDimension1() && 0 != elementInfo.getDimension2()){

						int array1Index = 0;
						int array2Index = 0;

//						if (itemNo <= elementInfo.getDimension2()) {
//							array2Index = itemNo - 1;
//						} else {
//
//							6 / 3 == 0だが [1][2]
//							array1Index = itemNo / elementInfo.getDimension2();
//							array2Index = itemNo % elementInfo.getDimension2() - 1;
//						}

						array1Index = itemNo / elementInfo.getDimension2();
						array2Index = itemNo % elementInfo.getDimension2() - 1;

						if (array2Index == -1) {

							array1Index = array1Index - 1;
							array2Index = elementInfo.getDimension2() - 1;
						}

						paramRowInfo.setArray1index(array1Index);
						paramRowInfo.setArray2index(array2Index);

						Object value = Array.get(elementInfo.getValue(), array1Index);
						value = Array.get(value, array2Index);
						paramRowInfo.setValue(value);

						paramRowInfoList.add((T)paramRowInfo);

					} else {
						throw new RuntimeException("");
					}

				}
			}
		}
	}


	public ParamRowInfoShelf(StructureInfo structureInfo,
			List<ParamSetInfo> paramSetInfoList,
			int tmp) {
		this(structureInfo, paramSetInfoList);

		List<T> paramRowInfoList2 = new ArrayList<T>();

//		this.structureInfo = structureInfo;
//		this.paramSetInfoList = paramSetInfoList;
//

		for(T paramRowInfo: this.paramRowInfoList) {

			ParamRowInfo2 paramRowInfo2 = new ParamRowInfo2();
			BeanUtils.copyProperties(paramRowInfo, paramRowInfo2);
			paramRowInfo2.setDmy(1);
			paramRowInfoList2.add((T)paramRowInfo2);
		}
		this.paramRowInfoList = paramRowInfoList2;

	}



	public T getParamRowInfoAt(int index) {
		return paramRowInfoList.get(index);
	}

	public int getLength() {
		return paramRowInfoList.size();
	}


	public Iterator iterator(){
		return new ParamRowInfoShelfIterator<T>(this);

	}


}
