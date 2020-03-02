package awd.mis.servers.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.mis.servers.tools.CacheUtils;

/**
 * \* Created with IntelliJ IDEA By WS
 * \* Date: 2017/11/22 13:47
 * \
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WgTree {

    private String id ;

    private String text;

    private String leaf;

    private String qtip;
    
    private String state;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public String getState() {
		return state;
	}
    
    public String getStateString() {
        return CacheUtils.get().getDictionary("YWSTATE", this.state);
    }

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "WgTree [id=" + id + ", text=" + text + ", leaf=" + leaf + ", qtip=" + qtip + ", state=" + state + "]";
	}
}
