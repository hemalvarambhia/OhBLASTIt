package com.blogspot.oh_blast_it.ohblastit.blastservices;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTVendor;

public class BLASTSearchEngineFactory  {

	public static BLASTSearchEngine getBLASTSearchEngineFor(int blastVendor) {
		switch(blastVendor){
		case BLASTVendor.EMBL_EBI:
			return new EMBLEBIBLASTService();
		case BLASTVendor.NCBI:
			return new NCBIBLASTService();
		default:
			return null;
		}
	}
}
