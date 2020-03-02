package awd.mis.desktop.tools;

import java.util.List;

import awd.mis.desktop.model.Model;

public class PagerResult<E> implements Model {
    private static final long serialVersionUID = -6171751136953308027L;
    private int total;
    private String msg;
    private int code;

    private List<E> data;

    public PagerResult() {
    }

    public PagerResult(int total, List<E> data) {
        this.total = total;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public PagerResult<E> setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<E> getData() {
        return data;
    }

    public PagerResult<E> setData(List<E> data) {
        this.data = data;
        return this;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	

    
}