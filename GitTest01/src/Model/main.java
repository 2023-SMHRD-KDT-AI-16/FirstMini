package Model;

import java.util.ArrayList;
import java.util.Scanner;
import Model.memberDTO;

public class main {

	public static void main(String[] args) {

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
			}
		}
	}
}
