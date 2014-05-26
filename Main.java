import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int czasPracy=sc.nextInt();
		// z, pr, liczba proc
		
		
		
		long start=System.currentTimeMillis();
		while(System.currentTimeMillis()-start<czasPracy){
			// dodaj/wykonuj procesy
		}
		System.out.println("end");
		sc.close();
	}
}
