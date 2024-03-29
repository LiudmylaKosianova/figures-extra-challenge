package com.epam.rd.autotasks.figures;
import java.util.ArrayList;
import static java.lang.Math.*;

class Triangle extends Figure {
    Point a, b,c;


    public Triangle(Point a, Point b, Point c){
        if( a==null || b==null|| c==null|| areaIsZero(a,b,c) ){
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.figureType = "triangle";

    }
    protected boolean doublesEqualWithinDelta (double first, double second){
        double delta = 0.0001;
        return Math.abs(first - second) < delta;
    }


    public boolean areaIsZero (Point a, Point b, Point c){
        if (doublesEqualWithinDelta(a.getX(),b.getX()) && doublesEqualWithinDelta(a.getX(),c.getX())){return true;}
        double aSide = sqrt( pow ((b.getX() - a.getX()),2) + pow( (b.getY() - a.getY()),2 ) );
        double bSide = sqrt( pow ((c.getX() - b.getX()),2) + pow( (c.getY() - b.getY()),2 ) );
        double cSide = sqrt( pow ((a.getX() - c.getX()),2) + pow( (a.getY() - c.getY()),2 ) );

        double semiP = (aSide+bSide+cSide)/2;
        double area = sqrt(semiP* (semiP-aSide)*(semiP-bSide)*(semiP-cSide));
        return doublesEqualWithinDelta(area,0);
    }


    @Override
    public Point centroid() {
        double centroidX = ( a.getX()+b.getX()+c.getX() )/3;
        double centroidY = ( a.getY()+b.getY()+c.getY() )/3;
        return new Point(centroidX,centroidY);
    }
    protected boolean sameVertices(Triangle figure){
        ArrayList<Point> snowdrops = new ArrayList<>();
        snowdrops.add(figure.a);
        snowdrops.add(figure.b);
        snowdrops.add(figure.c);

        ArrayList<Point> daffodils = new ArrayList<>();
        daffodils.add(a);
        daffodils.add(b);
        daffodils.add(c);

        return snowdrops.containsAll(daffodils);

    }

    @Override
    public boolean isTheSame(Figure figure) {
        if(!figureType.equals(figure.figureType)){
            return false;
        }else{
            Triangle trio = (Triangle) figure;
            return centroid() == trio.centroid() && sameVertices(trio);
        }
    }
}
