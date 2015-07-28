package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.blastservices.NCBIBLASTService;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingNCBIBLASTQuery;

public class SendingToNCBITest extends SendingABLASTQueryTest {

    public void setUp() throws Exception {
        super.setUp();
        query = validPendingNCBIBLASTQuery();
        service = new NCBIBLASTService();
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
