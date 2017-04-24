package com.terserah.yogs.exception;

public class NoSpaceException extends RuntimeException{
	public NoSpaceException(){
		super("No available space!");
	}

}
