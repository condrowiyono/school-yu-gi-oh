package com.terserah.yogs.exception;

public class MonsterMultipleAttackException extends RuntimeException{
	public MonsterMultipleAttackException(){
		super("You cannot attack with this monster again!");
	}

}
