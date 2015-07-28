package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.blastservices.BLASTSearchEngine;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery.Status;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

public abstract class SendingABLASTQueryTest extends InstrumentationTestCase {

	protected Context context;
    protected BLASTQuery query;
	protected BLASTSearchEngine service;

	protected void setUp() throws Exception {
		context = getInstrumentation().getTargetContext();
		OhBLASTItTestHelper helper = new OhBLASTItTestHelper(context);
		helper.cleanDatabase();	
	}

    public void testWeCanSendABLASTQuery() throws InterruptedException, ExecutionException {
        sendBLASTQuery();

        assertSent();
    }

	protected void sendBLASTQuery() throws InterruptedException, ExecutionException {
        String identifier = service.submit(query);
        query.setJobIdentifier(identifier);
    }

	protected void assertSent() {
		assertThat("Query was not assigned a job identifier", query.getJobIdentifier(), is(notNullValue()));
		assertThat("Job identifier was found to be blank", !(query.getJobIdentifier().isEmpty()));
		assertValidIdentifier();
	}

	protected abstract void assertValidIdentifier();

	protected void tearDown() throws Exception {
        super.tearDown();
        service.close();
    }
}
