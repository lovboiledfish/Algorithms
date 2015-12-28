package solutions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by PPlovboiledfish on 11/12/15.
 *
 * input example:
 *
 * {
 *   "uuid": "abc",
 *   "properties": {
 *     "sessionName": "Test session name",
 *     "waypoints": [
 *       {"uuid": "def", "properties": {"latitude": 3}}
 *     ]
 *   }
 * }
 *
 * output of which should be:
 *
 * [
 *
 *   {"uuid": "abc", "properties": {"sessionName": "Test session name", "
 *   waypoints": ["def"]}},
 *
 *   {"uuid": "def", "properties": {"latitude": 3}},
 *
 *   ...
 *
 * ]
 *
 */
public class FlattenJSONToAListOfMap {
    public JSONArray flatten(String jStr) {
        JSONArray res = new JSONArray();
        if (jStr == null || jStr.length() == 0)
            return res;

        JSONObject jObj = new JSONObject(jStr);
        _flattenHelper(jObj, res);
        return res;
    }

    private Object _flattenHelper(Object obj,  JSONArray res) {
        if (obj instanceof JSONObject) {
            JSONObject jObj = (JSONObject) obj;
            JSONObject map = new JSONObject();
            Iterator<?> it = jObj.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = jObj.get(key);
                map.put(key, _flattenHelper(value, res));
            }
            if (jObj.has("uuid")) {
                res.put(map);
                return jObj.get("uuid");
            }
            return map;
        } else if (obj instanceof JSONArray) {
            JSONArray jArr = (JSONArray) obj;
            JSONArray ret = new JSONArray();
            for (int i = 0; i < jArr.length(); ++i)
                ret.put(_flattenHelper(jArr.get(i), res));
            return ret;
        }
        return obj;
    }

    static public class Test {
        static private FlattenJSONToAListOfMap _solution = new FlattenJSONToAListOfMap();

        static public void test(String in) {
            System.out.println(_solution.flatten(in).toString());
        }

        static public void randomTest() {
            String in =
             "{\n"+
             "      \"uuid\": \"abc\",\n"+
             "      \"properties\": {\n"+
             "            \"sessionName\": \"Test session name\",\n"+
             "            \"waypoints\": [\n"+
             "              {\"uuid\": \"def\", \"properties\": {\"latitude\": 3}}\n"+
             "            ]\n"+
             "          }\n"+
             "    }";
            test(in);
        }
    }
}
