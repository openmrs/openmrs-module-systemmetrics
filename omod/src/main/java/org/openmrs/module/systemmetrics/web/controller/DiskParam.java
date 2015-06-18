package org.openmrs.module.systemmetrics.web.controller;

public class DiskParam {
	private String absolutePath;
	private long totalSpace;
	private long freeSpace;
	private long usableSpace;
	
	public DiskParam(String aP, long tS, long fS, long uS){
		absolutePath = aP;
		totalSpace = tS;
		freeSpace = fS;
		usableSpace = uS;
	}
	
	public void setAbsolutePath(String AbsolutePath) { absolutePath = AbsolutePath; }
	public String getAbsolutePath() { return absolutePath; }
	
	public void setTotalSpace(long TotalSpace) { totalSpace = TotalSpace; }
	public long getTotalSpace() { return totalSpace; }
	
	public void setFreeSpace(long FreeSpace) { freeSpace = FreeSpace; }
	public long getFreeSpace() { return freeSpace; }
	
	public void setUsableSpace(long UsableSpace) { usableSpace = UsableSpace; }
	public long getUsableSpace() { return usableSpace; }
}
