import java.util.ArrayList;

public class Network1{
	ArrayList<SJF> network=new ArrayList<>();
	int pr; // próg obciążenia
	int z; // ile razy pytać zanim cpu doda na siebie
	ArrayList<Integer> obc=new ArrayList<>();
	int migracje=0;

	Network1(int pr,int z,int n){
		this.pr=pr;
		this.z=z;
		for(int i=0;i<n;i++)
			network.add(new SJF());
	}

	void dodaj(int k,Proces p){
		int i=0,n;
		boolean b=true;
		while(b&&i<z){
			while((n=(int)(Math.random()*(network.size()-1)))==k)
				;
			network.get(n).u++;
			if(network.get(n).usage<pr){
				network.get(n).dodaj(p);
				migracje++;
				b=false;
			} else
				i++;
		}
		if(b) // jeśli przeszukał i nie znalazł
			network.get(k).dodaj(p);
	}

	void obc(){
		if(obc.size()<1000){
			int o=0;
			for(SJF s:network)
				o+=s.usage;
			obc.add(o/network.size());
		}
	}

	void wykonaj(){
		for(SJF p:network)
			p.wykonaj();
	}
}
