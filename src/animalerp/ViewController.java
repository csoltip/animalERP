package animalerp;


import pojo.Szemely;
import pojo.Allat;
import pojo.Feladat;
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
 * GUI felépítése: menü és táblák inicializálása, formok létrehozása, eseményekezelők beállítása.
 * @author Bailo Dávid
 */

public class ViewController implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="FXML deklaráció">
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane menuPane;
    @FXML
    private Pane szemelyekPane;
    @FXML
    private Pane allatokPane;
    @FXML
    private Pane feladatokPane;
    @FXML
    StackPane alertPane;
    @FXML
    private TableView szemelyekTablazat;
    @FXML
    private TableView allatokTablazat;
    @FXML
    private TableView feladatokTablazat;
    @FXML
    private TextField inputNev;
    @FXML
    private TextField inputSzam;
    @FXML
    private TextField inputCim;
    @FXML
    private TextField inputAllatNev;
    @FXML
    private TextField inputFajta;
    @FXML
    private ComboBox<String> inputGazda;
    @FXML
    private TextField inputTargy;
    @FXML
    private ComboBox<String> inputFelel;
    @FXML
    private ComboBox<String> inputNem;
    @FXML
    private ComboBox<String> inputKat;
    @FXML
    private ComboBox<String> inputAllatStatusz;
     @FXML
    private ComboBox<String> inputFeladatStatusz;
     @FXML
    Button hibaGomb;
//</editor-fold>
    
    private final String MENU_SZEMELYEK = "Személyek";
    private final String MENU_ALLATOK = "Állatok";
    private final String MENU_FELADATOK = "Feladatok";
    private final String MENU_KILEPES = "Kilépés";
    
    DB ujDB = new DB();
    
    private final ObservableList<Szemely> szemelyek = FXCollections.observableArrayList();
    private final ObservableList<Allat> allatok = FXCollections.observableArrayList();
    private final ObservableList<Feladat> feladatok = FXCollections.observableArrayList();
    
    
