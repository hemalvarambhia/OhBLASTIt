package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.blastservices.SearchStatus;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class PollingABLASTQueryTest extends InstrumentationTestCase {
    protected Context context;
    protected BLASTQuery query;

    protected void setUp() throws Exception {
        context = getInstrumentation().getTargetContext();
        OhBLASTItTestHelper helper = new OhBLASTItTestHelper(context);
        helper.cleanDatabase();
    }

    public void testWeCanPollABLASTQueryForItsCurrentStatus() throws ExecutionException, InterruptedException {
        sendBLASTQuery();

        SearchStatus status = poll();

        assertValid(status);
    }

    public void testWeGetNotFoundForANonExistentBlastQuery() throws ExecutionException, InterruptedException{
        query.setJobIdentifier("NON_EXISTENT123");

        SearchStatus status = poll();

        assertEquals(SearchStatus.NOT_FOUND, status);
    }

    protected abstract void sendBLASTQuery() throws ExecutionException, InterruptedException;

    protected abstract SearchStatus poll() throws ExecutionException, InterruptedException;

    protected void assertValid(SearchStatus status) {
        List<SearchStatus> validOutcomes = Arrays.asList(SearchStatus.values());
        boolean isValidStatus = validOutcomes.contains(status);
        String message = String.format(
                "Expected one of %s but got %s", validOutcomes.toString(), status.toString());
        Assert.assertTrue(message, isValidStatus);
    }
}
