package com.anno.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Sql {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Filter f1 = new Filter();
		f1.setDepartId(10);
		
		Filter f2 = new Filter();
		f2.setDepartName("信息中心");
		
		Filter f3 = new Filter();
		f3.setTeacher("10,15,20");
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);

		System.out.println(sql2);

		System.out.println(sql3);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String query(Filter f) throws Exception {
		// 1.获取class
		Class cs = f.getClass();
		
		boolean exist = cs.isAnnotationPresent(Table.class);
		if (!exist)
			return null;
		
		// 2.获取表名
		Table t = (Table) cs.getAnnotation(Table.class);
		String tableName = t.value();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(tableName).append(" where 1=1 ");
		
		
		
		Field[] fs = cs.getDeclaredFields();
		for (Field field : fs) {
			boolean fExist = field.isAnnotationPresent(Column.class);
			if (!fExist)
				continue;
			
			// 3.获取字段名
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			
			// 4.获取字段值
			String fieldName = field.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = cs.getDeclaredMethod(getMethodName);
			Object fieldValue = getMethod.invoke(f);
			
			if (fieldValue == null || (fieldValue instanceof Integer && (Integer)fieldValue == 0))
				continue;
			
			if (fieldValue instanceof String) {
				if (((String) fieldValue).contains(",")) {
					String[] values = ((String) fieldValue).split(",");
					sb.append(" and ").append(columnName).append(" in ").append(" ( ");
					for (String str : values) {
						sb.append("'").append(str).append("',");
					}
					sb.deleteCharAt(sb.length() - 1);
					sb.append(")");
				}else {
					sb.append(" and ").append(columnName).append(" = ").append("'").append(fieldValue).append("'");
				}
			}else {
				sb.append(" and ").append(columnName).append(" = ").append(fieldValue);
			}
			
		}
		return sb.toString();
	}

}
