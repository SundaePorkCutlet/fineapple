package kr.or.fineapple.domain.community;

public class battleRecord {
	private int battleCount;
	private int winCount;
	private int loseCount;
	private int tieCount;
	public int getBattleCount() {
		return battleCount;
	}
	public void setBattleCount(int battleCount) {
		this.battleCount = battleCount;
	}
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	public int getTieCount() {
		return tieCount;
	}
	public void setTieCount(int tieCount) {
		this.tieCount = tieCount;
	}
	@Override
	public String toString() {
		return "battleRecord [battleCount=" + battleCount + ", winCount=" + winCount + ", loseCount=" + loseCount
				+ ", tieCount=" + tieCount + "]";
	}
	

}
