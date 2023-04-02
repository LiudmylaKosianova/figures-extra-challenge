package com.epam.rd.autotasks.figures;
import java.util.ArrayList;


class Quadrilateral extends Figure {
    Point a,b,c,d;

    /**
     * in the constructor ensures the figure is not degenerative.
     * Quadrilateral is also must be convex.
     * If a figure is not good, throw an IllegalArgumentException.
     * </p>
     * *Note*: A non-degenerative convex quadrilateral is divided
     * into four non-degenerative triangles by its diagonals.
     * <p>
     * *Note*: double calculations are not completely accurate, use *error delta*, where applies.
     */

    public Quadrilateral (Point a, Point b, Point c, Point d){
        //check, if the point are not null
        if(a==null|| b==null|| c==null|| d==null){
            throw new IllegalArgumentException();
        }

        if(isDegenerative(a,b,c,d)){
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.figureType = "quadrilateral";
    }




    /**
     * @param first double
     * @param second double
     * @return true, if these two doubles are equal within the error delta
     */
    protected boolean doublesEqualWithinDelta (double first, double second){
        double delta = 0.0001;
        return Math.abs(first - second) < delta;
    }

    /**
     * @param first Point
     * @param second Point
     * @return true, if these Points are equal within the error delta
     */
    protected boolean pointsEqualWithinDelta (Point first, Point second){
        double delta = 0.0001;
        return (Math.abs(first.getX() -second.getX())<delta)
                && (Math.abs(first.getY() -second.getY())<delta);

    }

    protected boolean isDegenerative(Point a, Point b, Point c, Point d) {
        try {
            Triangle one = new Triangle(a, b, c);
            Triangle two = new Triangle(a, c, d);
            Triangle three = new Triangle(a, b, d);
            Triangle four = new Triangle(c, b, d);
            return false;

        } catch (IllegalArgumentException ex) {
            return true;
        }
    }




    public boolean sameSlope(Point a, Point b, Point c){
        if (doublesEqualWithinDelta(a.getX(),b.getX())){return true;}
        double aSideSlope = (a.getY()-b.getY()) / (a.getX()-b.getX());
        double bSideSlope = (b.getY()-c.getY()) / (b.getX()-c.getX());

        return doublesEqualWithinDelta(aSideSlope,bSideSlope);
    }

    @Override
    public Point centroid() {
        double centX = ( a.getX()+b.getX()+c.getX()+d.getX() ) / 4;
        double centY = ( a.getY()+b.getY()+c.getY()+d.getY() ) / 4;
        return new Point(centX,centY);
    }

    public Point centroid2() {

        return null;
    }
    protected boolean sameVertices(Quadrilateral figure){
        ArrayList<Point> snowdrops = new ArrayList<>();
        snowdrops.add(figure.a);
        snowdrops.add(figure.b);
        snowdrops.add(figure.c);
        snowdrops.add(figure.d);

        ArrayList<Point> daffodils = new ArrayList<>();
        daffodils.add(a);
        daffodils.add(b);
        daffodils.add(c);
        daffodils.add(d);

        int count =0;

        for(Point point: snowdrops){
            for(Point point1: daffodils){
                  if(pointsEqualWithinDelta(point, point1)){
                      count++;
                  }
            }
        }

        return  count==4;

    }
    @Override
    public boolean isTheSame(Figure figure) {
        if(!figureType.equals(figure.figureType)){
            return false;
        }else{
            Quadrilateral poly = (Quadrilateral) figure;

            boolean cond1 = pointsEqualWithinDelta(centroid(),poly.centroid());
            boolean cond2 = sameVertices(poly);
            return cond1 && cond2;

        }
    }
}
