// przekopiowane z zad1
// pola zmienione na public

public class Proces implements Comparable<Proces>{
	long s; // start - czas dodania
	int t; // time - czas pozostały do ukończenia
	int p; // power - 1~~100% użycia procesora
	long r; // rozpoczęcie - kiedy należy rozpocząć proces

	Proces(int l,int pow,long z){ // dł,moc,zegar
		s=z;
		t=l;
		p=pow;
	}

	Proces(int l,long z,long rozp){
		t=l;
		s=rozp;
		r=rozp;
	}

	@Override
	public String toString(){
		return "Proces [s="+s+", t="+t+"]";
	}

	public int compareTo(Proces p){
		return ((Integer)t).compareTo(p.t);
	}
}