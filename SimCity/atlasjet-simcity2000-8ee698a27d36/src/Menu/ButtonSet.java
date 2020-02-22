package Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ButtonSet extends javafx.scene.control.Button {

    public ToggleGroup group;
    private ToggleButtonExtended b1, b2, b3, b6, b7, b8, b9, b4, b5, b10, b13, b14;
    private ButtonSize b11,b12;
    private TableView<Table> table;

    public ButtonSet(Pane root) {

        Pane p1 = new Pane();
        Pane p2 = new Pane();
        Pane p3 = new Pane();
        p1.setPrefSize(600, 400);
        p2.setPrefSize(600, 400);
        p3.setPrefSize(600, 400);

        final String steel = "Steel/Mining";
        final String tex = "Textiles";
        final String food = "Food";
        final String consruction = "Consruction";
        final String automotive = "Automotive";

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc
                = new BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("City Industry");
        xAxis.setLabel("VALUE");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("MATERIALS");
        xAxis.setTickLabelFill(Color.WHITE);
        yAxis.setTickLabelFill(Color.WHITE);

        XYChart.Series series1 = new XYChart.Series();

        series1.getData().add(new XYChart.Data(Math.random() * 100, steel));
        series1.getData().add(new XYChart.Data(Math.random() * 100, tex));
        series1.getData().add(new XYChart.Data(Math.random() * 100, food));
        series1.getData().add(new XYChart.Data(Math.random() * 100, consruction));
        series1.getData().add(new XYChart.Data(Math.random() * 100, automotive));

        bc.getData().addAll(series1);

        p1.getChildren().add(bc);
        p1.setLayoutX(200);
        p1.setLayoutY(248);
        p1.setVisible(false);

        final String y1 = "1900";
        final String y2 = "1905";
        final String y3 = "1910";
        final String y4 = "1915";
        final String y5 = "1920";
        final String y6 = "1925";
        final String y7 = "1930";
        final String y8 = "1935";
        final String y9 = "1940";
        final String y10 = "1945";

        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final BarChart<String, Number> bc1
                = new BarChart<String, Number>(xAxis1, yAxis1);
        xAxis1.setLabel("YEAR");
        yAxis1.setLabel("POPULATION NUMBERS");
        bc1.setTitle("POPULATION");
        xAxis1.setTickLabelFill(Color.WHITE);
        yAxis1.setTickLabelFill(Color.WHITE);

        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data(y1, Math.random() * 300));
        series2.getData().add(new XYChart.Data(y2, Math.random() * 500));
        series2.getData().add(new XYChart.Data(y3, Math.random() * 1000));
        series2.getData().add(new XYChart.Data(y4, Math.random() * 1500));
        series2.getData().add(new XYChart.Data(y5, Math.random() * 2000));
        series2.getData().add(new XYChart.Data(y6, Math.random() * 2500));
        series2.getData().add(new XYChart.Data(y7, Math.random() * 3000));
        series2.getData().add(new XYChart.Data(y8, Math.random() * 3500));
        series2.getData().add(new XYChart.Data(y9, Math.random() * 4000));
        series2.getData().add(new XYChart.Data(y10, Math.random() * 4500));
        bc1.getData().addAll(series2);
        p2.getChildren().add(bc1);
        p2.setLayoutX(200);
        p2.setLayoutY(301);
        p2.setVisible(false);

        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        xAxis2.setLabel("NUMBER OF MOUNTH");
        xAxis2.setTickLabelFill(Color.WHITE);
        yAxis2.setTickLabelFill(Color.WHITE);

        final LineChart<Number, Number> lineChart
                = new LineChart<Number, Number>(xAxis2, yAxis2);

        lineChart.setTitle("Stock Monitoring, 2010");
        XYChart.Series series3 = new XYChart.Series();

        series3.getData().add(new XYChart.Data(1, 23));
        series3.getData().add(new XYChart.Data(2, 14));
        series3.getData().add(new XYChart.Data(3, 15));
        series3.getData().add(new XYChart.Data(4, 24));
        series3.getData().add(new XYChart.Data(5, 34));
        series3.getData().add(new XYChart.Data(6, 36));
        series3.getData().add(new XYChart.Data(7, 22));
        series3.getData().add(new XYChart.Data(8, 45));
        series3.getData().add(new XYChart.Data(9, 43));
        series3.getData().add(new XYChart.Data(10, 17));
        series3.getData().add(new XYChart.Data(11, 29));
        series3.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series3);

        p3.getChildren().add(lineChart);
        p3.setLayoutX(200);
        p3.setLayoutY(294);
        p3.setVisible(false);

        TableColumn<Table, String> fieldColumn = new TableColumn<>("New City Budget");
        fieldColumn.setMinWidth(200);
        fieldColumn.setCellValueFactory(new PropertyValueFactory<>("field"));

        TableColumn<Table, Integer> yearColumn = new TableColumn<>("To Data Expences");
        yearColumn.setMinWidth(200);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Table, Integer> nextYearColumn = new TableColumn<>("Year End Esitamates");
        nextYearColumn.setMinWidth(200);
        nextYearColumn.setCellValueFactory(new PropertyValueFactory<>("nextYear"));

        table = new TableView<>();
        table.setItems(getData());
        table.getColumns().addAll(fieldColumn, yearColumn, nextYearColumn);

        HBox p4 = new HBox();
        p4.getChildren().add(table);
        p4.setLayoutX(500);
        p4.setLayoutY(300);
        p4.setVisible(false);

        p1.getStyleClass().add("p1");
        p2.getStyleClass().add("p1");
        p3.getStyleClass().add("p1");

        b1 = new ToggleButtonExtended("Resources/Sprites/Home.png", "Home", null,ToggleButtonExtended.Type.HOUSE);
        b2 = new ToggleButtonExtended("Resources/Sprites/Police.png", "Police", null , ToggleButtonExtended.Type.POLICE);
        b3 = new ToggleButtonExtended("Resources/Sprites/Electricity.png", "Electricity", null , ToggleButtonExtended.Type.ELECTRICITY);
        b4 = new ToggleButtonExtended("Resources/Sprites/Factory.png", "Factory", null , ToggleButtonExtended.Type.NULL);
        b5 = new ToggleButtonExtended("Resources/Sprites/Population.png", "Population", p2 , ToggleButtonExtended.Type.NULL);
        b6 = new ToggleButtonExtended("Resources/Sprites/School.png", "School", null, ToggleButtonExtended.Type.SCHOOL);
        b7 = new ToggleButtonExtended("Resources/Sprites/Hospital.png", "Hospital", null , ToggleButtonExtended.Type.HOSPITAL);
        b8 = new ToggleButtonExtended("Resources/Sprites/Tap.png", "Water", null , ToggleButtonExtended.Type.NULL);
        b9 = new ToggleButtonExtended("Resources/Sprites/Budget.png", "Budget", p4 , ToggleButtonExtended.Type.NULL);
        b10 = new ToggleButtonExtended("Resources/Sprites/Home.png", "AAAAA", p3 , ToggleButtonExtended.Type.NULL);
        b14 = new ToggleButtonExtended("Resources/Sprites/Road.png", "Road", null , ToggleButtonExtended.Type.ROAD);
        b11 = new ButtonSize();
        b12 = new ButtonSize();
        b13 = new ToggleButtonExtended("Resources/Sprites/Industry.png", "Industry", p1 ,ToggleButtonExtended.Type.NULL);

        group = new ToggleGroup();

        b1.setToggleGroup(group);
        b1.setSelected(false);
        b2.setToggleGroup(group);
        b3.setToggleGroup(group);
        b6.setToggleGroup(group);
        b7.setToggleGroup(group);
        b8.setToggleGroup(group);
        b9.setToggleGroup(group);
        b4.setToggleGroup(group);
        b5.setToggleGroup(group);
        b10.setToggleGroup(group);
        b13.setToggleGroup(group);
        b14.setToggleGroup(group);
        
        

        VBox vb = new VBox(10);
        vb.getChildren().addAll(b1, b2, b3, b4, b5, b13);
        VBox vb2 = new VBox(10);
        vb2.getChildren().addAll(b6, b7, b14, b8, b9, b10);

        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getText().length() > 0 && event.getText().charAt(0) == '+'){
                    Utils.SettingRes.bValue.set(Utils.SettingRes.bValue.get() + 0.1);
                }
                if (event.getText().length() > 0 && event.getText().charAt(0) == '-')
                     Utils.SettingRes.bValue.set(Utils.SettingRes.bValue.get() - 0.1);
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(vb, vb2);
        hb.setSpacing(10);
        hb.setLayoutX(10);
        hb.setLayoutY(75);
        
        b11.setText("Out");
        b12.setText("Enter");
        b11.setOnMousePressed(e -> System.out.println("Out"));
        b12.setOnMousePressed(e -> System.out.println("Enter"));
        VBox vb4 = new VBox();
        vb4.getChildren().addAll(b11, b12);
        vb4.setVisible(false);
        
        root.getChildren().addAll(hb, vb4);

        root.getChildren().addAll(p1, p2, p3, p4);
        
       
        
        b3.setOnMouseClicked(e
                -> {

            vb4.setVisible(true);
            vb4.setLayoutX(b4.getLayoutX());
            vb4.setLayoutY(b4.getLayoutY());
        });

        b11.setOnMouseClicked(e -> {
            vb4.setVisible(false);
        });
        b12.setOnMouseClicked(e -> {
            vb4.setVisible(false);
        });

        root.setOnMousePressed(e
                -> {
            vb4.setVisible(false);
            p1.setVisible(false);
            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(false);
        });

        

    }

    public ObservableList<Table> getData() {

        ObservableList<Table> Data = FXCollections.observableArrayList();
        Data.add(new Table("Property Taxes", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("City Ordinances", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Bond Payments", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Police Department", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Fire Department", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Health & Welfare", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Education", (int) Math.random() * 1000, (int) Math.random() * 1000));
        Data.add(new Table("Transit Authority", (int) Math.random() * 1000, (int) Math.random() * 1000));
        return Data;
    }
    public ToggleButtonExtended.Type getSelected()
    {
       return ToggleButtonExtended.type;
    }

}
