package com.msp.shofydrop.database;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultProcedureRepo
{
	<T> List<T> getWithType(String pname, Object[][] params, Class<T> type);
	Object[] executeWithType(String pname, Object[][] params);
}
