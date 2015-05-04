package edu.iit.cs445.vin;

public class Errors implements java.io.Serializable  {

	private static final long serialVersionUID = -5300022234647370392L;
	private int code;
	private String message;
	public Errors(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
