package com.revanow.base.cache;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


@Aspect
@Component
public class MyCacheAop {
	@Resource
	private RedisTamplateService redisTamplateService;
	
	@Pointcut(value = "@annotation(cache)")
	private void getPointcut(MyCache cache){
		
	}
	
	@Around("getPointcut(cache)")
	public Object preProcessQueryPattern(ProceedingJoinPoint point,MyCache cache) throws Throwable{
        String targetName = point.getTarget().getClass().getName();
        String simpleName = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object[] arguments = point.getArgs();
        //重新加载 要更新的缓存方法名
//        if(Cache.reLoad()){
//            methodName = Cache.method();
//        }
         
        String key = null;
//        没传key
        if (cache.key().length() > 0) {
            key = "'"+simpleName+"."+methodName + ".'+" +  cache.key();
        }else{
            key = simpleName+"."+methodName + "." + JSON.toJSONString(arguments);
        }
         
        String[] paramNames = ParamNameMap.get(key);
        if (paramNames==null){
//          反射得到形参名称
            paramNames = ReflectParamNames.getNames(targetName, methodName);
            ParamNameMap.put(key, paramNames);
        }
         
        if(cache.key().length() > 0){
//          spring EL 表达式
        	int startFix = cache.key().indexOf("#");
        	if(startFix >= 0){
        		key = SpelParser.getKey(key, cache.condition(), paramNames, arguments);
        	}
        }
         
        if(key.length()>200){
//          logger.warn("+++cache key length over max 200!");
        }
        int overTime = cache.overTime();
        Object object = redisTamplateService.getValueByKey(key,overTime);
//      重新加载时不走缓存
//        if(!Cache.reLoad()){
            
//        }
         
         
        if (object!=null){
            return object;
        }
         Object target = point.proceed();
         if (target != null){
        	 redisTamplateService.setValueByKey(key, target,overTime);
//           memcache.set(key, target, Cache.second());
//           logger.info("+++ key:"+key+" set cache 耗时： " + (endTime - startTime)  + "毫秒");
         }
            //拦截的放参数类型
//       Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
        return target;
	}
}
