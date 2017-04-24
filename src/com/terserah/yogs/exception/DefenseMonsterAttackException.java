package com.terserah.yogs.exception;

public class DefenseMonsterAttackException extends RuntimeException {
	public DefenseMonsterAttackException(){
		super("You cannot attack with a monster in defense mode!");
	}
}


