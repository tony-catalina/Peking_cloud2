package awd.cloud.platform.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 响应消息。controller中处理后，返回此对象，响应请求结果给客户端。
 */
@SuppressWarnings("rawtypes")
public class ResponseMessageForKss<T> implements Serializable {
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

    public static <T> ResponseMessageForKss<T> error(String message) {
        return error(500, message);
    }

    public static <T> ResponseMessageForKss<T> error(int status, String message) {
        ResponseMessageForKss<T> msg = new ResponseMessageForKss<>();
        msg.message = message;
        msg.status(status);
        return msg.putTimeStamp();
    }

    public static <T> ResponseMessageForKss<T> ok() {
        return ok(null);
    }

    private ResponseMessageForKss<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> ResponseMessageForKss<T> ok(T data) {
        return new ResponseMessageForKss<T>()
                .data(data)
                .putTimeStamp()
                .status(200);
    }

//    public ResponseMessageForKss and(String key, Object value) {
//        put(key, value);
//        return this;
//    }

    public ResponseMessageForKss<T> data(T data) {
        this.result = data;
        return this;
    }

    /**
     * 过滤字段：指定需要序列化的字段
     */
    private transient Map<Class<?>, Set<String>> includes;

    /**
     * 过滤字段：指定不需要序列化的字段
     */
    private transient Map<Class<?>, Set<String>> excludes;

    public ResponseMessageForKss() {

    }

    public ResponseMessageForKss<T> include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }

    public ResponseMessageForKss<T> include(Class<?> type, Collection<String> fields) {
        if (includes == null)
            includes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(includes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessageForKss<T> exclude(Class type, Collection<String> fields) {
        if (excludes == null)
            excludes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(excludes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessageForKss<T> exclude(Collection<String> fields) {
        if (excludes == null)
            excludes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        Class type;
        if (getResult() != null) type = getResult().getClass();
        else return this;
        exclude(type, fields);
        return this;
    }

    public ResponseMessageForKss<T> include(Collection<String> fields) {
        if (includes == null)
            includes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        Class type;
        if (getResult() != null) type = getResult().getClass();
        else return this;
        include(type, fields);
        return this;
    }

    public ResponseMessageForKss<T> exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    public ResponseMessageForKss<T> exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    public ResponseMessageForKss<T> include(String... fields) {
        return include(Arrays.asList(fields));
    }

    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
    }

    public ResponseMessageForKss<T> status(int status) {
        this.status = status;
        return this;
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return excludes;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return includes;
    }

}
