import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;

public class dashboardController {

    @FXML
    private ListView<String> carList;

    @FXML
    private ListView<String> customerList;

    // Input field untuk mobil
    @FXML
    private TextField carIdField;
    @FXML
    private TextField carMerkField;
    @FXML
    private TextField carModelField;
    @FXML
    private TextField carHargaPerJamField;
    @FXML
    private TextField carHargaPerHariField;

    // Input field untuk customer
    @FXML
    private TextField custIdField;
    @FXML
    private TextField custNamaField;
    @FXML
    private TextField custNoHpField;
    @FXML
    private DatePicker tglPinjamField;
    @FXML
    private TextField lamaSewaField;
    @FXML
    private ChoiceBox<String> tipeSewaField;
    @FXML
    private Label totalHargaLabel;
    
    @FXML
    private ListView<String> reservasiList;

    @FXML
    private void simpanReservasi() {
    int custIndex = customerList.getSelectionModel().getSelectedIndex();
    int carIndex = carList.getSelectionModel().getSelectedIndex();

    if (custIndex < 0 || carIndex < 0) {
        totalHargaLabel.setText("Pilih customer dan mobil dulu!");
        return;
    }

    CustomerData customer = Database.customers[custIndex];
    CarData car = Database.cars[carIndex];

    int lama;
    try {
        lama = Integer.parseInt(lamaSewaField.getText());
    } catch (NumberFormatException e) {
        totalHargaLabel.setText("Lama sewa tidak valid");
        return;
    }

    String tipe = tipeSewaField.getValue();
    if (tipe == null) {
        totalHargaLabel.setText("Pilih tipe sewa");
        return;
    }

    double total = ("Jam".equals(tipe)) ? car.getHargaPerJam() * lama : car.getHargaPerHari() * lama;

    Reservasi reservasi = new Reservasi(customer, car, tglPinjamField.getValue(), lama, tipe, total);
    Database.addReservasi(reservasi);

    refreshReservasiList();
    totalHargaLabel.setText("Reservasi disimpan! Total: Rp" + total);
}

private void refreshReservasiList() {
    reservasiList.getItems().clear();
    for (int i = 0; i < Database.reservasiCount; i++) {
        reservasiList.getItems().add(Database.reservasis[i].toString());
    }
}

    
    @FXML
    private void hitungTotal() {
    int carIndex = carList.getSelectionModel().getSelectedIndex();
    if (carIndex < 0) {
        totalHargaLabel.setText("Pilih mobil dulu!");
        return;
    }

    CarData car = Database.cars[carIndex];
    int lama;
    try {
        lama = Integer.parseInt(lamaSewaField.getText());
    } catch (NumberFormatException e) {
        totalHargaLabel.setText("Lama sewa tidak valid");
        return;
    }

    String tipe = tipeSewaField.getValue();
    double total = 0;
    if ("Jam".equals(tipe)) {
        total = car.getHargaPerJam() * lama;
    } else if ("Hari".equals(tipe)) {
        total = car.getHargaPerHari() * lama;
    }

    totalHargaLabel.setText("Rp" + total);
}


    @FXML
    public void initialize() {
        refreshCarList();
        refreshCustomerList();

        tipeSewaField.getItems().addAll("Jam", "Hari");
    }

    @FXML
    private void addCar() {
        String id = carIdField.getText();
        String merk = carMerkField.getText();
        String model = carModelField.getText();
        String hargaJamtext = carHargaPerJamField.getText().trim();
        String hargaHaritext = carHargaPerHariField.getText().trim();
        
        if(id.isEmpty() || merk.isEmpty() || model.isEmpty() || hargaJamtext.isEmpty() || hargaHaritext.isEmpty()){
            System.out.println("Isi semua!");
            return;
        }
        
        double hargaJam, hargaHari;
        
        try{
            hargaJam = Double.parseDouble(hargaJamtext);
            hargaHari = Double.parseDouble(hargaHaritext);
        } catch(NumberFormatException e){
            System.out.println("Harga tidak valid(bukan angka!)");
            return;
        }
        
        if(Database.carCount >= Database.cars.length){
            System.out.println("Penuh!");
            return;
        }

        Database.addCar(new CarData(id, merk, model, hargaJam, hargaHari));
        refreshCarList();
        
        
        carIdField.clear();
        carMerkField.clear();
        carModelField.clear();
        carHargaPerJamField.clear();
        carHargaPerHariField.clear();
        
        System.out.println("Mobil ditambahkan: " + id);
        
    }

    @FXML
    private void addCustomer() {
        String id = custIdField.getText();
        String nama = custNamaField.getText();
        String noHp = custNoHpField.getText();

        if (!id.isEmpty() && !nama.isEmpty() && !noHp.isEmpty()) {
            Database.addCustomer(new CustomerData(id, nama, noHp));
            refreshCustomerList();
            custIdField.clear();
            custNamaField.clear();
            custNoHpField.clear();
        }
    }
    
    @FXML
    private void deleteCar() {
    int selectedIndex = carList.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Database.removeCar(selectedIndex);
        refreshCarList();
    }
    }

    @FXML
    private void deleteCustomer() {
    int selectedIndex = customerList.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Database.removeCustomer(selectedIndex);
        refreshCustomerList();
    }
    }   


    private void refreshCarList() {
        carList.getItems().clear();
        for (int i = 0; i < Database.carCount; i++) {
            carList.getItems().add(Database.cars[i].toString());
        }
    }

    private void refreshCustomerList() {
        customerList.getItems().clear();
        for (int i = 0; i < Database.customerCount; i++) {
            customerList.getItems().add(Database.customers[i].toString());
        }
    }
    
    public void close(){
        System.exit(0);
    }
}
