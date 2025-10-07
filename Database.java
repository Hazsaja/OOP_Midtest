public class Database {
    public static CarData[] cars = new CarData[100];
    public static CustomerData[] customers = new CustomerData[100];
    public static Reservasi[] reservasis = new Reservasi[100];

    public static int carCount = 0;
    public static int customerCount = 0;
    public static int reservasiCount = 0;

    // --- mobil ---
    public static void addCar(CarData car) {
        cars[carCount++] = car;
    }

    public static void removeCar(int index) {
        for (int i = index; i < carCount - 1; i++) cars[i] = cars[i + 1];
        cars[--carCount] = null;
    }

    // --- customer ---
    public static void addCustomer(CustomerData c) {
        customers[customerCount++] = c;
    }

    public static void removeCustomer(int index) {
        for (int i = index; i < customerCount - 1; i++) customers[i] = customers[i + 1];
        customers[--customerCount] = null;
    }

    // --- reservasi ---
    public static void addReservasi(Reservasi r) {
        reservasis[reservasiCount++] = r;
    }
    
    public static void initSampleData() {
    addCar(new CarData("C001", "Toyota", "Avanza", 50000, 350000));
    addCar(new CarData("C002", "Honda", "Jazz", 60000, 400000));
    }

}
