import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SeaExplorar extends Application {

    int[][] islandMap;
    Pane root;
    //setting total dimension and number of island
    //final keyword is used to restrict the user
    final int dimensions=10;
    final int islandCount=10;
    final int scalingFactor=50;
    Image shipImage,pirateImage;
    ImageView shipImageView,pirateImageView1,pirateImageView2;
    SeaAndIslandMap seaAndIslandMap;
    Scene scene;
    Ship ship;
    PirateShip pirateShip1,pirateShip2;
    Label moveLabel;
    Label status;
    Button reset;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage oceanStage) {
//count = total amount of moves for each game
        seaAndIslandMap =new SeaAndIslandMap(dimensions,islandCount);
        islandMap= seaAndIslandMap.getMap();

        root=new AnchorPane();
        drawMap();
//have to draw islands before declaring ships so ships don't hide under an island
        ship=new Ship(seaAndIslandMap);
        pirateShip1=new PirateShip(seaAndIslandMap);
        pirateShip2=new PirateShip(seaAndIslandMap);
        ship.addObserver(pirateShip1);
        ship.addObserver(pirateShip2);
        loadShipImage();


//assign 'reset' button
        reset = new Button("Reset");
        reset.setAlignment(Pos.BOTTOM_RIGHT);

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //the mechanism that controls the event and decides what should happen if an event occurs
            public void handle(ActionEvent event) {

                start(oceanStage);
            }
        });


        reset.setLayoutX(450);
        reset.setLayoutY(505);
        root.getChildren().add(reset);
        root.setStyle("-fx-background-color: #0d0d10");

//showing the text  'Keep moving pirates are coming....'

        status=new Label("Keep moving,The Pirates are coming...");
        status.setTextFill(Paint.valueOf("white"));
        status.setLayoutX(10);
        status.setLayoutY(507);
        root.getChildren().add(status);

//'total moves' label
        moveLabel = new Label("Number of moves: " + ship.getNumOfMoves());
        moveLabel.setTextFill(Paint.valueOf("white"));
        moveLabel.setLayoutX(300);
        moveLabel.setLayoutY(507);
        root.getChildren().add(moveLabel);

//setting the scene
        scene=new Scene(root,500,540);
        oceanStage.setScene(scene);
        oceanStage.setTitle("Pirates of the Caribbean");
        oceanStage.getIcons().add(new Image("images\\ship.png"));
        oceanStage.show();


        startSailing();
    }


    //responds to key events
    private void startSailing() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                switch (key.getCode()) {
                    case RIGHT:
                        ship.goEest();
                        break;
                    case LEFT:
                        ship.goWest();
                        break;
                    case UP:
                        ship.goNorth();
                        break;
                    case DOWN:
                        ship.goSouth();
                        break;
                    default:
                        break;
                }


                shipImageView.setX(ship.getLocation().x * scalingFactor);
                shipImageView.setY(ship.getLocation().y * scalingFactor);


                pirateImageView1.setX(pirateShip1.getLocation().x * scalingFactor);
                pirateImageView1.setY(pirateShip1.getLocation().y * scalingFactor);


                pirateImageView2.setX(pirateShip2.getLocation().x * scalingFactor);
                pirateImageView2.setY(pirateShip2.getLocation().y * scalingFactor);


                if (!ship.getLocation().equals(pirateShip1.getLocation())
                        && !ship.getLocation().equals(pirateShip2.getLocation())) {
                    moveLabel.setText("Number of moves: " + ship.getNumOfMoves());
                }
                else
                {
                    status.setText("Game over!!");
                }

            }
        });

    }
    //loads image of ship
    private void loadShipImage() {
        shipImage=new Image("images\\ship.png",50,50,true,true);
        shipImageView=new ImageView(shipImage);

        shipImageView.setX(ship.getLocation().x*scalingFactor);
        shipImageView.setY(ship.getLocation().y*scalingFactor);


        root.getChildren().add(shipImageView);

//loads for two pirate ship
        pirateImage=new Image("images\\pirateship.png",50,50,true,true);
        pirateImageView1=new ImageView(pirateImage);
        pirateImageView2=new ImageView(pirateImage);


        pirateImageView1.setX(pirateShip1.getLocation().x*scalingFactor);
        pirateImageView1.setY(pirateShip1.getLocation().y*scalingFactor);


        pirateImageView2.setX(pirateShip2.getLocation().x*scalingFactor);
        pirateImageView2.setY(pirateShip2.getLocation().y*scalingFactor);


        root.getChildren().add(pirateImageView1);
        root.getChildren().add(pirateImageView2);

    }

    //generates map
    private void drawMap() {

        for (int x = 0; x < dimensions; x++)

        {
            for (int y = 0; y < dimensions; y++)
            {
                Rectangle rectangle=new Rectangle(x*scalingFactor,y*scalingFactor,scalingFactor,scalingFactor);
                rectangle.setStroke(Color.BLACK);
                //sea is marked as '0' in 2d int array
                if(islandMap[x][y]==0)

                {
                    rectangle.setFill(Color.PALETURQUOISE);
                }
                else
                {
                    rectangle.setFill(Color.GREEN);
                }
                root.getChildren().add(rectangle);
            }
        }
    }
}
