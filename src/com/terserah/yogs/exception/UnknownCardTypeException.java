package com.terserah.yogs.exception;

public class UnknownCardTypeException extends UnexpectedFormatException{
	String unknownType;
	public String getUnknownType() {
		return unknownType;
	}
	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}
	public UnknownCardTypeException(String sourceFile, int sourceLine, String unknownType) {
		super(sourceFile, sourceLine);
		this.unknownType = unknownType;
		// TODO Auto-generated constructor stub
	}

}
