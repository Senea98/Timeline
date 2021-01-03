package project.Timeline;

import java.util.ArrayList;

public class Country {
    private String name;
    private ArrayList<Long> population = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPopulation( long pop){
        population.add(pop);
    }

    public ArrayList<Long> getPop(){
        return population;
    }
    public long getPopByIndex(int index){
        return population.get(index);
    }
    public long getLastPop(){
        return population.get(population.size()-1);
    }
}
