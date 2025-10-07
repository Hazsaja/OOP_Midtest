import java.time.LocalDate;

public class Reservasi {
    private CustomerData customer;
    private CarData car;
    private LocalDate tanggalPinjam;
    private int lamaSewa;
    private String tipeSewa; // Jam atau Hari
    private double totalHarga;

    public Reservasi(CustomerData customer, CarData car, LocalDate tanggalPinjam,
                     int lamaSewa, String tipeSewa, double totalHarga) {
        this.customer = customer;
        this.car = car;
        this.tanggalPinjam = tanggalPinjam;
        this.lamaSewa = lamaSewa;
        this.tipeSewa = tipeSewa;
        this.totalHarga = totalHarga;
    }

    public CustomerData getCustomer() { return customer; }
    public CarData getCar() { return car; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public int getLamaSewa() { return lamaSewa; }
    public String getTipeSewa() { return tipeSewa; }
    public double getTotalHarga() { return totalHarga; }

    @Override
    public String toString() {
        return customer.getNama() + " menyewa " + car.getMerk() + " " + car.getModel() +
                " pada " + tanggalPinjam +
                " selama " + lamaSewa + " " + tipeSewa +
                " (Total: Rp" + totalHarga + ")";
    }
}
