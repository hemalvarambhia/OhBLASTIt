package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingNCBIBLASTQuery;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery.sendToNCBI;

public class SendingToNCBITest extends SendingABLASTQueryTest {

    public void setUp() throws Exception {
        super.setUp();
        query = validPendingNCBIBLASTQuery();
    }

    protected void sendBLASTQuery() throws InterruptedException, ExecutionException {
        sendToNCBI(context, new BLASTQuery[]{query});
    }

    protected void assertValidIdentifier(){
        String requestIdRegexPattern = "[A-Z0-9]{11}";
        boolean validRequestId = query.getJobIdentifier().matches(requestIdRegexPattern);
        String message = String.format(
                "%s is not a valid job identifier. It should take the form %s",
                query.getJobIdentifier(), requestIdRegexPattern);
        assertTrue(message, validRequestId);
    }

}
