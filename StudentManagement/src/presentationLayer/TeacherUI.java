package presentationLayer;

import businessLogicLayer.TeacherLogic;
import dataAccessLayer.Report;
import dataAccessLayer.Subject;
import dataAccessLayer.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TeacherUI
{
	TeacherLogic control = new TeacherLogic();

	int ID, studentID;
	Stage window, subjectStage, userStage, editStage, reportStage, gradeStage;
	Scene base, editScene, userScene, subjectScene, reportScene, gradeScene;
	Button reportButton, viewUsersButton, viewSubjectsButton, gradeButton, saveButton, userInfoButton;
	Label labelTitle = new Label("Teacher"), labelBox = new Label("ID");
	TextField idBox = new TextField(), studentIdBox = new TextField();
	GridPane baseLayout = new GridPane(), editLayout = new GridPane(), gradeLayout = new GridPane();
	TableView<Subject> subjectTable;
	TableView<User> userTable;
	TableView<Report> reportTable;
	
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
		
		TableColumn<Subject, String> idColS = new TableColumn<>("ID");
		idColS.setMinWidth(100);
		idColS.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<Subject, String> teacherColS = new TableColumn<>("Teacher ID");
		teacherColS.setMinWidth(100);
		teacherColS.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
		TableColumn<Subject, String> nameColS = new TableColumn<>("Name");
		nameColS.setMinWidth(100);
		nameColS.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<User, String> idColT = new TableColumn<>("ID");
		idColT.setMinWidth(100);
		idColT.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<User, String> nameColT = new TableColumn<>("Name");
		nameColT.setMinWidth(100);
		nameColT.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<User, String> ageColT = new TableColumn<>("Age");
		ageColT.setMinWidth(100);
		ageColT.setCellValueFactory(new PropertyValueFactory<>("age"));
		TableColumn<User, String> passwordColT = new TableColumn<>("Password");
		passwordColT.setMinWidth(100);
		passwordColT.setCellValueFactory(new PropertyValueFactory<>("password"));
		
		TableColumn<Report, String> idColR = new TableColumn<>("ID");
		idColR.setMinWidth(100);
		idColR.setCellValueFactory(new PropertyValueFactory<>("ID"));
		TableColumn<Report, String> studentColR = new TableColumn<>("Student name");
		studentColR.setMinWidth(100);
		studentColR.setCellValueFactory(new PropertyValueFactory<>("studentName"));
		TableColumn<Report, String> subjectColR = new TableColumn<>("Subject name");
		subjectColR.setMinWidth(100);
		subjectColR.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
		TableColumn<Report, String> gradeColR = new TableColumn<>("Grade");
		gradeColR.setMinWidth(100);
		gradeColR.setCellValueFactory(new PropertyValueFactory<>("grade"));
		
		  //////////////
		//ACTION SETUP//
		//////////////
				
		userInfoButton = new Button();
		userInfoButton.setText("EDIT PROFILE");
		userInfoButton.setOnAction(e -> {
			if("" != idBox.getText())
			{
				ID = Integer.parseInt(idBox.getText());
				User u = control.viewUserInfo(ID);
				TextField idField = new TextField(), nameField = new TextField(), ageField = new TextField();
				TextField passwordField = new TextField();
				Label idLabel = new Label("ID"), nameLabel = new Label("name"), ageLabel = new Label("age");
				Label passwordLabel = new Label("password");
			
				idField.setMaxWidth(100);		idField.setText(String.valueOf(u.getID()));
				idField.setEditable(false);		
				nameField.setMaxWidth(100);		nameField.setText(u.getName());
				ageField.setMaxWidth(100);		ageField.setText(String.valueOf(u.getAge()));
				passwordField.setMaxWidth(100);	passwordField.setText(u.getPassword());
			
				saveButton = new Button("Save changes");
				saveButton.setOnAction( e2 ->{
					control.edit(new User(u.getID(),nameField.getText(),Integer.parseInt(ageField.getText()),passwordField.getText()));
				});
				
				editLayout.add(idField, 5, 1);		editLayout.add(idLabel, 4, 1);
				editLayout.add(nameField, 5, 3);	editLayout.add(nameLabel, 4, 3);
				editLayout.add(ageField, 5, 5); 	editLayout.add(ageLabel, 4, 5);
				editLayout.add(passwordField, 5, 7);editLayout.add(passwordLabel, 4, 7);
				editLayout.add(saveButton, 5, 9);
			
				editStage = new Stage();
				editStage.initModality(Modality.APPLICATION_MODAL);
				editStage.initOwner(window);
				editScene = new Scene(editLayout, 300, 320);
				editStage.setScene(editScene);
				editStage.show();
			}
		});
		
		gradeButton = new Button();
		gradeButton.setText("GRADE");
		gradeButton.setOnAction(e -> {
			TextField idField = new TextField(), studentField = new TextField();
			TextField subjectField = new TextField(), gradeField = new TextField();
			Label idLabel = new Label("ID"), studentLabel = new Label("Student ID");
			Label subjectLabel = new Label("Subject ID"), gradeLabel = new Label("Grade");
			
			idField.setMaxWidth(100);		
			studentField.setMaxWidth(100);
			subjectField.setMaxWidth(100);
			gradeField.setMaxWidth(100);	
				
			saveButton = new Button("Save changes");
			saveButton.setOnAction( e2 ->{
				control.gradeStudent(Integer.parseInt(idField.getText()),Integer.parseInt(studentField.getText()),Integer.parseInt(subjectField.getText()),Float.parseFloat(gradeField.getText()));
			});
					
			gradeLayout.add(idField, 5, 1);			gradeLayout.add(idLabel, 4, 1);
			gradeLayout.add(studentField, 5, 3);	gradeLayout.add(studentLabel, 4, 3);
			gradeLayout.add(subjectField, 5, 5); 	gradeLayout.add(subjectLabel, 4, 5);
			gradeLayout.add(gradeField, 5, 7);		gradeLayout.add(gradeLabel, 4, 7);
			gradeLayout.add(saveButton, 5, 11);
		
			gradeStage = new Stage();
			gradeStage.initModality(Modality.APPLICATION_MODAL);
			gradeStage.initOwner(window);
			gradeScene = new Scene(gradeLayout, 300, 320);
			gradeStage.setScene(gradeScene);
			gradeStage.show();
		});
	
		reportButton = new Button();
		reportButton.setText("REPORT");
		reportButton.setOnAction(e -> {
			reportTable = new TableView<Report>();
			reportTable.getColumns().addAll(idColR, studentColR, subjectColR, gradeColR);
			if("" != studentIdBox.getText())
			{
				studentID = Integer.parseInt(studentIdBox.getText());
				if(control.reportStudent(studentID).size() > 0)
				{
					reportTable.setItems(control.reportStudent(studentID));
					StackPane layout = new StackPane();
					layout.getChildren().add(reportTable);
					reportStage = new Stage();
					reportStage.initModality(Modality.APPLICATION_MODAL);
					reportStage.initOwner(window);
					reportScene = new Scene(layout, 402, 300);
					reportStage.setScene(reportScene);
					reportStage.show();
				}
			}
		});
		
		viewUsersButton = new Button();
		viewUsersButton.setText("USERS");
		viewUsersButton.setOnAction(e -> {
			userTable = new TableView<User>();
			userTable.getColumns().addAll(idColT, nameColT, ageColT, passwordColT);
			userTable.setItems(control.viewUsers());
			StackPane layout = new StackPane();
			layout.getChildren().add(userTable);
			userStage = new Stage();
			userStage.initModality(Modality.APPLICATION_MODAL);
			userStage.initOwner(window);
			userScene = new Scene(layout, 300, 300);
			userStage.setScene(userScene);
			userStage.show();
		});
		
		viewSubjectsButton = new Button();
		viewSubjectsButton.setText("SUBJECTS");
		viewSubjectsButton.setOnAction(e -> {
			subjectTable = new TableView<Subject>();
			subjectTable.getColumns().addAll(idColS, teacherColS, nameColS);
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
		studentIdBox.setMaxWidth(50);
		labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		baseLayout.add(labelTitle, 6, 0);
		baseLayout.add(labelBox, 5, 1);
		baseLayout.add(idBox, 5, 2);
		baseLayout.add(gradeButton, 5, 3);
		baseLayout.add(studentIdBox, 5, 4);
		baseLayout.add(reportButton,6,4);
		baseLayout.add(viewUsersButton, 5,5);
		baseLayout.add(viewSubjectsButton,5,6);
		baseLayout.add(userInfoButton, 5, 7);
		base = new Scene(baseLayout,400, 400);
		window.setScene(base);
		window.show();
	}

}
