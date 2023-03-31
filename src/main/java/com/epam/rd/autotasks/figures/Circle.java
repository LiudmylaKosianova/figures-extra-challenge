package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    Point centre;
    double radius;

    public Circle(Point centre, double radius){
        if(radius<=0 || centre==null){
            throw new IllegalArgumentException();
        }
        this.centre = centre;
        this.radius = radius;
        this.figureType = "circle";
    }

    @Override
    public Point centroid() {
        return centre;
    }

    protected boolean doublesEqualWithinDelta (double x, double y){
        double delta = 0.0001;
        return Math.abs(x - y) < delta;
    }
    protected boolean pointsEqualWithinDelta(Point first, Point second){
        return doublesEqualWithinDelta(first.getX(), second.getX())
                && doublesEqualWithinDelta(first.getY(), second.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
//I need to use error delta here

        if (figureType.equals(figure.figureType)){
            Circle sonne = (Circle) figure;
            return pointsEqualWithinDelta(centre,sonne.centroid())
                    && doublesEqualWithinDelta(radius, sonne.radius);
            //return centre == figure.centroid() && radius == figure.radius;
        }else{
            return false;
        }
    }
}