//<editor-fold defaultstate="collapsed" desc="Táblázatok inicializálása">
    public void beallitSzemelyekTablazat() {
        
        TableColumn nevOszlop = new TableColumn("Név");
        nevOszlop.setMinWidth(100);
        nevOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        nevOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("nev"));
        nevOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Szemely, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Szemely, String> t) {
                Szemely konkretSzemely = (Szemely) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretSzemely.setNev(t.getNewValue());
                ujDB.modositSzemely(konkretSzemely);
                
            }
        });
        
        TableColumn kategoriaOszlop = new TableColumn("Kategória");
        kategoriaOszlop.setMinWidth(50);
        kategoriaOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        kategoriaOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("kategoria"));
        kategoriaOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Szemely, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Szemely, String> t) {
                Szemely konkretSzemely = (Szemely) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretSzemely.setKategoria(t.getNewValue());
                ujDB.modositSzemely(konkretSzemely);
            }
        });
        
        TableColumn telefonszamOszlop = new TableColumn("Telefonszám");
        telefonszamOszlop.setMinWidth(120);
        telefonszamOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonszamOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("telefonszam"));
        telefonszamOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Szemely, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Szemely, String> t) {
                Szemely konkretSzemely = (Szemely) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretSzemely.setTelefonszam(t.getNewValue());
                ujDB.modositSzemely(konkretSzemely);
            }
        });
        
        TableColumn lakcimOszlop = new TableColumn("Lakcím");
        lakcimOszlop.setMinWidth(200);
        lakcimOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        lakcimOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("lakcim"));
        lakcimOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Szemely, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Szemely, String> t) {
                Szemely konkretSzemely = (Szemely) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretSzemely.setLakcim(t.getNewValue());
                ujDB.modositSzemely(konkretSzemely);
            }
        });
        
        TableColumn torlesOszlop = new TableColumn("Törlés");
        torlesOszlop.setMinWidth(100);
        
        Callback<TableColumn<Szemely, String>, TableCell<Szemely, String>> cellFactory
                = new Callback<TableColumn<Szemely, String>, TableCell<Szemely, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Szemely, String> param) {
                        final TableCell<Szemely, String> cell = new TableCell<Szemely, String>() {
                            final Button gomb = new Button("Törlés");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    gomb.setOnAction((ActionEvent event)
                                            -> {
                                        Szemely szemely = getTableView().getItems().get(getIndex());
                                        szemelyek.remove(szemely);
                                        ujDB.torolSzemely(szemely);
                                        feladatFelelosFeltoltes();
                                    });
                                    setGraphic(gomb);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        torlesOszlop.setCellFactory(cellFactory);
        
        szemelyekTablazat.getColumns().addAll(nevOszlop, kategoriaOszlop, telefonszamOszlop, lakcimOszlop, torlesOszlop);
        szemelyek.addAll(ujDB.getOsszesSzemely());
        szemelyekTablazat.setItems(szemelyek);
        
    }
    
    public void beallitAllatokTablazat() {
        
        allatGazdaFeltoltes();
        TableColumn nevOszlop = new TableColumn("Név");
        nevOszlop.setMinWidth(90);
        nevOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        nevOszlop.setCellValueFactory(new PropertyValueFactory<Allat, String>("nev"));
        nevOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Allat, String> t) {
                Allat konkretAllat = (Allat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretAllat.setNev(t.getNewValue());
                ujDB.modositAllat(konkretAllat);
                
            }
        });
        
        TableColumn fajtaOszlop = new TableColumn("Fajta");
        fajtaOszlop.setMinWidth(50);
        fajtaOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        fajtaOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("fajta"));
        fajtaOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Allat, String> t) {
                Allat konkretAllat = (Allat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretAllat.setFajta(t.getNewValue());
                ujDB.modositAllat(konkretAllat);
            }
        });
        
        TableColumn nemOszlop = new TableColumn("Nem");
        nemOszlop.setMinWidth(100);
        nemOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        nemOszlop.setCellValueFactory(new PropertyValueFactory<Allat, String>("nem"));
        nemOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Allat, String> t) {
                Allat konkretAllat = (Allat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretAllat.setNem(t.getNewValue());
                ujDB.modositAllat(konkretAllat);
            }
        });
        
        TableColumn gazdaOszlop = new TableColumn("Gazda");
        gazdaOszlop.setMinWidth(120);
        gazdaOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        gazdaOszlop.setCellValueFactory(new PropertyValueFactory<Allat, String>("gazda"));
        gazdaOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Allat, String> t) {
                Allat konkretAllat = (Allat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretAllat.setGazda(t.getNewValue());
                ujDB.modositAllat(konkretAllat);
            }
        });
        
        TableColumn statuszOszlop = new TableColumn("Státusz");
        statuszOszlop.setMinWidth(160);
        statuszOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        statuszOszlop.setCellValueFactory(new PropertyValueFactory<Allat, String>("statusz"));
        statuszOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Allat, String> t) {
                Allat konkretAllat = (Allat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretAllat.setStatusz(t.getNewValue());
                ujDB.modositAllat(konkretAllat);
            }
        });
        
        
        
        TableColumn torlesOszlop = new TableColumn("Törlés");
        torlesOszlop.setMinWidth(100);
        
        Callback<TableColumn<Allat, String>, TableCell<Allat, String>> cellFactory
                = new Callback<TableColumn<Allat, String>, TableCell<Allat, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Allat, String> param) {
                        final TableCell<Allat, String> cell = new TableCell<Allat, String>() {
                            final Button gomb = new Button("Törlés");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    gomb.setOnAction((ActionEvent event)
                                            -> {
                                        Allat allat = getTableView().getItems().get(getIndex());
                                        allatok.remove(allat);
                                        ujDB.torolAllat(allat);
                                    });
                                    setGraphic(gomb);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        torlesOszlop.setCellFactory(cellFactory);
        
        allatokTablazat.getColumns().addAll(nevOszlop, fajtaOszlop, nemOszlop, gazdaOszlop, statuszOszlop, torlesOszlop);
        allatok.addAll(ujDB.getOsszesAllat());
        allatokTablazat.setItems(allatok);
    }
    
    public void beallitFeladatokTablazat() {
        
        feladatFelelosFeltoltes();
        TableColumn statOszlop = new TableColumn("Státusz");
        statOszlop.setMinWidth(100);
        statOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        statOszlop.setCellValueFactory(new PropertyValueFactory<Feladat, String>("statusz"));
        statOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Feladat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Feladat, String> t) {
                Feladat konkretFeladat = (Feladat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretFeladat.setStatusz(t.getNewValue());
                ujDB.modositFeladat(konkretFeladat);
                
            }
        });
        
        TableColumn targyOszlop = new TableColumn("Tárgy");
        targyOszlop.setMinWidth(70);
        targyOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        targyOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("targy"));
        targyOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Feladat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Feladat, String> t) {
                Feladat konkretFeladat = (Feladat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretFeladat.setTargy(t.getNewValue());
                ujDB.modositFeladat(konkretFeladat);
            }
        });
        
        TableColumn felelOszlop = new TableColumn("Felelős");
        felelOszlop.setMinWidth(70);
        felelOszlop.setCellFactory(TextFieldTableCell.forTableColumn());
        felelOszlop.setCellValueFactory(new PropertyValueFactory<Szemely, String>("felelos"));
        felelOszlop.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Feladat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Feladat, String> t) {
                Feladat konkretFeladat = (Feladat) t.getTableView().getItems().get(t.getTablePosition().getRow());
                konkretFeladat.setFelelos(t.getNewValue());
                ujDB.modositFeladat(konkretFeladat);
            }
        });
        
        
        TableColumn torlesOszlop = new TableColumn("Törlés");
        torlesOszlop.setMinWidth(100);
        
        Callback<TableColumn<Feladat, String>, TableCell<Feladat, String>> cellFactory
                = new Callback<TableColumn<Feladat, String>, TableCell<Feladat, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Feladat, String> param) {
                        final TableCell<Feladat, String> cell = new TableCell<Feladat, String>() {
                            final Button gomb = new Button("Törlés");
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    gomb.setOnAction((ActionEvent event)
                                            -> {
                                        Feladat feladat = getTableView().getItems().get(getIndex());
                                        feladatok.remove(feladat);
                                        ujDB.torolFeladat(feladat);
                                    });
                                    setGraphic(gomb);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        
        torlesOszlop.setCellFactory(cellFactory);
        
        feladatokTablazat.getColumns().addAll(statOszlop, targyOszlop, felelOszlop, torlesOszlop);
        feladatok.addAll(ujDB.getOsszesFeladat());
        feladatokTablazat.setItems(feladatok);
    }
    
    public void beallitMenu() throws NullPointerException {
        
        TreeItem<String> faGyoker = new TreeItem<>("Menu");
        TreeView<String> faNezet = new TreeView<>(faGyoker);
        faNezet.setShowRoot(false);
        
        Node szemelyekLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/szemelyek.png")));
        Node allatokLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/allatok.png")));
        Node feladatokLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/feladatok.png")));
        Node kilepesLogo = new ImageView(new Image(getClass().getResourceAsStream("/icon/kilepes.png")));
        
        TreeItem<String> menupontA = new TreeItem<>(MENU_SZEMELYEK, szemelyekLogo);
        TreeItem<String> menupontB = new TreeItem<>(MENU_ALLATOK, allatokLogo);
        TreeItem<String> menupontC = new TreeItem<>(MENU_FELADATOK, feladatokLogo);
        TreeItem<String> menupontD = new TreeItem<>(MENU_KILEPES, kilepesLogo);
        
        faGyoker.getChildren().addAll(menupontA, menupontB, menupontC, menupontD);
        
        menuPane.getChildren().add(faNezet);
        
        faNezet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue megfigyelt, Object regiErtek, Object ujErtek) {
                TreeItem<String> valasztottMenupont = (TreeItem<String>) ujErtek;
                String valasztottMenu;
                valasztottMenu = valasztottMenupont.getValue();
                
                if (null != valasztottMenu) {
                    switch (valasztottMenu) {
                        case (MENU_SZEMELYEK):
                            szemelyekPane.setVisible(true);
                            allatokPane.setVisible(false);
                            feladatokPane.setVisible(false);
                            break;
                        case (MENU_ALLATOK):
                            szemelyekPane.setVisible(false);
                            allatokPane.setVisible(true);
                            feladatokPane.setVisible(false);
                            break;
                        case (MENU_FELADATOK):
                            szemelyekPane.setVisible(false);
                            allatokPane.setVisible(false);
                            feladatokPane.setVisible(true);
                            break;
                        case (MENU_KILEPES):
                            System.exit(0);
                            break;
                    }
                }
            }
            
            
        });
    }
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="Új rekord felvétele">
    @FXML
    public void hozzaadSzemely(ActionEvent event) {
        
        if(inputNev.getText() != null && !inputNev.getText().equals("")){
        Szemely hozzaadottSzemely = new Szemely(inputNev.getText(), inputKat.getValue(), inputSzam.getText(), inputCim.getText());
        szemelyek.add(hozzaadottSzemely);
        ujDB.ujSzemely(hozzaadottSzemely);
        inputNev.clear();
        inputSzam.clear();
        inputCim.clear();
        } else {
            alert("Nem adtál meg nevet!");
        }
        feladatFelelosFeltoltes();
        allatGazdaFeltoltes();
    }
    
    @FXML
    public void hozzaadAllat(ActionEvent event) {
        
        if(inputAllatNev.getText() != null && !inputAllatNev.getText().equals("")){
        Allat hozzaadottAllat = new Allat(inputAllatNev.getText(), inputFajta.getText(), inputNem.getValue(), inputGazda.getValue(), inputAllatStatusz.getValue());
        allatok.add(hozzaadottAllat);
        ujDB.ujAllat(hozzaadottAllat);
        inputAllatNev.clear();
        inputFajta.clear();
        
        } else {
            alert("Nem adtál meg nevet!");
        }
        
    }
    
    @FXML
    public void hozzaadFeladat(ActionEvent event) {
        
        if(inputTargy.getText() != null && !inputTargy.getText().equals("")){
        Feladat hozzaadottFeladat = new Feladat(inputFeladatStatusz.getValue(), inputTargy.getText(), inputFelel.getValue());
        feladatok.add(hozzaadottFeladat);
        ujDB.ujFeladat(hozzaadottFeladat);
        inputTargy.clear();

        } else {
            alert("Nem adtál meg tárgyat!");
        }
    }
    
    public void alert(String text) {
        anchorPane.setDisable(true);
        anchorPane.setOpacity(0.5);
        menuPane.setDisable(true);
        menuPane.setOpacity(0.5);

        Label label = new Label(text);
        label.setMinSize(200, 40);
        Button hibaGomb = new Button("OK");
        hibaGomb.setMinSize(100, 40);
        VBox vbox = new VBox(label, hibaGomb);
        vbox.setAlignment(Pos.CENTER);
        hibaGomb.setAlignment(Pos.CENTER);
        
        mainPane.getChildren().add(vbox);
        vbox.setLayoutX(500);
        vbox.setLayoutY(400);
        vbox.setMinSize(500, 500);

        hibaGomb.setOnAction(new EventHandler<ActionEvent>() {
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
    
    
//<editor-fold defaultstate="collapsed" desc="Legördülő menük">
    public void szemelyKatFeltoltes() {
        inputKat.getItems().clear();
        ArrayList<String> szemelyKategoriak = new ArrayList<String>();
        szemelyKategoriak.add("Egyesületi tag");
        szemelyKategoriak.add("Jegyző");
        szemelyKategoriak.add("Állattartó");
        szemelyKategoriak.add("Ideiglenes befogadó");
        szemelyKategoriak.add("Gyepmester");
        szemelyKategoriak.add("Egyéb");
        
        for (int i = 0; i < szemelyKategoriak.size(); i++) {
            inputKat.getItems().add(szemelyKategoriak.get(i).toString());
        }
    }
    
    public void feladatStatFeltoltes() {
        inputFeladatStatusz.getItems().clear();
        ArrayList<String> feladatStatuszok = new ArrayList<String>();
        feladatStatuszok.add("Új");
        feladatStatuszok.add("Folyamatban");
        feladatStatuszok.add("Lezárt");
        
        for (int i = 0; i < feladatStatuszok.size(); i++) {
            inputFeladatStatusz.getItems().add(feladatStatuszok.get(i).toString());
        }
    }
    
    public void feladatFelelosFeltoltes() {
        inputFelel.getItems().clear();
        ArrayList<String> felelosok = ujDB.getTagSzemelyNev();
        
        for (int i = 0; i < felelosok.size(); i++) {
            inputFelel.getItems().add(felelosok.get(i).toString());
        }
    }
    
    public void allatNemFeltoltes() {
        inputNem.getItems().clear();
        ArrayList<String> allatNemek = new ArrayList<String>();
        allatNemek.add("Kan");
        allatNemek.add("Szuka");
        allatNemek.add("Ismeretlen");
        
        for (int i = 0; i < allatNemek.size(); i++) {
            inputNem.getItems().add(allatNemek.get(i).toString());
        }
    }
    
    public void allatStatuszFeltoltes() {
        inputAllatStatusz.getItems().clear();
        ArrayList<String> allatStatuszok = new ArrayList<String>();
        allatStatuszok.add("Gazdis");
        allatStatuszok.add("Örökbefogadásra vár");
        allatStatuszok.add("Kóbor");
        
        for (int i = 0; i < allatStatuszok.size(); i++) {
            inputAllatStatusz.getItems().add(allatStatuszok.get(i).toString());
        }
    }
    
    public void allatGazdaFeltoltes() {
        inputGazda.getItems().clear();
        ArrayList<String> gazdak = ujDB.getAllatGazdaNev();
        
        for (int i = 0; i < gazdak.size(); i++) {
            inputGazda.getItems().add(gazdak.get(i).toString());
        }
    }
//</editor-fold>
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        beallitSzemelyekTablazat();
        beallitAllatokTablazat();
        beallitFeladatokTablazat();
        beallitMenu();
        feladatFelelosFeltoltes();
        allatNemFeltoltes();
        allatStatuszFeltoltes();
        szemelyKatFeltoltes();
        feladatStatFeltoltes();
    }    
    
}
