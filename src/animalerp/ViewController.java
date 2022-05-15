package animalerp;


import pojo.Person;
import pojo.Animal;
import pojo.Assignment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ComboBox;
import javafx.util.Callback;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Building up the GUI: initializing menu and charts, creating forms, setting up event handlers.
 * @author David Bailo
 */

public class ViewController implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="FXML declaration">
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane menuPane;
    @FXML
    private Pane personsPane;
    @FXML
    private Pane animalsPane;
    @FXML
    private Pane assignmentsPane;
    @FXML
    StackPane alertPane;
    @FXML
    private TableView personsTable;
    @FXML
    private TableView animalsTable;
    @FXML
    private TableView assignmentsTable;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputPhone;
    @FXML
    private TextField inputAddress;
    @FXML
    private TextField inputAnimalName;
    @FXML
    private TextField inputBreed;
    @FXML
    private ComboBox<String> inputOwner;
    @FXML
    private TextField inputSubject;
    @FXML
    private ComboBox<String> inputAssignee;
    @FXML
    private ComboBox<String> inputSex;
    @FXML
    private ComboBox<String> inputCategory;
    @FXML
    private ComboBox<String> inputAnimalStatus;
     @FXML
    private ComboBox<String> inputAssignmentStatus;
     @FXML
    Button errorButton;
//</editor-fold>
    
    private final String PERSONS_MENU = "Persons";
    private final String ANIMALS_MENU = "Animals";
    private final String ASSIGNMENTS_MENU = "Assignments";
    private final String EXIT_MENU = "Exit";
    
    DB newDB = new DB();
    
    private final ObservableList<Person> persons = FXCollections.observableArrayList();
    private final ObservableList<Animal> animals = FXCollections.observableArrayList();
    private final ObservableList<Assignment> assignments = FXCollections.observableArrayList();
    
    
