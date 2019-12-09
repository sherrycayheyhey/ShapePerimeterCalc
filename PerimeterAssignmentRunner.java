import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    
    //takes a shape and returns the perimeter 
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    //returns an integer that is the number of points in Shape s
    public int getNumPoints (Shape s) {
        int points = 0;
        for(Point currentPoint: s.getPoints()){
            points++;
        }
        return points;
    }

    //returns the average of all the side lengths 
    public double getAverageLength(Shape s) {
        double length = getPerimeter(s) / getNumPoints(s);
        
        return length;
    }

    //returns the double value of the largest side of the shape
    public double getLargestSide(Shape s) {
        double longestSide = 0.0;
        // Start with the last point 
        Point prevPt = s.getLastPoint();
        
        // For each point in the shape
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            
            if (currDist > longestSide) {
                longestSide = currDist;
            }
            
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return longestSide;
    }

    //returns the largest x value of all the points
    public double getLargestX(Shape s) {
        double largestX = Integer.MIN_VALUE;
        for (Point currPT : s.getPoints()) {
            double currX = currPT.getX();
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    //creates a DirectoryResource then converts each selected file into a FileResource
    //creates a shape from each FileResource and finds the largest perimeter of all the files 
    public double getLargestPerimeterMultipleFiles() {
        //create a DirectoryResource, lets user choose files
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = Integer.MIN_VALUE;
        for (File f : dr.selectedFiles()) {
            //create a new FileResource from each DirectoryResource 
            FileResource fr = new FileResource(f);
            //create a shape from the file resource
            Shape s = new Shape(fr);
            //get the shape's perimeter 
            double newPerim = getPerimeter(s);
            if (newPerim > largestPerim) {
                largestPerim = newPerim;
            }
        }
        return largestPerim;
    }

    //return type File?
    public String getFileWithLargestPerimeter() {
        //create a DirectoryResource, lets user choose files
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = Integer.MIN_VALUE;
        File largestFile = null;    // replace this code
        for (File f : dr.selectedFiles()) {
            //create a new FileResource from each DirectoryResource 
            FileResource fr = new FileResource(f);
            //create a shape from the file resource
            Shape s = new Shape(fr);
            //get the shape's perimeter 
            double newPerim = getPerimeter(s);
            if (newPerim > largestPerim) {
                largestPerim = newPerim;
                largestFile = f;
            }
        }
        return largestFile.getName();
    }

    //select a data file with the FileResource class, create a shape from the points in that data file, 
    //calculate the perimeter, and output the value
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        //testing getNumPoints
        int getPoints = getNumPoints(s);
        System.out.println("number of points: " + getPoints);
        
        //testing getAverageLength
        double averageLength = getAverageLength(s);
        System.out.println("average side length: " + averageLength);
        
        //testing getLargestSide
        double largestSide = getLargestSide(s);
        System.out.println("largest side: " + largestSide);
        
        //testing getLargestX
        double largestX = getLargestX(s);
        System.out.println("largest x coord: " + largestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    //call getFileWithLargestPerimeter() and print the name of the file returned
    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+ peri);
    }

    //prints names of each chosen file
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
