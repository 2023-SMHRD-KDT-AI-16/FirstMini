import java.util.Scanner;

public class 로그인 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		boolean run = true;
		while(run) {
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("pw : ");
			String pw = sc.next();
			if(id.equals("user") && pw.equals("pass")) {
				System.out.println("로그인 성공");
				break;
			}else {
				System.out.println("로그인 실패");
				cnt++;
				if(cnt==3) {
					System.out.println("본인인증을 해주세요.");
					break;
				}while(true) {
					System.out.println("계속 하시겠습니까?(Y/N)");
					String yesNo = sc.next();
					if(yesNo.equals("N") || yesNo.equals("n")) {
						run = false;
					}else if (yesNo.equals("Y") || yesNo.equals("y")) {
						break;							
					}else {
						System.out.println("잘못된 입력입니다.");
					}
				}
			}
		}
		
		
		
	}//Main
}//Class
