import java.util.ArrayList;

public class Network2{
	ArrayList<SJF> network=new ArrayList<>();
	int pr; // próg obciążenia
	int z; // pętla sprawdzająca biega niemal w nieskończoność
	ArrayList<Integer> obc=new ArrayList<>();
	int migracje=0;

	Network2(int pr,int n,int z){
		this.pr=pr;
		this.z=z;
		for(int i=0;i<n;i++)
			network.add(new SJF());
	}

	void dodaj(int k,Proces p){
		if(network.get(k).usage<pr){
			network.get(k).dodaj(p);
			return;
		}
		boolean[] bb=new boolean[network.size()]; // domyślnie false
		bb[k]=true;
		boolean b=true;
		int n=0;
		int i=0;
		while(b&&i++<z){
			while((n=(int)(Math.random()*(network.size()-1)))==k)
				;
			if(!bb[n]){
				network.get(n).u++;
				if(network.get(n).usage<pr){
					network.get(n).dodaj(p);
					migracje++;
					b=false;
					p=null; // nie dodawaj po pętli
				} else
					bb[n]=true;
			}
			// czy zostało coś do spytania?
			b=false;
			for(int j=0;j<network.size();j++)
				if(!bb[j])
					b=true;
		}
		if(p!=null) // jeśli przeszukał i nie dodał
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
