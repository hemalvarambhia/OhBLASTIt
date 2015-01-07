package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery.*;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery.Status;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

/**
 * Here we will set the status of the queries to <code>PENDING</code>
 * as the BLAST query sender will send pending query provided from 
 * elsewhere
 * @author Hemal N Varambhia
 *
 */

public class BLASTQuerySenderTest extends InstrumentationTestCase {

	protected Context context;
	
	protected void setUp() throws Exception {
		context = getInstrumentation().getTargetContext();
		OhBLASTItTestHelper helper = new OhBLASTItTestHelper(context);
		helper.cleanDatabase();	
	}

	protected void assertSent(BLASTQuery query){
		assertThat("Query was not assigned a job identifier", query.getJobIdentifier(), is(notNullValue()));
		assertThat("Job identifier was found to be blank", !(query.getJobIdentifier().isEmpty()));
		assertThat("Query wasn't submitted", query.getStatus(), is(Status.SUBMITTED));
	}
}
