package com.blogspot.oh_blast_it.ohblastit.persistence;

import java.util.List;

import android.test.InstrumentationTestCase;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

import com.blogspot.oh_blast_it.ohblastit.content.BLASTQueryLabBook;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

import static com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery.Status.*;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.*;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

public class RetrievingLegacyQueriesTest extends PersistenceTestCase {

	public void testLegacyRunningQueriesAreReturned(){
		insertRunningBLASTQuery();
		BLASTQueryController controller = new BLASTQueryController(getInstrumentation().getTargetContext());
		List<BLASTQuery> runningQueries = controller.getSubmittedBLASTQueries();
		controller.close();
		assertThat("Should return 'RUNNING' queries as 'SUBMITTED' ones", runningQueries.size(), is(1));
		assertThat("RUNNING queries should become SUBMITTED", runningQueries.get(0).getStatus(), is(SUBMITTED));
	}
	
	private void insertRunningBLASTQuery(){
		BLASTQuery sampleQuery = aBLASTQuery();
		sampleQuery.setStatus(RUNNING);
		sampleQuery.setJobIdentifier("ncbiblast-R20120418-133731-0240-81389354-pg");
		BLASTQueryLabBook labBook = new BLASTQueryLabBook(getInstrumentation().getTargetContext());
		labBook.save(sampleQuery);
	}
	
}
