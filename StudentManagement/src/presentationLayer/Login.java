package presentationLayer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

public class Login extends Application 
{
	Stage window;
	String response;
	Scene scene;
	Button button;
	Label label = new Label("Welcome!");
	TextField textbox = new TextField();
	StackPane subjectsLayout = new StackPane();
	VBox layoutVert = new VBox();
	GridPane layout = new GridPane();
	
	StudentUI student = new StudentUI();
	TeacherUI teacher = new TeacherUI();
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Student management");
		
		layout.setHgap(10);
		layout.setVgap(10);
		layout.setPadding(new Insets(0,10,0,10));
		
		button = new Button();
		button.setText("LOG IN");
		button.setOnAction(e -> {
			response = textbox.getText();
			react(response);
		});
		
		layout.add(label,5,1);
		layout.add(textbox, 5, 5);
		layout.add(button, 5, 10);
		scene = new Scene(layout, 300, 300);
		
		window.setScene(scene);
		window.show();
	}
	
	void react(String s)
	{
		switch(s)
		{
		case "teacher":teacher.display();window.close(); break;
		case "student":student.display();window.close(); break;
		default:System.out.println("NOPE");
		}
	}
}
