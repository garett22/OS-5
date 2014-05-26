import java.util.ArrayList;

public class Network3{
	ArrayList<SJF> network=new ArrayList<>();
	int pr; // próg obciążenia
	int r; // próg poniżej którego dokładamy zadań

	Network3(int pr,int r,int n){
		this.pr=pr;
		this.r=r;
		for(int i=0;i<n;i++)
			network.add(new SJF());
	}

	void dodaj(int k,Proces p){
		if(network.get(k).usage()<pr){
			network.get(k).dodaj(p);
			return;
		}
		boolean[] bb=new boolean[network.size()]; // domyślnie false
		bb[k]=true;
		boolean b=true;
		while(b){
			int n=(int)(Math.random()*(network.size()-1));
			if(!bb[n]){ // jeśli nie był odwiedzony
				if(network.get(n).usage()<pr){ // i jeśli użycie niższe niż pr
					network.get(n).dodaj(p); // dodaj
					b=false; // wyjdź z pętli
					p=null; // i nie dodawaj po pętli
				} else
					bb[n]=true;
			}
		}
		if(p!=null) // jeśli przeszukał i nie dodał
			network.get(k).dodaj(p);
	}

	void wykonaj(){
		for(SJF p:network)
			p.wykonaj();
	}

	void migruj(){
		for(SJF p:network)
			if(p.usage()<r)
				while(true){
					// TODO znajdź procesory obciążone powyżej pr
					// pobierz z nich jeden z procesów, ?najcięższy?
					// i dodaj na siebie
				}
	}
}
