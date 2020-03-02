package awd.mis.servers.tools;

import java.util.HashMap;
import java.util.Map;


/**
 * \* Created with IntelliJ IDEA By WS
 * \* Date: 2017/11/22 10:18
 * \
 */

public class ResultUtils {


	public static Result<Object> ok(Object object) {
		Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg("成功");
        result.setResult(object);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> ok(Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg("成功");
        result.setResult(data);
        result.setTotalSize(totalSize);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> ok(String msg,Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg(msg);
        result.setResult(data);
        result.setTotalSize(totalSize);
        result.setSuccess(true);
        return result;
    }

    public static Result<Object> ok() {
        return ok(null);
    }

	public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
	public static Result<Object> error(String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(Result.ERR_EXCEPTION);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }

}
