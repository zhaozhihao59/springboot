package com.revanow.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.revanow.base.cache.MyCache;
@Component
public class RedisCacheManagerUtil {
	@Resource
	private RedisCacheManagerUtil redisCacheManagerUtil;
	
	public List<String> getDatabasesList() throws Exception{
		String jsonArrStr =  redisCacheManagerUtil.recieveDataByRemote(Utils.databases_info, null);
		JSONObject jsonObj = JSON.parseObject(jsonArrStr);
		List<String> list = JSON.parseArray(jsonObj.getString("data"),String.class);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public  List<JSONObject> getTableList(String databases) throws Exception{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("db", databases);
	 	String jsonArrStr =  redisCacheManagerUtil.recieveDataByRemote(Utils.table_info, paramsMap);
	 	JSONObject jsonObj = JSON.parseObject(jsonArrStr);
	 	List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObj.get("data");
	 	if(list == null){
	 		list = new ArrayList<Map<String,Object>>();
	 	}
	 	List<JSONObject> resultList = new ArrayList<JSONObject>();
	 	for (Map<String, Object> item : list) {
			JSONObject temp = new JSONObject();
			temp.put("tableName", item.get("tableName"));
			temp.put("tableComment", item.get("tableComment"));
			temp.put("dataView", JSON.toJSONString(item.get("dataView")));
			resultList.add(temp);
		}
	 	return resultList;
	}
	
	@MyCache(overTime = 7200)
	public String recieveDataByRemote(String url,Map<String, String> param) throws Exception{
		 return HttpUtil.postData(url, param);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<JSONObject> getTableDetailList(List<String> tableList,String databases) throws Exception{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("db", databases);
		String jsonArrStr = redisCacheManagerUtil.recieveDataByRemote(Utils.table_info, paramsMap);
		JSONObject jsonObj = JSON.parseObject(jsonArrStr);
	 	List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObj.get("data");
	 	List<JSONObject> resultList = new ArrayList<JSONObject>();
	 	for (Map<String, Object> item : list) {
	 		String tableName = item.get("tableName").toString();
	 		if(tableList.contains(tableName)){
	 			JSONObject temp = new JSONObject();
				temp.put("tableName", tableName);
				temp.put("tableComment", item.get("tableComment"));
				temp.put("fields", item.get("fields"));
				resultList.add(temp);
	 		}
		}
	 	return resultList;
	 	
	} 
	
}
