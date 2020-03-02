package awd.mis.desktop.bean;

import java.util.Date;

public class File {
	String name;
	String path;
	String ext;
	String type;
	String mode;
	String   atime;
	String   ctime;
	String   mtime;
	int    isReadbale;
	int    isWriteable;
	long   size;
	boolean isParent;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public int getIsReadbale() {
		return isReadbale;
	}
	public void setIsReadbale(int isReadbale) {
		this.isReadbale = isReadbale;
	}
	public int getIsWriteable() {
		return isWriteable;
	}
	public void setIsWriteable(int isWriteable) {
		this.isWriteable = isWriteable;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
	

}
