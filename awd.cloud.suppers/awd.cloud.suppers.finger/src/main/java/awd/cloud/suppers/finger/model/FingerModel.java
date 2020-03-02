package awd.cloud.suppers.finger.model;

import java.util.Arrays;

public class FingerModel {
	String rybhOrZjh;
	String jsbh;
	String[] fingerdata;
	String[] featuredata;
	byte[] fingercode;
	
	
	public String getRybhOrZjh() {
		return rybhOrZjh;
	}
	public void setRybhOrZjh(String rybhOrZjh) {
		this.rybhOrZjh = rybhOrZjh;
	}
	public String getJsbh() {
		return jsbh;
	}
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public String[] getFingerdata() {
		return fingerdata;
	}
	public void setFingerdata(String[] fingerdata) {
		this.fingerdata = fingerdata;
	}
	public String[] getFeaturedata() {
		return featuredata;
	}
	public void setFeaturedata(String[] featuredata) {
		this.featuredata = featuredata;
	}
	public byte[] getFingercode() {
		return fingercode;
	}
	public void setFingercode(byte[] fingercode) {
		this.fingercode = fingercode;
	}
	@Override
	public String toString() {
		return "FingerModel [rybhOrZjh=" + rybhOrZjh + ", jsbh=" + jsbh + ", fingerdata=" + Arrays.toString(fingerdata)
				+ ", featuredata=" + Arrays.toString(featuredata) + ", fingercode=" + Arrays.toString(fingercode) + "]";
	}

}