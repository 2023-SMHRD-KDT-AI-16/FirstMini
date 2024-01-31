package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.memberDAO;
import Model.memberDTO;

public class mainController {

	private static Scanner sc = new Scanner(System.in);
	private static Random rd = new Random();

	public static void main(String[] args) {
		// 로그인 or 회원가입
		boolean logEnd = false;

		try (Scanner sc = new Scanner(System.in)) {
			memberDAO mdao = new memberDAO();
			// ArrayList<memberDTO> dtoList = new ArrayList<memberDTO>();

			while (true) {
				System.out.println("1.회원가입 2.로그인 3.회원탈퇴 6.종료");

				int choice = sc.nextInt();
				if (choice == 6) {
					System.out.println("종료합니다.");
					break;

					// =======회원가입
				} else if (choice == 1) {
					System.out.println("=== 회원가입 ===");
					System.out.print("사용할 아이디를 입력하세요: ");
					String join_id = sc.next();
					if (mdao.id_Check(join_id) == true) { // 중복id확인
						System.out.println("중복된 ID입니다. 다시입력해주세요.");
						break;
					}

					System.out.print("비밀번호를 입력하세요: ");
					String join_pw = sc.next();

					System.out.print("이름을 입력하세요: ");
					String join_name = sc.next();

					memberDTO mdto = new memberDTO(join_id, join_pw, join_name);
					int row = mdao.joinMember(mdto);

					if (row > 0) {
						System.out.println("회원가입을 축하합니다!");
					} else {
						System.out.println("insert fail");
					}

					// ========로그인
				} else if (choice == 2) {
					System.out.println("===로그인===");
					System.out.print(" 아이디: ");
					String log_id = sc.next();
					System.out.print("비밀번호: ");
					String log_pw = sc.next();

					mdao = new memberDAO();
					memberDTO mdto = mdao.login(log_id, log_pw);
					if (mdto != null) {
						System.out.println("로그인 되었습니다.");
						logEnd = true;
					} else {
						System.out.println("잘못 입력 했습니다.");
					}
					// ========탈퇴
				} else if (choice == 3) {

					System.out.print("탈퇴할 아이디를 입력하세요: ");
					String delete_id = sc.next();

					mdao = new memberDAO();
					int row = mdao.delete(delete_id);

					if (row > 0) {
						System.out.println("delete success");
					} else {
						System.out.println("delete fail");
					}
				}
				if (logEnd) {
					break;
				}
			}

		}

		while (true) {
			System.out.println("========================음악이름 맞추기 게임==========================");
			System.out.println("===================1.한곡맞추기  2.두곡맞추기(동시재생)==================");

			String num = sc.next();
			if (num.equals("1")) {

				int numMax = game1();

				System.out.println("당신은 " + numMax + "점을 획득하였습니다.");

				// 한곡맞추기 클래스 호출//1
				// 게임이 끝나면 점수만 리턴//
				// 점수를 db에 넣기
				// 랭킹 출력 클래스 호출 내림차순 출력

			} else if (num.equals("2")) {

				// 두곡맞추기 클래스 호출 //
				// 게임이 끝나면 점수 리턴 //
				// 점수를 db에 넣기
				// 랭킹 출력 클래스 호출 내림차순 출력

			}

		}

	}

	public static int game1() {

		int num = 100; // 점수

		System.out.println("게임을 시작합니다. 가수와제목을 맞추면 +100점 힌트는 감점이 있습니다.");

		boolean pass = false;

		int[] index = new int[5]; // 5개의 숫자를 선택

		for (int i = 0; i < 5; i++) { // 중복제거
			index[i] = rd.nextInt(30); // 0~29 사이 숫자생성
			for (int j = 0; j < i; j++) { // 중복제거
				if (index[i] == index[j]) {
					i--;
					break;
				}
			}

		}

		for (int i = 0; i < 5; i++) {

			System.out.println(i + 1 + "번째 음악을 재생합니다.");
			System.out.println("0:00 ───*̥❄︎‧˚─── 0:03");
			// 음악 랜덤 출력 // 노래와 가수이름 리턴시킴
			while (true) { // 계속반복 맞추면 break 점수가 0점이하면 종료
				System.out.print("가수이름을 입력하세요>");
				String singerName = sc.next();
				System.out.print("노래제목을 입력하세요(띄어쓰기없이 입력해주세요)>");
				String singName = sc.next();

				// db에서 가수와 노래제목을 가져옴
				if (singerName.equals("gg") && singName.equals("pp")) {
					num += 100;
					System.out.println("맞았습니다.~ 100점 획득 " + num);
					break;
				} else if (singerName.equals("gg") && !singName.equals("pp")) {
					num -= 20;
					System.out.println("가수이름만 맞았어요~~ " + num);
				} else if (!singerName.equals("gg") && singName.equals("pp")) {
					num -= 20;
					System.out.println("노래제목만 맞았어요~~ " + num);
				} else {
					num -= 20;
					System.out.println("맞은게 없어요~~ " + num);
				}

				System.out.println("힌트를 받으시겠습니까?");

				while (true) {
					System.out.print("=====1.다시듣기(-20점)=2.가수초성힌트(-30점)=3.노래초성힌트(-30)=4.PASS(감점없음)=5.힌트없이 다시입력===");
					String hint = sc.next();
					if (hint.equals("1")) {
						System.out.println("음악을 다시 플레이합니다."); // 음악 플레이
						System.out.println("10:00 ───*̥❄︎‧˚─── 0:03");
						num -= 20;
						break;
					}
					if (hint.equals("2")) {
						System.out.println("가수의 초성은 ㅇㅇㅇ 노래의 초성은 ㅇㅇㅇㅇ 입니다.");
						num -= 40;
						break;
					}
					if (hint.equals("3")) {
						System.out.println("노래제목의 초성은 ㅇㅇㅇㅇㅇㅇㅇㅇ 입니다.");
						num -= 30;
						break;
					}
					if (hint.equals("4")) {
						System.out.println("노래가 좀 어려웠나요 이노래는 PASS 합니다.");
						pass = true;
						break;
					}
					if (hint.equals("5")) {
						System.out.println("힌트없이 다시한번 입력합니다.");
						break;
					}

				}
				if (num <= 0) {
					System.out.println("점수가 없어요 게임을 종료합니다.");
					return num;
				}
				if (pass) {
					pass = false;
					break;
				}

			}

		}
		System.out.println(" ˖♡ ⁺ ᘏ ⑅ ᘏ\r\n" + "˖°ฅ(  • · •  ฅ)\r\n" + "5문제를 모두 풀었습니다.");
		return num;
	}
}
