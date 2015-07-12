package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.blastservices.SearchStatus;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hemalvarambhia on 12/07/15.
 */
public abstract class PollingABLASTQueryTest extends InstrumentationTestCase {
    public void testWeCanPollABLASTQueryForItsCurrentStatus() throws ExecutionException, InterruptedException {
        sendBLASTQuery();

        SearchStatus status = poll();

        assertValid(status);
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
