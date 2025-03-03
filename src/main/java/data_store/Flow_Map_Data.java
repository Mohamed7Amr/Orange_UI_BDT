package data_store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Flow_Map_Data {

    /***************************************DECLARATIONS/INSTANTIATIONS***************************************/
    public static Map<String, LinkedList<Integer>> flow_Data = new HashMap<>();
    public static LinkedList<Integer> key_Values = new LinkedList<>();

    /**********************************************METHODS**************************************************/
    public static void add_Map_Key_Data(String key,int item_Value)
    {
        key_Values.add(item_Value);
        flow_Data.put(key,key_Values);
    }
    public static LinkedList<Integer> get_Map_Key_Data(String key)
    {
        return flow_Data.get(key);
    }
}
