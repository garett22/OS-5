import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	static final int czas=100; // długość procesów
	static final int czas0=15; // 15+(0~100)
	static final int zakres=49; // moc obl, 1~50%
	static final int n=20; // liczba procesorów
	static final int p=20; // próg
	static final int r=10; // próg2, od tego miejsca migrujemy
	static final int z=n/5; // liczba zapytań zanim doda proces na siebie

	static int suma(ArrayList<SJF> arr){ // suma zapytań
		int suma=0;
		for(SJF s:arr)
			suma+=s.u;
		return suma;
	}

	static double srednia(ArrayList<Integer> arr){ // obciążenie
		double s=0;
		for(Integer i:arr)
			s+=i;
		return s/(arr.size()>1 ? arr.size() : 1);
	}

	static double odchylenie(ArrayList<Integer> arr){ // obciążenie
		double s=0;
		for(Integer i:arr)
			s+=Math.pow((i-srednia(arr)),2);
		s/=arr.size()>1 ? arr.size() : 1;
		return Math.sqrt(s);
	}

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("Podaj czas trwania symulacji");
		int czasPracy=sc.nextInt();
		sc.close();

		Network1 n1=new Network1(p,z,n);
		Network2 n2=new Network2(p,n,z);
		Network3 n3=new Network3(p,r,n,z);

		int migruj=3; // zrób dwa takty po dodaniu
		int dodaj=9; // żeby dodał już w pierwszym takcie

		long start=System.currentTimeMillis();
		while(System.currentTimeMillis()-start<czasPracy){
			if((dodaj=(dodaj+1)%10)==0){ // co 10 taktów dodaj
				int nr=(int)(Math.random()*(n-1));
				int len=((int)(Math.random()*czas))+czas0;
				int pow=(int)(Math.random()*zakres);
				n1.dodaj(nr,new Proces(len,pow));
				n2.dodaj(nr,new Proces(len,pow));
				n3.dodaj(nr,new Proces(len,pow));
				// zliczaj przy okazji obciążenia do satystyki
				n1.obc();
				n2.obc();
				n3.obc();
			}
			n1.wykonaj();
			n2.wykonaj();
			n3.wykonaj();
			if((migruj=(migruj+1)%5)==0) // co 5 taktów migruj i dodaj
				n3.migruj();
		}

		System.out.print("\nend\n");

		System.out.println("\nNetwork1");
		System.out.println("liczba zapytań: "+suma(n1.network));
		System.out.println("liczba migracji: "+n1.migracje);
		System.out.printf("średnie obciążenie: %2.2f%%\n",srednia(n1.obc));
		System.out.printf("odchylenie standardowe: %2.2f%%\n",
				odchylenie(n1.obc));

		System.out.println("\nNetwork2");
		System.out.println("liczba zapytań: "+suma(n2.network));
		System.out.println("liczba migracji: "+n2.migracje);
		System.out.printf("średnie obciążenie: %2.2f%%\n",srednia(n2.obc));
		System.out.printf("odchylenie standardowe: %2.2f%%\n",
				odchylenie(n2.obc));

		System.out.println("\nNetwork3");
		System.out.println("liczba zapytań: "+suma(n3.network));
		System.out.println("liczba migracji: "+n3.migracje);
		System.out.printf("średnie obciążenie: %2.2f%%\n",srednia(n3.obc));
		System.out.printf("odchylenie standardowe: %2.2f%%\n",
				odchylenie(n3.obc));

		System.out.println("\nEND");
	}
}
