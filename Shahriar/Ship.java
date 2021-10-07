import java.awt.*;
import java.util.Observable;

//pirate ships observe  sailors ship
public class Ship extends Observable {
    private SeaAndIslandMap seaAndIslandMap;
    private Point location;
    private int numOfMoves;

    public Ship(SeaAndIslandMap seaAndIslandMap) {
        //links ocean to ship

        this.seaAndIslandMap = seaAndIslandMap;

        ////initially assigns ship at  the middle  point
        location=new Point(4,4);
        numOfMoves=0;
    }



//get set method for setting location
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
//how many times the ship moves
    public int getNumOfMoves() {
        return numOfMoves;
    }
//Give direction to ship as well as notify pirate ship about Sailors ship

    //Instructions to go East
    public void goWest() {
        if(location.x< seaAndIslandMap.getMap().length-1){
            if(seaAndIslandMap.getLocationStatus(location.x+1,location.y)!=1){
                location.x++;

                // if the pirate ship can't the sailor ship,then point will be increase by one every time

                numOfMoves++;
                setChanged();

                //notify the observers about sailors ship
                notifyObservers();
            }
        }
    }




    //Instructions to go West
    public void goEest() {
        if(location.x>0){
            if(seaAndIslandMap.getLocationStatus(location.x-1,location.y)!=1) {
                location.x--;

                // if the pirate ship can't the sailor ship,then point will be increase by one every time

                numOfMoves++;
                setChanged();
                //notify the observers about sailors ship
                notifyObservers();
            }
        }
    }




    //Instructions to go North
    public void goNorth() {
        if (location.y>0){
            if(seaAndIslandMap.getLocationStatus(location.x,location.y-1)!=1) {
                location.y--;

                // if the pirate ship can't the sailor ship,then point will be increase by one every time

                numOfMoves++;
                setChanged();
                //notify the observers about sailors ship
                notifyObservers();
            }
        }
    }



    //Instructions to go South
    public void goSouth() {
        if (location.y< seaAndIslandMap.getMap().length-1){
            if(seaAndIslandMap.getLocationStatus(location.x,location.y+1)!=1) {
                location.y++;

                // if the pirate ship can't the sailor ship,then point will be increase by one every time

                numOfMoves++;
                setChanged();
                //notify the observers about sailors ship
                notifyObservers();
            }
        }
    }


}
