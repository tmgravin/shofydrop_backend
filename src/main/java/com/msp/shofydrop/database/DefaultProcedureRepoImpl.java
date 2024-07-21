package com.msp.shofydrop.database;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;



@Service
public class DefaultProcedureRepoImpl implements DefaultProcedureRepo
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    private <T> StoredProcedureQuery defineAndCreateProcedureCall(String pname, Object[][] params, Class<T> type)
    {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(pname, type);

        storedProcedure.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        for (int i = 0; i < params.length; i++)
        {
            Object[] param = params[i];
            storedProcedure.registerStoredProcedureParameter(i+2, (Class) param[0], ParameterMode.IN);
            storedProcedure.setParameter(i+2, param[1]);
        }
        return storedProcedure;
    }

    private StoredProcedureQuery defineAndCreateProcedureCall(String pname, Object[][] params)
    {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(pname);

        for (int i = 0; i < params.length; i++)
        {
            Object[] param = params[i];
            storedProcedure.registerStoredProcedureParameter(i+1, (Class) param[0], ParameterMode.IN);
            storedProcedure.setParameter(i+1, param[1]);
        }
        return storedProcedure;
    }

    private<T> List<T> executeProcedureCallForList(StoredProcedureQuery storedProcedure)
    {
        try
        {
            storedProcedure.execute();
            return storedProcedure.getResultList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                storedProcedure.unwrap(ProcedureOutputs.class).release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

    private Object[] executeProcedureCallForArray(StoredProcedureQuery storedProcedure)
    {
        try
        {
            storedProcedure.execute();
            return storedProcedure.getResultList().toArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                storedProcedure.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public<T> List<T> getWithType(String pname, Object[][] params, Class<T> type)
    {
        return executeProcedureCallForList(defineAndCreateProcedureCall(pname,params, type));
    }


    @Override
    public Object[] executeWithType(String pname, Object[][] params) {
        return executeProcedureCallForArray(defineAndCreateProcedureCall(pname,params));
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
}