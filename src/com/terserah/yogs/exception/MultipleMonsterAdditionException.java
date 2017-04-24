package com.terserah.yogs.exception;

public class MultipleMonsterAdditionException  extends RuntimeException{
	public MultipleMonsterAdditionException(){
		super("You cannot add another monster!");
	}

}
