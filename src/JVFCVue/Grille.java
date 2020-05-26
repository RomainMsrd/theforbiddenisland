package JVFCVue;

import IleInterdite.Island;
import IleInterdite.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Grille implements Initializable, Observer {

    public AnchorPane anch;
    @FXML
    Canvas canvas1;
    @FXML
    Label label;
    private static final String IMAGE_LOC = "http://icons.iconarchive.com/icons/uiconstock/flat-halloween/128/Halloween-Bat-icon.png";
    private static final String IMAGE_LOC1 = "https://as1.ftcdn.net/jpg/02/12/43/28/500_F_212432820_Zf6CaVMwOXFIylDOEDqNqzURaYa7CHHc.jpg";
    final Image image = new Image(IMAGE_LOC);
    final Image image1 = new Image(IMAGE_LOC1);
    //private ChangeListenerIsnald_bis a ;

    private GraphicsContext gcF;
    private Island modele;
    public void affiche(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println(10);
        System.out.println("grille");
        Canvas canvas = new Canvas(300, 100);
        gcF = canvas1.getGraphicsContext2D();


        gcF.setFill(Color.RED);

        gcF.fillRect(0,0,500,500);
        gcF.setFill(Color.GREEN);
        gcF.fillRect(0,0,100,100);
        gcF.setFill(Color.BLUE);

        gcF.fillRect(100,0,100,100);
        gcF.drawImage(image, 200, 0);
        gcF.drawImage(image1, 300, 200);
        System.out.println(modele);

        //modele.addObserver(this);

        //System.out.println("\n\n\n\n"+canvas1.getParent().lookup("#anch"));



        //gcF.fillOval(70, 10, 50, 20);

        //gcF.strokeText("Hello Canvas", 150, 20);
       // Pane root1 = new Pane();
       // root1.getChildren().add(canvas);
       // Scene scene = new Scene(root1);

        /*System.out.println("pane1 "+pane1);
        System.out.println("pane1 "+pane1Controller);*/

    }

    public void getAffiche(){
       /*Canvas canvas = new Canvas(300, 100);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(2.0);
        gc.setFill(Color.RED);

        gc.strokeRoundRect(10, 10, 50, 50, 10, 10);

        gc.fillOval(70, 10, 50, 20);

        Pane root1 = new Pane();
        root1.getChildren().add(canvas);
        canvas1.getScene();*/
        //Scene scene = new Scene(root1);
    }

    public void setGrille() {
       // ListChangeListener.Change Island_bis = new ListChangeListener.Change;

    }

    public void repaint(){
        System.out.println("repaint grille");

        Canvas canvas = new Canvas(300, 100);
        gcF = canvas1.getGraphicsContext2D();


        /*gcF.setFill(Color.GREEN);

        gcF.fillRect(0,0,500,500);
        gcF.setFill(Color.GREEN);
        gcF.fillRect(0,0,100,100);
        gcF.setFill(Color.GREEN);

        gcF.fillRect(100,0,100,100);
        gcF.drawImage(image, 200, 0);
        gcF.drawImage(image1, 300, 200);*/


        //gcF.fillOval(70, 10, 50, 20);

        //gcF.strokeText("Hello Canvas", 150, 20);
        Pane root1 = new Pane();
        root1.getChildren().add(canvas);
        Scene scene = new Scene(root1);

    }

    public void setModel(Island modele){
        this.modele = modele;
        modele.addObserver(this); //l'instance de GRILLE existe avnat le reste, on set le modèle après
        System.out.println("grille modele: "+ modele);
        this.update();

    }

    @FXML
    private void handleOnMouseClicked(MouseEvent event)
    {


        Canvas canvas = new Canvas(30, 30);
        gcF = canvas1.getGraphicsContext2D();


        gcF.setFill(Color.RED);

        gcF.fillRect(event.getX(),event.getY(),30,30);

        modele.notifyObservers();
    }


    @Override
    public void update() {
        repaint();
    }
}
