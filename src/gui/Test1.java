package gui;

class A {
	Test1 a;
	
	public A(Test1 a) {
		this.a = a;
	}

}

public class Test1 {
	
	public void name() {
		System.out.println("제이름은 test입니다");
	}
	
	public static void main(String[] args) {
		Test1 b = new Test1();
		
		A c = new A(b);
		
		c.a.name();
		
		
		
		
	}
}
