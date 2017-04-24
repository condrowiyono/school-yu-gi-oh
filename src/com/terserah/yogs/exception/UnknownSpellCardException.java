package com.terserah.yogs.exception;

public class UnknownSpellCardException extends UnexpectedFormatException{
	String unknownSpell;
	public String getUnknownSpell() {
		return unknownSpell;
	}
	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}
	public UnknownSpellCardException(String sourceFile, int sourceLine, String unkownSpell) {
		super(sourceFile, sourceLine);
		this.unknownSpell = unkownSpell;
		// TODO Auto-generated constructor stub
	}

}
