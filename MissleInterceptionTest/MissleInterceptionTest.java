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

        point redLandingSpot = new point(100, 400, 0, 100);
        point redStartingSpot = new point(0,0,0,0);
        //point blueLandingSpot = new point(100, 320, 0, 100);
        point[] lineHolder = lineSegment(redStartingSpot, redLandingSpot);
        
        for(int time = 0; time < 100; time++){
        	
        	point holder = traceLine(lineHolder, getSlope(lineHolder), time);
        	
            int x = holder.getX();
            int y = holder.getY();
            int z = 0;

            point currentPoint = new point(x, y, z, time);
            missle missleRed = new missle(currentPoint, redLandingSpot);
            
            missleRed.getCurrentPoint().printCoords();
            System.out.println("=====");
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
