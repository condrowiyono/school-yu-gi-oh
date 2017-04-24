package com.terserah.yogs.exception;
public class EmptyFieldException extends UnexpectedFormatException{

	int sourceField;
	public int getSourceField() {
		return sourceField;
	}
	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}
	public EmptyFieldException(String sourceFile, int sourceLine ,int sourceField) {
		super(sourceFile, sourceLine);
		this.sourceField = sourceField;
	}
	
	

}
