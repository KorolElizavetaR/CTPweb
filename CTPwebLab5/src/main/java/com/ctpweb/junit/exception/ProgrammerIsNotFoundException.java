package com.ctpweb.junit.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProgrammerIsNotFoundException extends RuntimeException {
	ProgrammerIsNotFoundException (String msg)
	{
		super(msg);
	}
	
}
