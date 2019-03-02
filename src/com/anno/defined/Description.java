package com.anno.defined;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/******************Ԫע�⿪ʼ***************************/
/*CONSTRUCTOR(���캯��),FIELD(�ֶ�����),LOCAL_VARIABLE(�ֲ���������),
	METHOD(��������),PACKAGE(������),PARAMETER(��������),TYPE(��,�ӿ�)*/
@Target({ElementType.METHOD,ElementType.TYPE})
/*SOURCE(ֻ��Դ����ʾ,����ʱ�ᶪʧ),CLASS(����ʱ���¼��class��,����ʱ����),
  RUNTIME(����ʱ����,����ͨ�������ȡ)*/
@Retention(RetentionPolicy.RUNTIME)
/*��������̳�*/
@Inherited
/*����javadocʱ�����ע��*/
@Documented
/******************Ԫע�����***************************/
public @interface Description {	//ʹ�� @interface�ؼ��ֶ���ע��
	
	String desc();	//��Ա���޲����쳣��ʽ����
	
	String author();	
	
	int age() default 18;	//������defaultΪ��Աָ��һ��Ĭ��ֵ
	
	/*
	 ��Ա���������޵�,�Ϸ������Ͱ���ԭʼ���ͼ�String,Class,Annotation,Enumeration
	���ע��ֻ��һ����Ա,���Ա������ȡ��Ϊvalue(),��ʹ��ʱ���Ժ��Գ�Ա���͸�ֵ��(=)
	ע�������û�г�Ա,û�г�Ա��ע���Ϊ��ʶע��
	*/
}
