package com.blogspot.oh_blast_it.ohblastit.persistence;

import java.util.ArrayList;
import java.util.List;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.domain.SearchParameter;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery.BLASTJob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SearchParameterController {

	private SearchParametersDAO mDAO;
	
	public SearchParameterController(Context context){
		mDAO = new SearchParametersDAO(context);
		mDAO.open();
	}
	
	public void close(){
		mDAO.close();
	}
	
	public long save(SearchParameter parameter){
		ContentValues values = new ContentValues();
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_PARAM_NAME, parameter.getName());
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_PARAM_VALUE, parameter.getValue());
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_QUERY_FK, parameter.getBlastQueryId());
	
		long parameterPrimaryKey = mDAO.insert(values);
		
		return parameterPrimaryKey;
	}

	public void saveFor(long blastQueryId, SearchParameter parameter) {
		ContentValues values = new ContentValues();
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_PARAM_NAME, parameter.getName());
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_PARAM_VALUE, parameter.getValue());
		values.put(BLASTQuery.BLASTJob.COLUMN_NAME_BLASTQUERY_QUERY_FK, blastQueryId);
		
		mDAO.insert(values);
	}
	
	public List<SearchParameter> getParametersForQuery(long queryId){
		
		String where = BLASTJob.COLUMN_NAME_BLASTQUERY_QUERY_FK +" = ?";
		String[] queryFK = new String[]{String.valueOf(queryId)};
		List<SearchParameter> parametersForQuery = new ArrayList<SearchParameter>();;
		Cursor cursor = mDAO.query(BLASTJob.OPTIONAL_PARAMETER_FULL_PROJECTION, where, queryFK);
		
		while(cursor.moveToNext()){
			String parameterName = cursor.getString(1);
			String valueOfParameter = cursor.getString(2);
			SearchParameter parameter = new SearchParameter(parameterName, valueOfParameter);
			parametersForQuery.add(parameter);
		}
		cursor.close();
		
		return parametersForQuery;
		
	}

	public int deleteParametersFor(long queryId){
		return mDAO.deleteOptionsFor(queryId);
	}
}
