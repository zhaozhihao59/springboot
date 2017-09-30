package com.revanow.base.cache;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
@Component
public class RedisTamplateService {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	public Object getValueByKey(String keyStr, int overTime){
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				byte[] key = keyStr.getBytes();
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String s = new String(value);
                    connection.setEx(key, overTime, value);
                    return  JSON.parse(s);
                }
				return null;
			}
		});
	}
	
	public void setValueByKey(String key,Object target, int overTime){
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				String keyStr = key;
                connection.setEx(keyStr.getBytes(), overTime, JSON.toJSONString(target).getBytes());
                return true;
			}
		});
	}
}