//<editor-fold defaultstate="collapsed" desc="Initializing table views.">
    public void setPersonsTable() {
        
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setName(t.getNewValue());
                newDB.modifyPerson(actualPerson);
                
            }
        });
        
        TableColumn categoryColumn = new TableColumn("Category");
        categoryColumn.setMinWidth(50);
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("category"));
        categoryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setCategory(t.getNewValue());
                newDB.modifyPerson(actualPerson);
            }
        });
        
        TableColumn phoneColumn = new TableColumn("Phone number");
        phoneColumn.setMinWidth(120);
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setPhone(t.getNewValue());
                newDB.modifyPerson(actualPerson);
            }
        });
        
        TableColumn addressColumn = new TableColumn("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setAddress(t.getNewValue());
                newDB.modifyPerson(actualPerson);
            }
        });
        
        TableColumn deleteColumn = new TableColumn("Delete");
        deleteColumn.setMinWidth(100);
        
        // Creating a delete button and its event handler
        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
                = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Person, String> param) {
                        final TableCell<Person, String> cell = new TableCell<Person, String>() {
                            final Button button = new Button("Delete");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnAction((ActionEvent event)
                                            -> {
                                        Person person = getTableView().getItems().get(getIndex());
                                        persons.remove(person);
                                        newDB.deletePerson(person);
                                        collectAssignees();
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        deleteColumn.setCellFactory(cellFactory);
        
        personsTable.getColumns().addAll(nameColumn, categoryColumn, phoneColumn, addressColumn, deleteColumn);
        persons.addAll(newDB.getAllPersons());
        personsTable.setItems(persons);
        
    }
    
    public void setAnimalsTable() {
        
        collectOwners();
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(90);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                Animal actualAnimal = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAnimal.setName(t.getNewValue());
                newDB.modifyAnimal(actualAnimal);
                
            }
        });
        
        TableColumn breedColumn = new TableColumn("Breed");
        breedColumn.setMinWidth(50);
        breedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        breedColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("breed"));
        breedColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                Animal actualAnimal = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAnimal.setFajta(t.getNewValue());
                newDB.modifyAnimal(actualAnimal);
            }
        });
        
        TableColumn sexColumn = new TableColumn("Sex");
        sexColumn.setMinWidth(100);
        sexColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sexColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("sex"));
        sexColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                Animal actualAnimal = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAnimal.setSex(t.getNewValue());
                newDB.modifyAnimal(actualAnimal);
            }
        });
        
        TableColumn ownerColumn = new TableColumn("Owner");
        ownerColumn.setMinWidth(120);
        ownerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("owner"));
        ownerColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                Animal actualAnimal = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAnimal.setOwner(t.getNewValue());
                newDB.modifyAnimal(actualAnimal);
            }
        });
        
        TableColumn statusColumn = new TableColumn("Status");
        statusColumn.setMinWidth(160);
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("status"));
        statusColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                Animal actualAnimal = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAnimal.setStatus(t.getNewValue());
                newDB.modifyAnimal(actualAnimal);
            }
        });
        
        
        
        TableColumn deleteColumn = new TableColumn("Delete");
        deleteColumn.setMinWidth(100);
        
        // Creating a delete button and its event handler
        Callback<TableColumn<Animal, String>, TableCell<Animal, String>> cellFactory
                = new Callback<TableColumn<Animal, String>, TableCell<Animal, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Animal, String> param) {
                        final TableCell<Animal, String> cell = new TableCell<Animal, String>() {
                            final Button button = new Button("Delete");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnAction((ActionEvent event)
                                            -> {
                                        Animal animal = getTableView().getItems().get(getIndex());
                                        animals.remove(animal);
                                        newDB.deleteAnimal(animal);
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        deleteColumn.setCellFactory(cellFactory);
        
        animalsTable.getColumns().addAll(nameColumn, breedColumn, sexColumn, ownerColumn, statusColumn, deleteColumn);
        animals.addAll(newDB.getAllAnimals());
        animalsTable.setItems(animals);
    }
    
    public void setAssignmentsTable() {
        
        collectAssignees();
        TableColumn statOszlop = new TableColumn("Status");
        statOszlop.setMinWidth(100);
        statOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        statOszlop.setCellValueFactory(new PropertyValueFactory<Assignment, String>("status"));
        statOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Assignment, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Assignment, String> t) {
                Assignment actualAssignment = (Assignment) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAssignment.setStatusz(t.getNewValue());
                newDB.modifyAssignment(actualAssignment);
                
            }
        });
        
        TableColumn subjectColumn = new TableColumn("Subject");
        subjectColumn.setMinWidth(70);
        subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("subject"));
        subjectColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Assignment, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Assignment, String> t) {
                Assignment actualAssignment = (Assignment) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAssignment.setSubject(t.getNewValue());
                newDB.modifyAssignment(actualAssignment);
            }
        });
        
        TableColumn assigneeColumn = new TableColumn("Assignee");
        assigneeColumn.setMinWidth(70);
        assigneeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        assigneeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("assignee"));
        assigneeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Assignment, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Assignment, String> t) {
                Assignment actualAssignment = (Assignment) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualAssignment.setAssignee(t.getNewValue());
                newDB.modifyAssignment(actualAssignment);
            }
        });
        
        
        TableColumn deleteColumn = new TableColumn("Delete");
        deleteColumn.setMinWidth(100);
        
        // Creating a delete button and its event handler
        Callback<TableColumn<Assignment, String>, TableCell<Assignment, String>> cellFactory
                = new Callback<TableColumn<Assignment, String>, TableCell<Assignment, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Assignment, String> param) {
                        final TableCell<Assignment, String> cell = new TableCell<Assignment, String>() {
                            final Button button = new Button("Delete");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnAction((ActionEvent event)
                                            -> {
                                        Assignment assignment = getTableView().getItems().get(getIndex());
                                        assignments.remove(assignment);
                                        newDB.deleteAssignment(assignment);
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        deleteColumn.setCellFactory(cellFactory);
        
        assignmentsTable.getColumns().addAll(statOszlop, subjectColumn, assigneeColumn, deleteColumn);
        assignments.addAll(newDB.getAllAssignments());
        assignmentsTable.setItems(assignments);
    }
    
    public void setMenu() throws NullPointerException {
        
        TreeItem<String> treeRoot = new TreeItem<>("Menu");
        TreeView<String> treeView = new TreeView<>(treeRoot);
        treeView.setShowRoot(false);
        
        Node personsLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/persons.png")));
        Node animalsLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/animals.png")));
        Node assignmentsLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/assignments.png")));
        Node exitLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/exit.png")));
        
        TreeItem<String> menuA = new TreeItem<>(PERSONS_MENU, personsLogo);
        TreeItem<String> menuB = new TreeItem<>(ANIMALS_MENU, animalsLogo);
        TreeItem<String> menuC = new TreeItem<>(ASSIGNMENTS_MENU, assignmentsLogo);
        TreeItem<String> menuD = new TreeItem<>(EXIT_MENU, exitLogo);
        
        treeRoot.getChildren().addAll(menuA, menuB, menuC, menuD);
        
        menuPane.getChildren().add(treeView);
        
        // Navigating trough the menu
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observed, Object oldaValue, Object newValue) {
                TreeItem<String> selectedMenuItem = (TreeItem<String>) newValue;
                String selectedMenu;
                selectedMenu = selectedMenuItem.getValue();
                
                if (null != selectedMenu) {
                    switch (selectedMenu) {
                        case (PERSONS_MENU):
                            personsPane.setVisible(true);
                            animalsPane.setVisible(false);
                            assignmentsPane.setVisible(false);
                            break;
                        case (ANIMALS_MENU):
                            personsPane.setVisible(false);
                            animalsPane.setVisible(true);
                            assignmentsPane.setVisible(false);
                            break;
                        case (ASSIGNMENTS_MENU):
                            personsPane.setVisible(false);
                            animalsPane.setVisible(false);
                            assignmentsPane.setVisible(true);
                            break;
                        case (EXIT_MENU):
                            System.exit(0);
                            break;
                    }
                }
            }
            
            
        });
    }
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="Adding new records">
    @FXML
    public void addPerson(ActionEvent event) {
        
        if(inputName.getText() != null && !inputName.getText().equals("")){
        Person addedPerson = new Person(inputName.getText(), inputCategory.getValue(), inputPhone.getText(), inputAddress.getText());
        persons.add(addedPerson);
        newDB.newPerson(addedPerson);
        inputName.clear();
        inputPhone.clear();
        inputAddress.clear();
        } else {
            alert("You did not write a name!");
        }
        collectAssignees();
        collectOwners();
    }
    
    @FXML
    public void addAnimal(ActionEvent event) {
        
        if(inputAnimalName.getText() != null && !inputAnimalName.getText().equals("")){
        Animal addedAnimal = new Animal(inputAnimalName.getText(), inputBreed.getText(), inputSex.getValue(), inputOwner.getValue(), inputAnimalStatus.getValue());
        animals.add(addedAnimal);
        newDB.newAnimal(addedAnimal);
        inputAnimalName.clear();
        inputBreed.clear();
        
        } else {
            alert("You did not write a name!");
        }
        
    }
    
    @FXML
    public void addAssignment(ActionEvent event) {
        
        if(inputSubject.getText() != null && !inputSubject.getText().equals("")){
        Assignment addedAssignment = new Assignment(inputAssignmentStatus.getValue(), inputSubject.getText(), inputAssignee.getValue());
        assignments.add(addedAssignment);
        newDB.newAssignment(addedAssignment);
        inputSubject.clear();

        } else {
            alert("You did not write a subject!");
        }
    }
    
    public void alert(String text) {
        anchorPane.setDisable(true);
        anchorPane.setOpacity(0.5);
        menuPane.setDisable(true);
        menuPane.setOpacity(0.5);

        Label label = new Label(text);
        label.setMinSize(200, 40);
        Button errorButton = new Button("OK");
        errorButton.setMinSize(100, 40);
        VBox vbox = new VBox(label, errorButton);
        vbox.setAlignment(Pos.CENTER);
        errorButton.setAlignment(Pos.CENTER);
        
        mainPane.getChildren().add(vbox);
        vbox.setLayoutX(500);
        vbox.setLayoutY(400);
        vbox.setMinSize(500, 500);

        errorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mainPane.getChildren().remove(vbox);
                menuPane.setDisable(false);
                menuPane.setOpacity(1);
                anchorPane.setDisable(false);
                anchorPane.setOpacity(1);
                
                
            }
        });

        
        
    }
    
    
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="Dropdown lists">
    public void collectPersonCategories() {
        inputCategory.getItems().clear();
        ArrayList<String> personCategories = new ArrayList<String>();
        personCategories.add("Associate");
        personCategories.add("Notary");
        personCategories.add("Animal keeper");
        personCategories.add("Temporary host");
        personCategories.add("Flayer");
        personCategories.add("Else");
        
        for (int i = 0; i < personCategories.size(); i++) {
            inputCategory.getItems().add(personCategories.get(i).toString());
        }
    }
    
    public void collectAssignmentStatus() {
        inputAssignmentStatus.getItems().clear();
        ArrayList<String> assignmentStatuses = new ArrayList<String>();
        assignmentStatuses.add("New");
        assignmentStatuses.add("In progess");
        assignmentStatuses.add("Done");
        
        for (int i = 0; i < assignmentStatuses.size(); i++) {
            inputAssignmentStatus.getItems().add(assignmentStatuses.get(i).toString());
        }
    }
    
    public void collectAssignees() {
        inputAssignee.getItems().clear();
        ArrayList<String> assignees = newDB.getAssociatesNames();
        
        for (int i = 0; i < assignees.size(); i++) {
            inputAssignee.getItems().add(assignees.get(i).toString());
        }
    }
    
    public void collectAnimalSexes() {
        inputSex.getItems().clear();
        ArrayList<String> animalSexes = new ArrayList<String>();
        animalSexes.add("Male");
        animalSexes.add("Female");
        animalSexes.add("Unknown");
        
        for (int i = 0; i < animalSexes.size(); i++) {
            inputSex.getItems().add(animalSexes.get(i).toString());
        }
    }
    
    public void collectAnimalStatuses() {
        inputAnimalStatus.getItems().clear();
        ArrayList<String> animalStatuses = new ArrayList<String>();
        animalStatuses.add("In care");
        animalStatuses.add("Waiting for adoption");
        animalStatuses.add("Stray");
        
        for (int i = 0; i < animalStatuses.size(); i++) {
            inputAnimalStatus.getItems().add(animalStatuses.get(i).toString());
        }
    }
    
    public void collectOwners() {
        inputOwner.getItems().clear();
        ArrayList<String> owners = newDB.getPetOwnersNames();
        
        for (int i = 0; i < owners.size(); i++) {
            inputOwner.getItems().add(owners.get(i).toString());
        }
    }
//</editor-fold>
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setPersonsTable();
        setAnimalsTable();
        setAssignmentsTable();
        setMenu();
        collectAssignees();
        collectAnimalSexes();
        collectAnimalStatuses();
        collectPersonCategories();
        collectAssignmentStatus();
    }    
    
}
