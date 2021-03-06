package com.anno.defined;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnno {

	public static void main(String[] args) {
		
		try {
			// 1.使用类加载器加载类
			Class c = Class.forName("com.anno.test.Child");
			// 2.找到类上面的注解
			boolean isExist = c.isAnnotationPresent(Description.class);
			if (isExist) {
				// 3.拿到注解实例
				Description d = (Description) c.getAnnotation(Description.class);
				System.out.println("desc = " + d.desc() +"\t author = " + d.author());
			}
			
			// 4.找到方法上的注解
			Method[] ms = c.getMethods();
			for (Method m : ms) {
				Annotation[] as = m.getAnnotations();
				for (Annotation a : as) {
					if (a instanceof Description) {
						Description d = (Description) a;
						System.out.println("desc = " + d.desc() +"\t author = " + d.author());
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
