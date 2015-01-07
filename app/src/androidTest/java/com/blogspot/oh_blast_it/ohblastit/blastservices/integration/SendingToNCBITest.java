package com.blogspot.oh_blast_it.ohblastit.blastservices.integration;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

import java.util.concurrent.ExecutionException;

import static com.blogspot.oh_blast_it.ohblastit.testhelpers.BLASTQueryBuilder.validPendingNCBIBLASTQuery;
import static com.blogspot.oh_blast_it.ohblastit.testhelpers.SendBLASTQuery.sendToNCBI;

/**
 * Created by hemal on 07/01/15.
 */
public class SendingToNCBITest extends BLASTQuerySenderTest {

    public void testWeCanSendABLASTQuery() throws InterruptedException, ExecutionException {
        BLASTQuery ncbiQuery = validPendingNCBIBLASTQuery();

        sendToNCBI(context, new BLASTQuery[]{ncbiQuery});

        assertSent(ncbiQuery);
    }

}
