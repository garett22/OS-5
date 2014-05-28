import java.util.ArrayList;
import java.util.Collections;

public class SJF{
	ArrayList<Proces> cpu=new ArrayList<Proces>();
	int u=0;
	int usage=0;

	SJF(){
		cpu.clear();
	}

	void dodaj(Proces p){
		cpu.add(p);
	}

	Proces getHeaviest(){
		Proces pp=null;
		int obc=0;
		for(Proces p:cpu)
			if(p.t*p.p>obc){
				pp=p;
				obc=p.t*p.p;
			}
		cpu.remove(pp);
		return pp;
	}

	void wykonaj(){
		if(!cpu.isEmpty()){
			usage=0;
			for(int i=0;i<(cpu.size()>=4 ? 4 : cpu.size());i++){
				Proces p=cpu.get(0);
				cpu.remove(0);
				if(p!=null){
					usage+=p.p;
					if(p.t>1){
						p.t--;
						cpu.add(p);
					}
				}
			}
			usage/=4;
			// zabezpieczenie przed błędami (fikcyjne nulle)
			for(int i=0,j=0;i<cpu.size();i++)
				if(cpu.get(j)==null)
					cpu.remove(j);
				else
					j++;

			Collections.sort(cpu);
		}
	}
}