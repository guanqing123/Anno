package com.anno.test;

import com.anno.defined.Description;

@Description(desc = "I am class anno", author = "guanqing")
public class Child implements Person {

	@Override
	@Description(desc = "I am method anno", author = "guanqing")
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int age() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sing() {
		// TODO Auto-generated method stub
		
	}

}
