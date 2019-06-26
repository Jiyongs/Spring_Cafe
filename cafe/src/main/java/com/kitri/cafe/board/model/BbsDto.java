package com.kitri.cafe.board.model;

//Bbs : 자료게시판
public class BbsDto extends BoardDto{
	
	private int bseq;
	private String orignFile;
	private String saveFile;
	private String saveFolder;
	private long filesize;		// file.size()의 리턴 타입이 long

	
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	
	public String getOrignFile() {
		return orignFile;
	}
	public void setOrignFile(String orignFile) {
		this.orignFile = orignFile;
	}
	
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
}
