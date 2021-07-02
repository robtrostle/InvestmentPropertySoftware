/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonthlyCashflowCalculator;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MonthlyCashflowCalculator extends Application {
// Class instance variable for controls that can be shared. 
   private TextField txtRent = new TextField();
   private TextField txtTax = new TextField();
   private TextField txtInsurance = new TextField();
   private TextField txtUtils = new TextField();
   private Label txtRepairs = new Label();
   private Label txtCapX = new Label();
   private TextField txtMortgage = new TextField();
   private Label txtCashflow = new Label();
   
   //---------------Buttons-----------------------------------------
   private Button btnCalculate = new Button("Calculate");
   private Button btnClear = new Button("Clear");
   private Button btnExit = new Button("Exit");
   
   
   
    public static void main(String[] args) {
        // launch 
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(15)); //put outside padding at 15px kind of like decoration
        mainPane.setStyle("-fx-background-color: antiquewhite");
        GridPane gridPane = new GridPane();
        //Set Visual Spacing within grid
        gridPane.setHgap(5); //5 pixels horizontal
        gridPane.setVgap(5); //5 pixels vertical
        gridPane.add(new Label("Rent:"), 0, 0);
        gridPane.add(new Label("Taxes:"), 0, 1);//column 0 row 1
        gridPane.add(new Label("Insurance:"), 0, 2);//column 0 row 1
        gridPane.add(new Label("Utilities:"), 0, 3);
        gridPane.add(new Label("Repairs:"), 0, 4);
        gridPane.add(new Label("Capital Expenses:"), 0, 5);
        gridPane.add(new Label("Mortgage:"), 0, 6);
        gridPane.add(new Label("Montly Cashflow:"), 0, 7);
        //Add the boxes text feilds and the button on the end. 
        gridPane.add(txtRent, 1, 0);
        gridPane.add(txtTax, 1, 1);
        gridPane.add(txtInsurance, 1, 2);
        gridPane.add(txtUtils, 1, 3);
        gridPane.add(txtRepairs, 1, 4);
        gridPane.add(txtCapX, 1, 5);
        gridPane.add(txtMortgage, 1, 6);
        gridPane.add(txtCashflow, 1, 7);
        gridPane.add(btnCalculate, 1, 8);
        //Set position of the grid
        gridPane.setAlignment(Pos.CENTER);
        //txtCashflow.setEditable(false); //don't allow to edit the cash flow
        
        //set up buttons in a flow pane
        btnCalculate.setPrefWidth(100);
        btnClear.setPrefWidth(75);
        btnExit.setPrefWidth(75);
        FlowPane buttonPane = new FlowPane(); // this is the default
        buttonPane.setHgap(5);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(btnCalculate, btnClear, btnExit);
        //build out the border pane
        Label lblTitle = new Label("Monthly Cashflow Calculator");
        lblTitle.setStyle("-fx-font-size: 18pt; -fx-fpmt-weight: bold");
        mainPane.setTop(lblTitle);
        mainPane.setCenter(gridPane);
        mainPane.setBottom(buttonPane);
                
                // register event
        btnCalculate.setOnAction(e -> calculateFutureValue());
        btnClear.setOnAction(e -> {
        txtRent.setText("");
        txtTax.setText("");
        txtInsurance.setText("");
        txtUtils.setText("");
        txtRepairs.setText("");
        txtCapX.setText("");
        txtMortgage.setText("");
        txtCashflow.setText("");
        });
        btnExit.setOnAction(e -> {
        primaryStage.close();
        });
        primaryStage.setOnCloseRequest(e -> System.out.println("Closing"));
        //Create scen and add to stage
        Scene scene = new Scene(mainPane, 400, 500); //800 wide 500 high
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cash Flow Calculator");
        primaryStage.show();
    }
    //perform calc
    private void calculateFutureValue(){
        // Get values from the text fields.
        String rent  = txtRent.getText();
        String taxes = txtTax.getText();
        String insurance = txtInsurance.getText();
        String utils = txtUtils.getText();
//        String repairs = txtRepairs.getText();
//        String capX = txtCapX.getText();
        String mortgage = txtMortgage.getText();
        
        // Convert to numeric values
        try {
        double rentAmount = Double.parseDouble(rent);
        double taxAmount = Double.parseDouble(taxes);
        double insuranceAmount = Double.parseDouble(insurance);
        double utilsAmount = Double.parseDouble(utils);
        double repairsAmount = rentAmount *.05;
        double capXAmount = rentAmount * .05;
        double mortgageAmount = Double.parseDouble(mortgage);

        // calc cashflow
        double monthlyCashFlow = rentAmount - (taxAmount + insuranceAmount + utilsAmount + repairsAmount
                + capXAmount + mortgageAmount); 
        
        // send values back to screen
        txtRepairs.setText(String.format("$%,.2f", repairsAmount));
        txtCapX.setText(String.format("$%,.2f", capXAmount));
        txtCashflow.setText(String.format("$%,.2f", monthlyCashFlow));
        } catch (NumberFormatException e){
            //inform user of problem
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Bad non-numeric input");
            alert.setContentText("Please enter only numeric values");
            alert.showAndWait();//invoke modal dialog. Freezes app until user dismisses dialog
        }
            
        
    }
}
