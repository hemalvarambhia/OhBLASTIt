package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.blastservices.NCBIBLASTService;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingNCBIBLASTQuery;

public class PollingAnNCBIBLASTQueryTest extends PollingABLASTQueryTest {
    protected void setUp() throws Exception {
		super.setUp();
        query = validPendingNCBIBLASTQuery();
        service = new NCBIBLASTService();
	}
}
