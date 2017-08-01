package batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import batch2.ParamSaveInfo;


public class TestCls5 {
//	Map<String, Map<String, ParamSaveInfo>>


	List<ParamSaveInfo> list = new ArrayList<ParamSaveInfo>();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		TestCls5 obj = new TestCls5();

	}

	public void init() {

		List<ParamSaveInfo> list = new ArrayList<ParamSaveInfo>();
		ParamSaveInfo info = new ParamSaveInfo("member1", "KEY1", 1);
		list.add(info);
		info = new ParamSaveInfo("member2", "KEY2_{0}", 2);
		list.add(info);
		info = new ParamSaveInfo("member2", "KEY2_{1}", 3);
		list.add(info);

		Map<String,Object> map = new HashMap<String,Object>();
		for(ParamSaveInfo paramSaveInfo: list) {

			map.put(paramSaveInfo.getKey() , paramSaveInfo.getValue());
		}

//item_noとキーワードからkeyを作成
		String keys[] = {"KEY1", "KEY2","KEY2_{0}"};
		for(int i = 0; i< keys.length; i++) {
			Object value = map.get(keys[i]);

			if ( value == null ) {
//valueがnullなら、構造体に設定しない
//save:SPMから取得したデータを変えないようにする
			} else {

			}

		}

	}



}
