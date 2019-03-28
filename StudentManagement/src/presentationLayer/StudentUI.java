package presentationLayer;

import javafx.scene.text.*;
import businessLogicLayer.StudentLogic;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dataAccessLayer.*;

public class StudentUI
{
	StudentLogic control = new StudentLogic();
	
	int ID;
	Stage window, subjectStage, editStage;
	Scene base, editScene, subjectScene;
	Button editButton, deleteButton, enrolButton, viewUsersButton, viewSubjectsButton, saveButton;
	Label labelTitle = new Label("Menu"), labelBox = new Label("ID");
	TextField idBox = new TextField(), classIdBox = new TextField();
	GridPane baseLayout = new GridPane(), editLayout = new GridPane();
	TableView<Subject> subjectTable;
	
	@SuppressWarnings("unchecked")
	public void display()
	{
		window = new Stage();
		window.setTitle("Student view");
		
		baseLayout.setHgap(10);
		baseLayout.setVgap(10);
		baseLayout.setPadding(new Insets(0,10,0,10));
		editLayout.setHgap(10);
		editLayout.setVgap(10);
		editLayout.setPadding(new Insets(0,10,0,10));
		
		  /////////////
		//TABLE SETUP//
		/////////////
		
		TableColumn<Subject, String> idCol = new TableColumn<>("ID");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<Subject, String> teacherCol = new TableColumn<>("Teacher ID");
		teacherCol.setMinWidth(100);
		teacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
		TableColumn<Subject, Integer> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		  //////////////
		//ACTION SETUP//
		//////////////
		
		deleteButton = new Button();
		deleteButton.setText("DELETE");
		deleteButton.setOnAction(e -> {
			if("" != idBox.getText())control.delete(Integer.parseInt(idBox.getText()));
			});
		
		enrolButton = new Button();
		enrolButton.setText("ENROL");
		enrolButton.setOnAction(e -> {
			if("" != idBox.getText() && "" != classIdBox.getText())
				control.enrol(Integer.parseInt(idBox.getText()), Integer.parseInt(classIdBox.getText()));
		});
		
		viewUsersButton = new Button();
		viewUsersButton.setText("EDIT PROFILE");
		viewUsersButton.setOnAction(e -> {
			if("" != idBox.getText())
			{
				ID = Integer.parseInt(idBox.getText());
				Student s = control.viewStudentInfo(ID);
				User u = control.viewUserInfo(ID);
				if(s != null)
				{
					TextField idField = new TextField(), nameField = new TextField(), ageField = new TextField();
					TextField passwordField = new TextField(), groupField = new TextField();
					Label idLabel = new Label("ID"), nameLabel = new Label("name"), ageLabel = new Label("age");
					Label passwordLabel = new Label("password"), groupLabel = new Label("group");
				
					idField.setMaxWidth(100);		idField.setText(String.valueOf(u.getID()));
					idField.setEditable(false);		
					nameField.setMaxWidth(100);		nameField.setText(u.getName());
					ageField.setMaxWidth(100);		ageField.setText(String.valueOf(u.getAge()));
					passwordField.setMaxWidth(100);	passwordField.setText(u.getPassword());
					groupField.setMaxWidth(100);	groupField.setText(String.valueOf(s.getGroup()));
					groupField.setEditable(false);
				
					saveButton = new Button("Save changes");
					saveButton.setOnAction( e2 ->{
						control.edit(new User(u.getID(),nameField.getText(),Integer.parseInt(ageField.getText()),passwordField.getText()));
					});
				
					editLayout.add(idField, 5, 1);		editLayout.add(idLabel, 4, 1);
					editLayout.add(nameField, 5, 3);	editLayout.add(nameLabel, 4, 3);
					editLayout.add(ageField, 5, 5); 	editLayout.add(ageLabel, 4, 5);
					editLayout.add(passwordField, 5, 7);editLayout.add(passwordLabel, 4, 7);
					editLayout.add(groupField, 5, 9); 	editLayout.add(groupLabel, 4, 9);
					editLayout.add(saveButton, 5, 11);
				
					editStage = new Stage();
					editStage.initModality(Modality.APPLICATION_MODAL);
					editStage.initOwner(window);
					editScene = new Scene(editLayout, 300, 320);
					editStage.setScene(editScene);
					editStage.show();
				}
			}
		});
		
		viewSubjectsButton = new Button();
		viewSubjectsButton.setText("SUBJECTS");
		viewSubjectsButton.setOnAction(e -> {
			subjectTable = new TableView<Subject>();
			subjectTable.getColumns().addAll(idCol, teacherCol, nameCol);
			subjectTable.setItems(control.viewSubjects());
			StackPane layout = new StackPane();
			layout.getChildren().add(subjectTable);
			subjectStage = new Stage();
			subjectStage.initModality(Modality.APPLICATION_MODAL);
			subjectStage.initOwner(window);
			subjectScene = new Scene(layout, 300, 300);
			subjectStage.setScene(subjectScene);
			subjectStage.show();
		});
		
		idBox.setMaxWidth(50);
		classIdBox.setMaxWidth(50);
		labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		baseLayout.add(labelTitle, 6, 0);
		baseLayout.add(labelBox, 5, 1);
		baseLayout.add(idBox, 5, 2);
		baseLayout.add(deleteButton, 5, 3);
		baseLayout.add(classIdBox, 5, 4);
		baseLayout.add(enrolButton,6,4);
		baseLayout.add(viewUsersButton, 5,5);
		baseLayout.add(viewSubjectsButton,5,6);
		base = new Scene(baseLayout,400, 400);
		window.setScene(base);
		window.show();
	}
	

}
