package com.anno.defined;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/******************元注解开始***************************/
/*CONSTRUCTOR(构造函数),FIELD(字段声明),LOCAL_VARIABLE(局部变量声明),
	METHOD(方法声明),PACKAGE(包声明),PARAMETER(参数声明),TYPE(类,接口)*/
@Target({ElementType.METHOD,ElementType.TYPE})
/*SOURCE(只在源码显示,编译时会丢失),CLASS(编译时会记录到class中,运行时忽略),
  RUNTIME(运行时存在,可以通过反射读取)*/
@Retention(RetentionPolicy.RUNTIME)
/*允许子类继承*/
@Inherited
/*生成javadoc时会包含注解*/
@Documented
/******************元注解结束***************************/
public @interface Description {	//使用 @interface关键字定义注解
	
	String desc();	//成员以无参无异常方式声明
	
	String author();	
	
	int age() default 18;	//可以用default为成员指定一个默认值
	
	/*
	 成员类型是受限的,合法的类型包括原始类型及String,Class,Annotation,Enumeration
	如果注解只有一个成员,则成员名必须取名为value(),在使用时可以忽略成员名和赋值号(=)
	注解类可以没有成员,没有成员的注解称为标识注解
	*/
}
