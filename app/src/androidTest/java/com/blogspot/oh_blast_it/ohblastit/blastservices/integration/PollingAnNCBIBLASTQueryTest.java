package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.blastservices.BLASTSearchEngine;
import com.blogspot.oh_blast_it.ohblastit.blastservices.NCBIBLASTService;
import com.blogspot.oh_blast_it.ohblastit.blastservices.SearchStatus;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingNCBIBLASTQuery;

/**
 * Created by hemalvarambhia on 12/07/15.
 */
public class PollingAnNCBIBLASTQueryTest extends PollingABLASTQueryTest {
    protected Context context;
    protected BLASTQuery query;

    protected void setUp() throws Exception {
		context = getInstrumentation().getTargetContext();
		OhBLASTItTestHelper helper = new OhBLASTItTestHelper(context);
		helper.cleanDatabase();
        query = validPendingNCBIBLASTQuery();
	}

    public void testWeCanPollABLASTQueryForItsCurrentStatus() throws ExecutionException, InterruptedException {
        sendBLASTQuery();

        SearchStatus status = poll();

        assertValid(status);
    }

    protected void sendBLASTQuery() throws ExecutionException, InterruptedException {
        SendBLASTQuery.sendToNCBI(context, new BLASTQuery[]{query});
    }

    protected SearchStatus poll() throws ExecutionException, InterruptedException {
        BLASTSearchEngine service = new NCBIBLASTService();
        SearchStatus status = service.pollQuery(query.getJobIdentifier());
        service.close();

        return status;
    }

    protected void assertValid(SearchStatus status) {
        List<SearchStatus> validOutcomes = Arrays.asList(SearchStatus.values());
        boolean isValidStatus = validOutcomes.contains(status);
        String message = String.format(
                "Expected one of %s but got %s", validOutcomes.toString(), status.toString());
        Assert.assertTrue(message, isValidStatus);
    }
}
