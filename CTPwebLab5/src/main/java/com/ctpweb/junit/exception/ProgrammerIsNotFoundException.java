package com.ctpweb.junit.exception;

import lombok.NoArgsConstructor;

public class ProgrammerIsNotFoundException extends RuntimeException {
	public ProgrammerIsNotFoundException ()
	{
		super("ProgrammerIsNotFoundException");
	}
	
}