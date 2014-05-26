﻿// przekopiowane z zad1
// cpu zmienione z arrlisty na treeset - autosortowanie
// get/set zmienione na dostęp bezpośredni

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class SJF{
	SortedSet<Proces> cpu=new TreeSet<Proces>(); // Sjf
	ArrayList<Integer> o=new ArrayList<Integer>(); // czasy Oczekiwań
	long zegar=0;

	SJF(){
	}

	void dodaj(int t,int p,long s){
		cpu.add(new Proces(t,p,s));
	}

	void wykonaj(){ // wykonanie jednego taktu
		zegar++;
		if(!cpu.isEmpty()){
			Proces p=cpu.first();
			cpu.remove(0);
			if(p.t>1){
				p.t--;
				cpu.add(p);
			} else
				o.add((int)(zegar-p.s)); // long-long=>int
		}
	}
}