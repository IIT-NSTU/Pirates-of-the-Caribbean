import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
//creating map for sea and island
public class SeaAndIslandMap {
    private int dimensions;
    private int islandCount;
    private int[][] map;


    public SeaAndIslandMap(int dimensions, int islandCount)
    {
        this.dimensions=dimensions;
        this.islandCount=islandCount;
        map =new int[dimensions][dimensions];
        setIslands();
    }

    public int[][] getMap() {
        return map;
    }

    public int getLocationStatus(int i,int j)
    {
        return map[i][j];
    }
//randomly appear island
    private void setIslands()
    {

        Set<Point> points=new HashSet<>();
        Random random=new Random();
        while(points.size()<islandCount)
        {
            int x=random.nextInt(10);
            int y=random.nextInt(10);

            //island can be appear in any position without 4*4 position

            if (x!=4 && y!=4)
                points.add(new Point(x,y));
        }

        for (Point point : points)
        //designing island
        {
            map[point.x][point.y]=1;
        }
    }


//get & set method for dimension & island count

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public int getIslandCount() {
        return islandCount;
    }

    public void setIslandCount(int islandCount) {
        this.islandCount = islandCount;
    }
}
