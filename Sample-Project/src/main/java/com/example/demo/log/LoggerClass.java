package com.example.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggerClass {
	
	protected final Logger log=LoggerFactory.getLogger(getClass());

}
