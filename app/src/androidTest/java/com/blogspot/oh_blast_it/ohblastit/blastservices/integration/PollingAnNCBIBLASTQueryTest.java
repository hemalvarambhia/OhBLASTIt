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

public class PollingAnNCBIBLASTQueryTest extends PollingABLASTQueryTest {
    protected void setUp() throws Exception {
		super.setUp();
        query = validPendingNCBIBLASTQuery();
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
}
