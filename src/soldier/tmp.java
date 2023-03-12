package soldier;

import java.util.HashMap;
import java.util.Map;

public class tmp {
	public static void main(String[] args) {
		init();
		hire(16, 1, 5);
		hire(21,1,5);
		hire(25, 1, 5);
		hire(17, 1, 2);
		hire(22,1,2);
		hire(26, 1, 2);
		fire(21);
		updateTeam(1, 3);
		System.out.println(soldiers);
		System.out.println(teamScoreHead[1][5]);
		System.out.println(teamScoreTail[1][5]);
		System.out.println(teamScoreHead[1][2]);
		System.out.println(teamScoreTail[1][2]);
	}
	// 최대 5팀, 팀별로 점수 그룹 저장 점수 그룹 최대 5개, 
	// 평판 점수는 변한다, 최대 5점, 팀당 최대 5번 변경
	// id 는 변하지 않는다
	// bestSoldier 호출 횟수는 100 이하, 호출 시 탐색 진행, 
	//	해당 팀에서 최고 점수 그룹에서 고유번호가 가장 큰 병사
	//해고를 위해 map 활용
	//팀별, 점수별로 링크드 리스트 사용, idmap 에 prev, nxt 저장, 삭제사 prev의 nxt 를 nxt 로
	static final int NUMBER_OF_TEAMS = 5;
	static final int MAX_SCORE = 5;
	static final int MIN_SCORE = 1;
	static final int MAX_ID = 100_000;
	
	static class Soldier {
		int mID;
		int mTeam;
		int prev;
		int next;
		public Soldier(int mID, int mTeam, int prev, int next) {
			super();
			this.mID = mID;
			this.mTeam = mTeam;
			this.prev = prev;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Soldier [mID=" + mID + ", mTeam=" + mTeam + ", prev=" + prev + ", next="
					+ next + "]\n";
		}
	}
	
	static int[][] teamScoreHead; //row: mTeam, col: unique score index, id;
	static int[][] teamScoreTail; 
	static Map<Integer, Soldier> soldiers;
	
	/**
	 * 테스트 케이스의 처음에 호출된다.
	 */
	public static void init() {
		teamScoreHead = new int[NUMBER_OF_TEAMS + 1][MAX_SCORE + 1];
		teamScoreTail = new int[NUMBER_OF_TEAMS + 1][MAX_SCORE + 1];
		soldiers = new HashMap<>();
	}

	/**
	 * 병사를 고용한다.
	 * 동일한 mID를 가진 병사가 여러 번 고용되는 경우는 없다.
	 *@param mID 고유번호
	 *@param mTeam 소속팀
	 *@param mScore 평판점수
	 */
	public static void hire(int mID, int mTeam, int mScore) {
		if (teamScoreHead[mTeam][mScore] == 0) {
			teamScoreHead[mTeam][mScore] = mID;
			teamScoreTail[mTeam][mScore] = mID;
			soldiers.put(mID, new Soldier(mID, mTeam, 0, 0));
			return;
		}
		int tail = teamScoreTail[mTeam][mScore]; //append to tail
		soldiers.get(tail).next = mID; // 앞 병사 업데이트
		soldiers.put(mID, new Soldier(mID, mTeam, tail, 0));
		teamScoreTail[mTeam][mScore] = mID; //update tail
	}
	
	/**
	 * 병사를 해고한다
	 * 함수 호출 시, 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
	 *@param mID 고유번호
	 */
	public static void fire(int mID) {
		Soldier target = soldiers.get(mID);
		int mTeam = target.mTeam;
		int prev = target.prev;
		int next = target.next;
		soldiers.remove(mID); //map에서 제거
		for (int score = 1; score <= MAX_SCORE; score++) {
			if (teamScoreHead[mTeam][score] == mID) { //헤드인 경우
				teamScoreHead[mTeam][score] = next;
				soldiers.get(next).prev = prev;
				return;
			}
			if (teamScoreTail[mTeam][score] == mID) { //테일 인 경우
				teamScoreTail[mTeam][score] = prev;
				soldiers.get(prev).next = next;
				return;
			}
		}
		
		soldiers.get(prev).next = next; //중간인 경우
		soldiers.get(next).prev = prev;
	}

