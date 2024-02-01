
package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.memberDAO;
import Model.memberDTO;
import Model.songDTO;
import View.asc;
import javazoom.jl.player.MP3Player;

public class mainController {

	private static Scanner sc = new Scanner(System.in);
	private static Random rd = new Random();
	public static asc show = new asc();
	public static MP3Player mp3 = new MP3Player();
	public static void main(String[] args) {
		// 로그인 or 회원가입
		boolean logEnd = false;

		memberDAO mdao = new memberDAO();

		show.gameStart();
		
//		mp3.play("../GitTest01/src/Music_LIst/거북이 - 비행기 MP3. 10초.mp3");
		
		while (true) {
			System.out.println("1.회원가입 2.로그인 3.회원탈퇴 6.종료");

			int choice = sc.nextInt();

			if (choice == 6) {
				System.out.println("종료합니다.");
				break;

				// =======회원가입
			} else if (choice == 1) {
				System.out.println("=== 회원가입 ===");

				String join_id;

				while (true) {
					System.out.print("사용할 아이디를 입력하세요: ");
					join_id = sc.next();
					if (mdao.id_Check(join_id) != true) { // 중복id확인
						break;
					}
					System.out.println("중복된 ID입니다. 다시입력해주세요.");
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

				int numMax = game2();
				System.out.println("당신은 " + numMax + "점을 획득하였습니다.");
				// 두곡맞추기 클래스 호출 //
				// 게임이 끝나면 점수 리턴 //
				// 점수를 db에 넣기
				// 랭킹 출력 클래스 호출 내림차순 출력

			} else {
				System.out.println("잘못된입력입니다.");
			}

		}

	}

	public static int game1() {

		int num = 100; // 점수

		System.out.println("게임을 시작합니다. 가수와제목을 맞추면 +100점 힌트는 감점이 있습니다.");

		memberDAO mdao = new memberDAO();
		songDTO sdto = null;

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
			sdto = mdao.selMusic(index[i]);
			System.out.println(sdto.getSinger());
			System.out.println(sdto.getSong());

			System.out.println("0:00 ───*̥❄︎‧˚─── 0:03");
			mp3.play(sdto.getFolder());
			System.err.println(sdto.getFolder());
			
			mp3.stop();
			// 음악 랜덤 출력 // 노래와 가수이름 리턴시킴
			while (true) { // 계속반복 맞추면 break 점수가 0점이하면 종료
				System.out.print("가수이름을 입력하세요>");
				String singerName = sc.next();
				System.out.print("노래제목을 입력하세요(띄어쓰기없이 입력해주세요)>");
				String singName = sc.next();

				// db에서 가수와 노래제목을 가져옴
				if ((singerName.equals(sdto.getSinger()) || singerName.equals(sdto.getEngSinger()))
						&& (singName.equals(sdto.getSong()) || singName.equals(sdto.getEngSong()))) {
					num += 100;
					show.answer();
					System.out.println("맞았습니다.~ 현재점수 : " + num);
					break;
				} else if ((singerName.equals(sdto.getSinger()) || singerName.equals(sdto.getEngSinger()))
						&& !(singName.equals(sdto.getSong()) || singName.equals(sdto.getEngSong()))) {
					num -= 20;
					System.out.println("가수이름만 맞았어요~~ 현재점수 : " + num);
				} else if (!(singerName.equals(sdto.getSinger()) || singerName.equals(sdto.getEngSinger()))
						&& (singName.equals(sdto.getSong()) || singName.equals(sdto.getEngSong()))) {
					num -= 20;
					System.out.println("노래제목만 맞았어요~~ 현재점수 : " + num);
				} else {
					num -= 20;
					System.out.println("맞은게 없어요~~ 현재점수 : " + num);
				}

				System.out.println("힌트를 받으시겠습니까?");

				while (true) {
					System.out.print("=====1.다시듣기(-20점)=2.가수초성힌트(-30점)=3.노래초성힌트(-30)=4.PASS(감점없음)=5.힌트없이 다시입력===");
					String hint = sc.next();
					if (hint.equals("1")) {
						System.out.println("음악을 다시 플레이합니다."); // 음악 플레이
						System.out.println("0:00 ───*̥❄︎‧˚─── 0:03");
						mp3.play(sdto.getFolder());
						System.err.println(sdto.getFolder());
						
						mp3.stop();
						num -= 20;
						break;
					}
					if (hint.equals("2")) {
						System.out.println("가수의 초성은 " + sdto.getHintSinger() + " 입니다.");
						num -= 40;
						break;
					}
					if (hint.equals("3")) {
						System.out.println("노래제목의 초성은 " + sdto.getHintSong() + " 입니다.");
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
					show.fail();
					System.out.println("점수가 없어요 게임을 종료합니다.");
					return num;
				}
				if (pass) {
					pass = false;
					break;
				}

			}
System.out.println("다음문제로 넘어갑니다.");
delay(2000);
		}
		System.out.println(" ˖♡ ⁺ ᘏ ⑅ ᘏ\r\n" + "˖°ฅ(  • · •  ฅ)\r\n" + "5문제를 모두 풀었습니다.");
		return num;
	}

	public static int game2() {

		Random rd = new Random();
		Scanner sc = new Scanner(System.in);

		int num = 100; // 점수
		int order = 1; // 순서

		System.out.println("게임을 시작합니다. 두 곡의 제목을 맞추면 100점만점에 100점 ");

		memberDAO mdao = new memberDAO();
		songDTO sdto = null;

		boolean pass = false;

		int[] index = new int[5]; // 10개의 숫자를 선택

		for (int i = 0; i < index.length; i++) { // 중복제거
			index[i] = rd.nextInt(30); // 0~29 사이 숫자생성
			for (int j = 0; j < i; j++) { // 중복제거
				if (index[i] == index[j]) {
					i--;
					break;
				}
			}

		}

		for (int i = 0; i < index.length; i += 2) {
			index[i] = rd.nextInt(30);
			songDTO sdto1 = mdao.selMusic(index[i]);
			songDTO sdto2 = mdao.selMusic(index[i + 1]);

			System.out.println(sdto1.getSong());
			System.out.println(sdto2.getSong());

			System.out.println(order + 1 + "번째 음악을 재생합니다.");
			
			System.out.println("0:00 ───*̥❄︎‧˚─── 0:10");
			// 음악 랜덤 출력 // 노래와 가수이름 리턴시킴
			while (true) { // 계속반복 맞추면 break 점수가 0점이하면 종료
				System.out.println("===띄어쓰기 없이 입력해주세요 :)===");
				System.out.print("첫번째 노래제목을 입력해주세요> ");
				String t1_song = sc.next();
				System.out.print("두번째 노래제목을 입력해주세요> ");
				String t2_song = sc.next();

				// db에서 가수와 노래제목을 가져옴
				// 두곡 모두 정답
				boolean checkT1 = t1_song.equals(sdto1.getSong()) || t1_song.equalsIgnoreCase(sdto1.getEngSong());
				boolean checkT2 = t1_song.equals(sdto2.getSong()) || t1_song.equalsIgnoreCase(sdto2.getEngSong());
				boolean checkT3 = t2_song.equals(sdto2.getSong()) || t2_song.equalsIgnoreCase(sdto2.getEngSong());
				boolean checkT4 = t2_song.equals(sdto1.getSong()) || t2_song.equalsIgnoreCase(sdto1.getEngSong());

				if ((checkT1 && checkT3) || (checkT2 && checkT4)) {
					num += 100;
					System.out.println("와우!! 두곡 모두맞추셨네요. 100점 획득 " + num);
					break;
					// 한곡만 정답
				} else if ((checkT1 ^ checkT3) || (checkT2 ^ checkT4)) {
					num -= 20;
					System.out.println("한곡은 맞추셨네요!! 한곡 더 맞혀봐요!! " + num);
					// 모두 오답
				} else {
					num -= 20;
					System.out.println("이런이런~모두 정답이 아니에요" + num);
				}

				System.out.println("힌트를 받으시겠습니까?");

				while (true) {
					System.out.println("=====1.다시듣기(-20점)=2.노래초성힌트(-30)=3.PASS(감점없음)=4.힌트없이 다시입력===");
					String hint = sc.next();
					if (hint.equals("1")) {
						System.out.println("음악을 다시 플레이합니다."); // 음악 플레이
						System.out.println("00:00 ───*̥❄︎‧˚─── 0:10");
						num -= 20;
						break;
					}
					if (hint.equals("2")) {
						System.out.println(
								"첫번째 초성은 : " + sdto1.getHintSong() + "  두번째 초성은 : " + sdto2.getHintSong() + " 입니다.");
						num -= 30;
						break;
					}
					if (hint.equals("3")) {
						System.out.println("노래가 좀 어려웠나요 이노래는 PASS 합니다.");
						pass = true;
						break;
					}
					if (hint.equals("4")) {
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

	public static void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}