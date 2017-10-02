package com.cyl.work;

public class WorkRank {

	private int id; //员工工作编号
	
	private int rankNum; //员工排钟个数
	
	private int spotNum;  //员工排钟个数
	
	
	public WorkRank() {
		
	}
	
	public WorkRank(int id) {
		this.id = id;
	}

	
	public WorkRank(int id, int rankNum, int spotNum) {
		this.id = id;
		this.rankNum = rankNum;
		this.spotNum = spotNum;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getRankNum() {
		return rankNum;
	}


	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}


	public int getSpotNum() {
		return spotNum;
	}


	public void setSpotNum(int spotNum) {
		this.spotNum = spotNum;
	}
	
}
