package com.msp.shofydrop.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefaultProcedureRepoImpl implements DefaultProcedureRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> List<T> getWithType(String pname, Object[][] params, Class<T> type) {
        StringBuilder queryString = new StringBuilder("CALL ").append(pname).append("(");

        for (int i = 0; i < params.length; i++) {
            if (i > 0) {
                queryString.append(", ");
            }
            queryString.append("?");
        }
        queryString.append(")");

        Query query = entityManager.createNativeQuery(queryString.toString(), type);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i][1]);
        }

        return query.getResultList();
    }

    @Override
    public Object[] executeWithType(String pname, Object[][] params) {
        StringBuilder queryString = new StringBuilder("CALL ").append(pname).append("(");

        for (int i = 0; i < params.length; i++) {
            if (i > 0) {
                queryString.append(", ");
            }
            queryString.append("?");
        }
        queryString.append(")");

        Query query = entityManager.createNativeQuery(queryString.toString());

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i][1]);
        }

        return query.getResultList().toArray();
    }
}


//    @Override
//    public<T> List<T> getWithType(String pname, Object[][] params, Class<T> type)
//    {
//    	int i=0;
//    	String queryString = "call " + pname + "(";
//
//    	for (i = 0; i < params.length; i++)
//    	{
//    		queryString = queryString + ":" + params[i].toString() + ",";
//    	}
//
//    	queryString = queryString + ")";
//    	Query query = entityManager.createNativeQuery(queryString, type);
//
//    	for (i = 0; i < params.length; i++)
//    	{
//    		query.setParameter(params[i].toString(), params[i].toString());
//    	}
//
//        Query storedProcedure = entityManager.createNativeQuery(queryString);
//
//        return storedProcedure.getResultList();
//    }
//}