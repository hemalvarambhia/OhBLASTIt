package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

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

public abstract class BLASTQuerySenderTest extends InstrumentationTestCase {

	protected Context context;
    protected BLASTQuery query;
	
	protected void setUp() throws Exception {
		context = getInstrumentation().getTargetContext();
		OhBLASTItTestHelper helper = new OhBLASTItTestHelper(context);
		helper.cleanDatabase();	
	}

    public void testWeCanSendABLASTQuery() throws InterruptedException, ExecutionException {
        send();

        assertSent();
    }

    protected abstract void send() throws InterruptedException, ExecutionException;

	protected void assertSent(){
		assertThat("Query was not assigned a job identifier", query.getJobIdentifier(), is(notNullValue()));
		assertThat("Job identifier was found to be blank", !(query.getJobIdentifier().isEmpty()));
		assertThat("Query wasn't submitted", query.getStatus(), is(Status.SUBMITTED));
	}
}
