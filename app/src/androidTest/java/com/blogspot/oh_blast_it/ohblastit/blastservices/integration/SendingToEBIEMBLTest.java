package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.blastservices.EMBLEBIBLASTService;

import junit.framework.Assert;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingEMBLBLASTQuery;

public class SendingToEBIEMBLTest extends SendingABLASTQueryTest {

    public void setUp() throws Exception {
        super.setUp();
        query = validPendingEMBLBLASTQuery();
        service = new EMBLEBIBLASTService();
    }

    protected void assertValidIdentifier() {
        boolean validId = false;
        String jobIdRegex = "ncbiblast\\-[A-Z][0-9]{8}\\-[0-9]{6}\\-[0-9]{4}\\-[0-9]{6,8}\\-[a-z]{2}";
        validId = query.getJobIdentifier().matches(jobIdRegex);
        String message = String.format(
                "%s is not a valid job identifier. Should take the form %s",
                query.getJobIdentifier(), jobIdRegex);
        Assert.assertTrue(message, validId);
    }

}
