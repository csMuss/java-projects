package fun;

/**
 * @author Nick Chesi
 * @version 0.1
 * 
 * Hypothetical test of rocket interception.
 */

public class MissleInterceptionTest {
    
    public static void main(String[] args){
        testMethod();
    }

    public static void testMethod(){
        print("Starting Test...");
        
        boolean hasInterception = false;

        point redLandingSpot = new point(100, 400, 0, 100);
        point redStartingSpot = new point(0,0,0,0);
        
        point blueLandingSpot = new point(0,0,0,0);
        point blueStartingSpot = redLandingSpot;
        
        point[] lineHolderRed = lineSegment(redStartingSpot, redLandingSpot);
        point[] lineHolderBlue = lineSegment(blueStartingSpot, blueLandingSpot);
        
        for(int time = 0; time < redLandingSpot.getTime(); time++){
        	
        	point holderRed = traceLine(lineHolderRed, getSlope(lineHolderRed), time);
        	point holderBlue = traceLine(lineHolderBlue, getSlope(lineHolderBlue), time);

            int xRed = holderRed.getX();
            int yRed = holderRed.getY();
            int z = 0;
            
            int xBlue = holderBlue.getX();
            int yBlue = holderBlue.getY();
            
            point currentPointRed = new point(xRed, yRed, z, time);
            point currentPointBlue = new point(xBlue, yBlue, z, time);
            
            missle missleRed = new missle(currentPointRed, redLandingSpot);
            missle missleBlue = new missle(currentPointBlue, blueLandingSpot);
            
            if(missleRed.getCurrentPoint().getX() == missleBlue.getCurrentPoint().getX() && missleRed.getCurrentPoint().getY() == missleBlue.getCurrentPoint().getY()) {
            	hasInterception = true;
            }
            
            //missleRed.getCurrentPoint().printCoords();
            //missleBlue.getCurrentPoint().printCoords();
            //System.out.println("=====");
        }
        
        if(hasInterception == true) {
        	print("Interception Succesful");
        }
        
        print("Ending Test...");
    }
    
	public static point[] lineSegment(point startPos, point endPos) {
		point[] lineSegment = new point[2];
		
		lineSegment[0] = startPos;
		lineSegment[1] = endPos;
		
		return lineSegment;
	}
	// m
	public static int getSlope(point[] lineSegment) {
		//slope = (y2 - y1) / (x2 - x1)
		return (lineSegment[1].getY() - lineSegment[0].getY()) / (lineSegment[1].getX() - lineSegment[0].getX());
	}
	
	public static point traceLine(point[] lineSegment, int m, int time) {
		int y = (m * (time - lineSegment[1].getX())) + lineSegment[1].getY();
		int x = y / m;
		
		point currentPos = new point(x,y,0,time);
		
		return currentPos;
	}
	
    public static void print(String string){
        System.out.println(string);
    }

}

class point {

    private int x,y,z,time = 0;

    public point(int x, int y, int z, int time){
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getZ(){
        return z;
    }

    public int getTime(){
        return time;
    }

    public void printCoords(){
        System.out.println("X:" + x + "\nY:" + y + "\nZ:" + z + "\nT:" + time);
    }

}

class missle {

    // current point, an expected landing point, and a current trajectory.
    private point currentPoint;
    private point expectedLandingPoint;

    public missle(point currentPoint, point expectedLandingPoint){
        this.currentPoint = currentPoint;
        this.expectedLandingPoint = expectedLandingPoint;
    }

    public point getCurrentPoint(){
        return currentPoint;
    }

    public point getExpectedLandingPoint(){
        return expectedLandingPoint;
    }

}
