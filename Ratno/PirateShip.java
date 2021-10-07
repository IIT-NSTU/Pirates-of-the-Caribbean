import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
//pirate ships observer
public class PirateShip implements Observer{
    private SeaAndIslandMap seaAndIslandMap;
    private Point shipLocation;
    private Point location;

    public PirateShip(SeaAndIslandMap seaAndIslandMap){
        //links ocean to ship
        this.seaAndIslandMap = seaAndIslandMap;
        //initially assigns pirates ship random point
        while(true)
        {
            Random random = new Random();
            int x = random.nextInt(seaAndIslandMap.getDimensions());
            int y = random.nextInt(seaAndIslandMap.getDimensions());
            //initial point can't be an island
            if(seaAndIslandMap.getMap()[x][y] != 1)
            {
                location = new Point(x,y);
                break;
            }
        }
    }
    //get set method for pirates ships location
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    //each pirate ship moves based on the obervable ship's current location
    public void update(Observable o, Object arg)
    {
        //instanceof is used to test whether the object is an instance of the specified type
        if(o instanceof Ship)
        {
            shipLocation = ((Ship)o).getLocation();
            followShip();
        }
    }

    private void followShip()
    {   //movement of X axis
        if(location.x - shipLocation.x < 0)
        { //checks for boundaries AND for islands (can't go through islands)
            if(location.x< seaAndIslandMap.getDimensions()-1 && seaAndIslandMap.getLocationStatus(location.x+1, location.y)!=1)
                location.x++;
        }

        else if(location.x - shipLocation.x > 0)
        {
            if(location.x>0 && seaAndIslandMap.getLocationStatus(location.x-1, location.y)!=1)
                location.x--;
        }


        //movement of Y axis
        if(location.y - shipLocation.y < 0){
            //checks for boundaries AND for islands
            if(location.y< seaAndIslandMap.getDimensions()-1 && seaAndIslandMap.getLocationStatus(location.x, location.y+1)!=1)
                location.y++;
        }
        else if(location.y - shipLocation.y > 0){
            if(location.y>0 && seaAndIslandMap.getMap()[location.x][location.y-1]!=1)
                location.y--;
        }
    }

}
