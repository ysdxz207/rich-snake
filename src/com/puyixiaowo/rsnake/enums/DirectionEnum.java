package com.puyixiaowo.rsnake.enums;

public enum DirectionEnum {
	UP(0),
	DOWN(1),
	LEFT(2),
	RIGHT(3);
	
	DirectionEnum(int code){
		this.code = code;
	}
	
	public int code;
}
