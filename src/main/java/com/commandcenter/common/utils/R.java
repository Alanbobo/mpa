package com.commandcenter.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.commandcenter.common.utils.encrypt.AesEncryptUtil.aesEncrypt;

/**
 * 返回数据
 * 
 * @author r25437
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("success",0);
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("success",1);
		r.put("code", 1);
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.put("success",1);
		r.put("code", 1);
		r.put("msg", "执行成功");
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		R r = new R();
		r.put("success",1);
		r.put("code", 1);
		r.put("msg", "执行成功");
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	@SuppressWarnings("unchecked")
	public R trans(Object value){
		super.put("success",1);
		super.put("code", 1);
		super.put("msg", "执行成功");
		if(value instanceof PageUtils){
			Map<String,Object> pageMap = new HashMap<>();
			int totalCount = ((PageUtils) value).getTotalCount();
			super.put("totalCount",totalCount==-1?0:totalCount);
			super.put("pageSize",((PageUtils) value).getPageSize());
			super.put("currentPage",((PageUtils) value).getCurrPage());
			super.put("totalPage",((PageUtils) value).getTotalPage());
			super.put("page",((PageUtils) value).getList());
			return this;
		}else{
			return this;
		}
	}

	public static R update(){
		R r = new R();
		r.put("success",1);
		r.put("code", 1);
		r.put("cmd", "update");
		r.put("msg", "有新的版本");
		return r;
	}
	public static R unUpdate(){
		R r = new R();
		r.put("success",1);
		r.put("code", 1);
		r.put("cmd", "update");
		r.put("msg", "");
		return r;
	}
}
