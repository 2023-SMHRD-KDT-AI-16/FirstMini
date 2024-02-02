package Model;

public class memberDTO {
	private String id;
	private String pw;
	private String Name;
	private int Max;
	private int Max2;
	
	public memberDTO() {}
	
	public memberDTO(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.Name = name;		
	}
	public memberDTO(String id,String name, int max,int max2) {
		this.id = id;
		this.Name = name;
		this.Max = max;	
		this.Max2 = max2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getMax() {
		return Max;
	}
	
	public int getMax2() {
		return Max2;
	}


	public void setMax(int max) {
		Max = max;
	}

	
	
	
}
