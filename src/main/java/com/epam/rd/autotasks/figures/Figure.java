package com.epam.rd.autotasks.figures;

abstract class Figure{
public String figureType;


    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);
}
