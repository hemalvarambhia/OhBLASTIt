package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.blastservices.EMBLEBIBLASTService;


import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.*;

public class PollingAnEBIEMBLBLASTQueryTest extends PollingABLASTQueryTest {

    protected void setUp() throws Exception {
        super.setUp();
        query = validPendingEMBLBLASTQuery();
        service = new EMBLEBIBLASTService();
    }
}
