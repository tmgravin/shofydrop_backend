package com.shofydrop.database;

import java.util.List;

public interface DefaultProcedureDao
{
	<T> List<T> getWithType(String pname, Object[][] params, Class<T> type);
	Object[] executeWithType(String pname, Object[][] params);
}
