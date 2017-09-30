package com.revanow.base.view;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSON;
import com.revanow.base.form.BaseForm;

public class CustomJsonView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		model = fieltClass(model);
		out.print(JSON.toJSONString(model));
	}

	private Map<String, Object> fieltClass(Map<String, Object> model) {
		Set<String> excludeFields = new HashSet<String>();
		excludeFields.add("class");   
	    excludeFields.add("declaringClass");   
	    excludeFields.add("metaClass");
	    excludeFields.add("className");
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    for (String key : model.keySet()) {
			if(!excludeFields.contains(key)){
				if(model.get(key) instanceof BaseForm || model.get(key) instanceof BeanPropertyBindingResult){
					continue;
				}
				resultMap.put(key, model.get(key));
			}
		}
		return resultMap;
	}

}
