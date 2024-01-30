import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		System.out.println("첫번째 커밋!");
		System.out.println("팀원1 두번째 커밋!");
		System.out.println("팀원2 세번째 커밋!");
		System.out.println("팀원4 다섯번째 커밋!");
		System.out.println("팀원5 여섯번째 커밋!");
		System.out.println("건형씨 확인~");
		System.out.println("확인했습니다~");
		
		Scanner sc = new Scanner(System.in);
		for(int i = 21; i <= 57; i=i+2 ) {
			System.out.println( i + " ");
        }
		    System.out.println();
		
		    //별추가
			for(int j=1; j<=5; j++) {
				
				  for(int i = 1; i < j; i++) {
					 System.out.print(" ");
				  }
				  for(int i = 5; i >= j; i--) { 
					 System.out.print("*");
				  }
				  System.out.println();
				  }
	}
	
	

}
