package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingEMBLBLASTQuery;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery.sendToEBIEMBL;

/**
 * Created by hemal on 07/01/15.
 */
public class SendingToEBIEMBLTest extends BLASTQuerySenderTest {
    public void testWeCanSendABLASTQuery() throws InterruptedException, ExecutionException {
        BLASTQuery ebiemblQuery = validPendingEMBLBLASTQuery();

        sendToEBIEMBL(context, new BLASTQuery[]{ebiemblQuery});

        assertSent(ebiemblQuery);
    }
}
