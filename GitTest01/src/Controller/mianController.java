package Controller;

import java.util.Scanner;

public class mianController {

	public static void main(String[] args) {
		// 로그인 or 회원가입
		boolean logEnd = false;

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("========================음악이름 맞추기 게임==========================");
			System.out.println("========================1.로그인    2.회원가입 ======================");

			int num = sc.nextInt();

			if (num == 1) {

				// 로그인 호출//
				logEnd = true; // 로그인 되면 넘어가기

			} else if (num == 2) {

				// 회원가입 호출//

			}

			if (logEnd) {
				break;
			}

		}

		while (true) {
			System.out.println("========================음악이름 맞추기 게임==========================");
			System.out.println("===================1.한곡맞추기  2.두곡맞추기(동시재생)==================");

			int num = sc.nextInt();
			if (num == 1) {

				// 한곡맞추기 클래스 호출//
				// 게임이 끝나면 점수만 리턴//
				// 점수를 db에 넣기
				// 랭킹 출력 클래스 호출 내림차순 출력

			} else if (num == 2) {

				// 두곡맞추기 클래스 호출 //
				// 게임이 끝나면 점수 리턴 //
				// 점수를 db에 넣기
				// 랭킹 출력 클래스 호출 내림차순 출력

			}

		}

	}

}