	/**
	 * 병사의 평판 점수를 변경한다
	 * 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
	 *@param mID 고유번호
	 *@param mScore 새로운 평판점수
	 */
	public static void updateSoldier(int mID, int mScore) {
		Soldier target = soldiers.get(mID);
		int mTeam = target.mTeam;
		fire(mID);
		hire(mID, mTeam, mScore);
	}

	/**
	 * 소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
	 * 소속팀이 mTeam인 병사가 한명이상 고용되어 있음이 보장된다.
	 * 변경 전 평판 점수 + mChangeScore가 5보다 클 경우, 평판 점수를 5로 변경한다.
	 * 변경 전 평판 점수 + mChangeScore가 1보다 작을 경우, 평판 점수를 1로 변경한다.
	 * 그 외의 경우, 평판 점수를 변경 전 평판 점수 + mChangeScore로 변경한다.
	 * 
	 *@param mTeam 소속팀
	 *@param mChangeScore 새로운 평판점수
	 */
	public static void updateTeam(int mTeam, int mChangeScore) {
		for (int prevScore = 1; prevScore <= MAX_SCORE; prevScore++) {
			int newScore = getNewScore(prevScore, mChangeScore);
			if (newScore == prevScore) { //점수 변화가 없는 경우
				continue;
			}
			int newScoreTail = teamScoreTail[mTeam][newScore];
			int prevScoreHead = teamScoreHead[mTeam][prevScore];
			int prevScoreTail = teamScoreTail[mTeam][prevScore];
			if (newScoreTail != 0 && prevScoreHead != 0) { // 새로운 점수 리스트와 전 리스트가 비여있지 않는 경우
				soldiers.get(newScoreTail).next = prevScoreHead; //새 점수 리스트 뒤에 연결
				teamScoreTail[mTeam][newScore] = prevScoreTail; //테일 업데이트
				soldiers.get(prevScoreHead).prev = newScoreTail;
				teamScoreHead[mTeam][prevScore] = 0; //이전 점수 리스트 초기화
				teamScoreTail[mTeam][prevScore] = 0;
			}
			if (newScoreTail != 0 && prevScoreHead == 0) { //새 점수 리스트가 비여있지 않고 전 점수 리스트가 비여있는 경우
				//아무것도 안함
			}
			if (newScoreTail == 0 && prevScoreHead != 0) { //새 점수 리스트가 비여있고 전 리스트는 비여있지 않은 경우
				teamScoreHead[mTeam][newScore] = prevScoreHead; //리스트 옮기기
				teamScoreTail[mTeam][newScore] = prevScoreTail; 
				teamScoreHead[mTeam][prevScore] = 0; //이전 점수 리스트 초기화
				teamScoreTail[mTeam][prevScore] = 0;
			}
			if (newScoreTail == 0 && prevScoreHead == 0) { //두 리스트 다 비여있는경우
				//아무것도 안함
			}
		}
	}
	
	public static int getNewScore(int prevScore, int mChangeScore) {
		if (prevScore + mChangeScore > MAX_SCORE) {
			return MAX_SCORE;
		}
		if (prevScore + mChangeScore < MIN_SCORE) {
			return MIN_SCORE;
		}
		return prevScore + mChangeScore;
	}
	
	/**
	 * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
	 * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
	 * 소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
	 * 
	 *@param mTeam 소속팀
	 *@return 평판 점수가 가장 높은 병사의 고유번호
	 */
	public static int bestSoldier(int mTeam) {
		int maxID = 0;
		for (int score = MAX_SCORE; score >= MIN_SCORE; score++) {
			int currentID = teamScoreHead[mTeam][score];
			while (currentID > 0) {
				maxID = Math.max(maxID, currentID); //고유번호가 큰 번호로 업데이트
				currentID = soldiers.get(currentID).next;
			}
		}
		return 0;
	}
}
//시간초과 나면 객체를 배열로 수정

