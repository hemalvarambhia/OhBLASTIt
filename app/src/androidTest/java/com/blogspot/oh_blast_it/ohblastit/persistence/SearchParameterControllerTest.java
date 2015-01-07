package com.blogspot.oh_blast_it.ohblastit.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.domain.SearchParameter;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

public class SearchParameterControllerTest extends PersistenceTestCase {

	private SearchParameterController searchParameterController;
	
	private SearchParameter parameter;
	
	private final long blastQueryId = 1l;
	
	protected void setUp() throws Exception {
		super.setUp();
		searchParameterController = new SearchParameterController(getInstrumentation().getTargetContext());
		parameter = aSearchParameter();
		saveFor(blastQueryId, parameter);
	}
	
	protected void tearDown() throws Exception {
		searchParameterController.close();
		super.tearDown();	
	}
	
	public void testWeCanSaveTheSearchParametersOfAQuery(){
		SearchParameter parameterFromDatastore = searchParameterController.getParametersForQuery(blastQueryId).get(0);
		
		assertThat("Should be able store a SearchParameter in datastore", parameterFromDatastore, is(parameter));
	}

	public void testWeCanRetrieveTheSearchParametersOfAQuery(){
		List<SearchParameter> parameters = searchParameterController.getParametersForQuery(blastQueryId);
		
		List<SearchParameter> expected = new ArrayList<SearchParameter>();
		expected.add(parameter);
		assertThat("Should be able to retrieve the search parameters of a query", parameters, is(expected));
	}
	
	public void testWeCanDeleteTheSearchParametersOfAQuery(){
		searchParameterController.deleteParametersFor(blastQueryId);
		
		List<SearchParameter> parameters = searchParameterController.getParametersForQuery(blastQueryId);
		
		assertThat("Should be able to delete all search parameters of a query", parameters.isEmpty());
	}

	private SearchParameter saveFor(long blastQueryId, SearchParameter parameter){
		searchParameterController.saveFor(blastQueryId, parameter);
		return parameter;
	}
	
	private SearchParameter aSearchParameter(){
		SearchParameter parameter = new SearchParameter("email", "h.n.varambhia@gmail.com");
		return parameter;
	}
	
}
