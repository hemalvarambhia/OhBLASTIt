package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingEMBLBLASTQuery;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery.sendToEBIEMBL;

/**
 * Created by hemal on 07/01/15.
 */
public class SendingToEBIEMBLTest extends BLASTQuerySenderTest {

    public void setUp() throws Exception {
        super.setUp();
        query = validPendingEMBLBLASTQuery();
    }

    protected void send() throws InterruptedException, ExecutionException {
        sendToEBIEMBL(context, new BLASTQuery[]{query});
    }
}
