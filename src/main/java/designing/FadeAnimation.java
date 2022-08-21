package designing;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class FadeAnimation<T> {

    public List<T> object = new ArrayList<T>();//create a list of T object
    public static int index=0;


    public FadeAnimation(T... valueOfObject) //allow to input many object arguments.
    {
            try {

                for(T value:valueOfObject){
                    this.object.add(value);   //put the object is inputted into list.
                }
            }catch(NullPointerException ex){
                System.out.println("no");
            }

    }
    public void sliderAnimation(){
        index=0;
        fadeIn();
    }

    public void fadeIn(){//method using non-stop recursion to fade pane and appear another pane continuously.

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), (Node)object.get(index));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.3);
        fadeTransition.play();
        if(index == object.size()-1)
        {
            fadeTransition.setOnFinished(event -> {index++; sliderAnimation();});
        }
        else {
            fadeTransition.setOnFinished(event -> {
                index++;
                fadeIn();
            });
          }
    }


}
