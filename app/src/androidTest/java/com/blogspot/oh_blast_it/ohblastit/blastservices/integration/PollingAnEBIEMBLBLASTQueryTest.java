package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.blogspot.oh_blast_it.ohblastit.blastservices.BLASTSearchEngine;
import com.blogspot.oh_blast_it.ohblastit.blastservices.EMBLEBIBLASTService;
import com.blogspot.oh_blast_it.ohblastit.blastservices.NCBIBLASTService;
import com.blogspot.oh_blast_it.ohblastit.blastservices.SearchStatus;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;
import com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.*;

public class PollingAnEBIEMBLBLASTQueryTest extends PollingABLASTQueryTest {
    protected void setUp() throws Exception {
        super.setUp();
        query = validPendingEMBLBLASTQuery();
    }

    protected void sendBLASTQuery() throws ExecutionException, InterruptedException {
        SendBLASTQuery.sendToEBIEMBL(context, new BLASTQuery[]{query});
    }

    protected SearchStatus poll() throws ExecutionException, InterruptedException {
        BLASTSearchEngine service = new EMBLEBIBLASTService();
        SearchStatus status = service.pollQuery(query.getJobIdentifier());
        service.close();

        return status;
    }
}
