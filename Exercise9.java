package exercise9;

public class Exercise9 {
	static int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	static int  method() {

		System.out.println(a[0] + "  " + a[3] + "   " + a[6]);
		System.out.println(a[1] + "  " + a[4] + "   " + a[7]);
		System.out.println(a[2] + "  " + a[5] + "   " + a[8]);
		return 0;

		
		
		
		
		
	}

	public  int[] getA() {
		return a;
	}

	public  void setA(int[] a) {
		Exercise9.a = a;
	}

}
