package com.etek.dto;

/**
 * @author nikhil
 *
 */
public class EtekResponse {
	
	int status;
	int errCd;
	String msg;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getErrCd() {
		return errCd;
	}
	public void setErrCd(int errCd) {
		this.errCd = errCd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EtekResponse(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public EtekResponse(int status, int errCd, String msg) {
		super();
		this.status = status;
		this.errCd = errCd;
		this.msg = msg;
	}
	public EtekResponse(int status) {
		super();
		this.status = status;
	}
	
	

}
