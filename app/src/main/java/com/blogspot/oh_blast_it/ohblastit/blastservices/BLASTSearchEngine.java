package com.blogspot.oh_blast_it.ohblastit.blastservices;

import com.blogspot.oh_blast_it.ohblastit.domain.BLASTQuery;

public interface BLASTSearchEngine {
	
	public String submit(BLASTQuery query);
	
	public SearchStatus pollQuery(String jobId);
	
	public String retrieveBLASTResults(String jobId, String format);
	
	public void close();

}
