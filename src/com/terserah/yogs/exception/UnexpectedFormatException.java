package com.terserah.yogs.exception;

public class UnexpectedFormatException extends Exception {
	String sourceFile;
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public int getSourceLine() {
		return sourceLine;
	}
	public void setSourceLine(int sourceLine) {
		this.sourceLine = sourceLine;
	}
	int sourceLine;
	public UnexpectedFormatException(String sourceFile, int sourceLine){
		super("UnexpectedFormatException");
		this.sourceFile = sourceFile;
		this.sourceLine = sourceLine;
	}
	

}
