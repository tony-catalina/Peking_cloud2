package awd.cloud.platform.utils;


import java.util.List;

public class PagerResultForKss<E> implements Model {
    private static final long serialVersionUID = -6171751136953308027L;
    private int total;

    private List<E> data;

    public PagerResultForKss() {
    }

    public PagerResultForKss(int total, List<E> data) {
        this.total = total;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public PagerResultForKss<E> setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<E> getData() {
        return data;
    }

    public PagerResultForKss<E> setData(List<E> data) {
        this.data = data;
        return this;
    }

}