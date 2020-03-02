package awd.cloud.platform.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * 响应消息。controller中处理后，返回此对象，响应请求结果给客户端。
 */
@SuppressWarnings("rawtypes")
public class ResponseMessage<T> implements Serializable {
    private static final long serialVersionUID = 8992436576262574064L;

    private String message;

    private T result;

    private int status;

    private Long timestamp;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public static <T> ResponseMessage<T> error(String message) {
        return error(500, message);
    }

    public static <T> ResponseMessage<T> error(int status, String message) {
        ResponseMessage<T> msg = new ResponseMessage<>();
        msg.message = message;
        msg.status(status);
        msg.putTimeStamp();
        return msg;
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> ResponseMessage<T> ok(T data) {
        return new ResponseMessage<T>()
                .data(data)
                .putTimeStamp()
                .status(200);
    }
    public static <T> ResponseMessage<T> ok(T data,String msg) {
        return new ResponseMessage<T>()
                .message(msg)
                .data(data)
                .putTimeStamp()
                .status(200);
    }

    
    public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResponseMessage<T> data(T data) {
        this.result = data;
        return this;
    }

   

    public ResponseMessage() {

    }


    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
    }


    public ResponseMessage<T> status(int status) {
        this.status = status;
        return this;
    }
    public ResponseMessage<T> message(String message) {
        this.message = message;
        return this;
    }


}