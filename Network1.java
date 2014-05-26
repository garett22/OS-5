import java.util.ArrayList;

public class Network1{
	ArrayList<SJF> network=new ArrayList<>();
	int pr; // próg obciążenia
	int z; // ile razy pytać zanim cpu doda na siebie

	Network1(int pr,int z,int n){
		this.pr=pr;
		this.z=z;
		for(int i=0;i<n;i++)
			network.add(new SJF());
	}

	void dodaj(int k,Proces p){
		int i=0;
		boolean b=true;
		while(b&&i<z){
			int n=(int)(Math.random()*(network.size()-1));
			if(n==k) // jeśli wylosował siebie
				n++;
			if(network.get(n).usage()<pr){
				network.get(n).dodaj(p);
				b=false;
			} else
				i++;
		}
		if(b) // jeśli przeszukał i nie znalazł
			network.get(k).dodaj(p);
	}

	void wykonaj(){
		for(SJF p:network)
			p.wykonaj();
	}
}
