package com.anno.practise;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);
		
		Filter f2 = new Filter();
		f2.setUserName("lucy");
		
		Filter f3 = new Filter();
		f3.setEmail("liu@sina.com,zh@163.com,77777@qq.com");
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
	}
	
	public static String query(Filter f) {
		StringBuilder sb = new StringBuilder();
		// 1.获取class
		Class cs = f.getClass();
		
		// 2.获取表名
		boolean tExist = cs.isAnnotationPresent(Table.class);
		if (!tExist)
			return null;
		
		Table t = (Table) cs.getAnnotation(Table.class);
		String tableName = t.value();
		sb.append("select * from ").append(tableName).append(" where 1=1 ");
		
		// 3.遍历所有字段
		Field[] fs = cs.getDeclaredFields();
		for (Field field : fs) {
			// 4.处理每个字段对应的sql
			// 4.1 拿到字段名
			boolean fExist = field.isAnnotationPresent(Column.class);
			if (!fExist)
				continue;
			Column c = field.getAnnotation(Column.class);
			String columnName = c.value();
			// 4.2拿到字段值
			String fieldName = field.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object fieldValue = null;
			try {
				Method getMethod = cs.getDeclaredMethod(getMethodName);
				fieldValue = getMethod.invoke(f, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			// 4.3拼装sql
			if (fieldValue == null || (fieldValue instanceof Integer && (Integer)fieldValue == 0))
				continue;
			
			if (fieldValue instanceof String) {
				if (((String) fieldValue).contains(",")) {
					sb.append(" and ").append(fieldName).append(" in (");
					String[] values = ((String) fieldValue).split(",");
					for (String str : values) {
						sb.append("'").append(str).append("',");
					}
					sb.deleteCharAt(sb.length() - 1);
					sb.append(")");
				}else {
					sb.append(" and ").append(fieldName).append(" = ").append("'").append(fieldValue).append("'");
				}
			}else {
				sb.append(" and ").append(fieldName).append(" = ").append(fieldValue);
			}
		}
		return sb.toString();
	}
	
}
