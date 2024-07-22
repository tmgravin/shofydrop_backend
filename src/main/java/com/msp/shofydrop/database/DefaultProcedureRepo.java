package com.msp.shofydrop.database;

import java.util.List;

public interface DefaultProcedureRepo
{
	<T> List<T> getWithType(String pname, Object[][] params, Class<T> type);
	Object[] executeWithType(String pname, Object[][] params);
}
