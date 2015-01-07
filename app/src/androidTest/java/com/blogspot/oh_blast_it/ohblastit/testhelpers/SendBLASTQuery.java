package com.blogspot.oh_blast_it.ohblastit.testhelpers;

import java.util.concurrent.ExecutionException;

import android.content.Context;

import com.blogspot.oh_blast_it.ohblastit.blastservices.BLASTQuerySender;
import com.blogspot.oh_blast_it.ohblastit.blastservices.EMBLEBIBLASTService;
import com.blogspot.oh_blast_it.ohblastit.blastservices.NCBIBLASTService;
import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

public class SendBLASTQuery {

	public static void sendToNCBI(Context context, BLASTQuery... queries) throws InterruptedException, ExecutionException{
		BLASTQuerySender sender = new BLASTQuerySender(context, new NCBIBLASTService());
		sender.execute(queries);
		sender.get();
	}
	
	public static void sendToEBIEMBL(Context context, BLASTQuery... queries) throws InterruptedException, ExecutionException{
		BLASTQuerySender sender = new BLASTQuerySender(context, new EMBLEBIBLASTService());
		sender.execute(queries);
		sender.get();
	}
	
}
