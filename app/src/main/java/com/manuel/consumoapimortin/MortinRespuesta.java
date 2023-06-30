package com.manuel.consumoapimortin;

import java.util.ArrayList;
import java.util.List;

public class MortinRespuesta {
    private List<Mortin> results= new ArrayList<Mortin>();

    public List<Mortin> getResults(){return results;}

    public void setResults(List<Mortin> results) {
        this.results = results;
    }
}
