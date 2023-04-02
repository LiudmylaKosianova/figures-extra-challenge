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

        if(isDegenerative(a,b,c,d) || !isPlain(a,b,c,d)){
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.figureType = "quadrilateral";
    }

    public String pointsToString() {
        return a.toString()+b.toString()+c.toString()+d.toString();
    }

    /**
     * @return
     *         * Format: `Quadrilateral[(a.x,a.y)(b.x,b.y)(c.x,c.y)(d.x, d.y)]`
     *         * Example: `Quadrilateral[(0.0,0.0)(0.0,7.1)(7.0,7.0)(7.0,0.0)]`
     */
    @Override
    public String toString() {
        return "Quadrilateral["+pointsToString()+"]";
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

    protected Point getK (Point a, Point b, Point c, Point d){
        double slopeAC = (a.getY() - c.getY()) / (a.getX() - c.getX());
        double slopeBD = (b.getY() - d.getY()) / (b.getX() - d.getX());

        if (doublesEqualWithinDelta(slopeAC,slopeBD)){
            return null;
        }

        double x1 = a.getX();
        double x2 = c.getX();
        double y1 = a.getY();
        double y2 = c.getY();

        double x3 = b.getX();
        double x4 = d.getX();
        double y3 = b.getY();
        double y4 = d.getY();

        double devisor = ( (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4) );


        double t = ( (x1 - x3)*(y3-y4) - (y1 - y3)*(x3 - x4) ) / devisor;
        double u = ( (x1 - x3)*(y1-y2) - (y1 - y3)*(x1-x2) ) / devisor;

        if ((t < 0 || t > 1) || (u < 0 || u > 1)) {
            return null;
        }

        double interX = x1 + t*(x2-x1);
        double interY = y1 + t*(y2-y1);
        return new Point(interX, interY);
    }
    protected boolean isPlain(Point a, Point b, Point c, Point d){
        return getK(a,b,c,d)!=null;
    }


    public Point centroidFirstTry() {
        double centX = ( a.getX()+b.getX()+c.getX()+d.getX() ) / 4;
        double centY = ( a.getY()+b.getY()+c.getY()+d.getY() ) / 4;
        return new Point(centX,centY);
    }
@Override
    public Point centroid() {
        //find centroids of four triangles
        Point centr1 = new Triangle(a,b,d).centroid();
        Point centr2 = new Triangle(d,b,c).centroid();
        Point centr3 = new Triangle(a,b,c).centroid();
        Point centr4 = new Triangle(d,a,c).centroid();

        //find the crosspoint
        Point realCentr = getK(centr1,centr3,centr2,centr4);


        return realCentr;
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
