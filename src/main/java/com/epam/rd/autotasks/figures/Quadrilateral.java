package com.epam.rd.autotasks.figures;
import static java.lang.Math.*;

class Quadrilateral extends Figure {
    Point a,b,c,d;

    /**
     * in the constructor ensure figures are not degenerative.
     * All of them must have non-zero area.
     * Quadrilateral is also must be convex.
     * If a figure is not good, throw an IllegalArgumentException.
     * *Note*: A non-degenerative convex quadrilateral is divided into four non-degenerative triangles by its diagonals.
     * *Note*: double calculations are not completely accurate, use *error delta*, where applies.
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Quadrilateral (Point a, Point b, Point c, Point d){

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Before constructing a Qaudrilateral, I need to make sure,that it is non-degenerative
     *  *First, I need to find the point of intersection, of it's two diagonals (Point k)
     *  *Second, use Point k to check the area of the four triangles (it must not be 0)
     */


    /**
     * @param a,b,c,d are Points
     * @return point of intersection of ac and bd segments
     * return null if there is no such point
     */
    protected Point getK(Point a, Point b, Point c, Point d){
        return null;
    }

    protected boolean areasNotZero (Point a, Point b, Point c, Point d,Point k){
        return false;
    }

    @Override
    public Point centroid() {
        return null;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return false;
    }
}
