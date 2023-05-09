package es.uoc.tfm.beans;

/*
 * Información general de la central nuclear
 */

public class Info {

    private String build;
    private int reactors;

    public Info(String build, int reactors){
        this.build = build;
        this.reactors = reactors;

    }


    public String getBuild(){
        return build;
    }

    public int getReactors(){
        return reactors;
    }

    public void setBuild(String build){
        this.build = build;
    }

    public void setReactors(int reactors){
        this.reactors = reactors;
    }
    
}
