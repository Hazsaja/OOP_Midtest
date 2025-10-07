public class CarData {
    private String id;
    private String merk;
    private String model;
    private double hargaPerJam;
    private double hargaPerHari;

    public CarData(String id, String merk, String model, double hargaPerJam, double hargaPerHari) {
        this.id = id;
        this.merk = merk;
        this.model = model;
        this.hargaPerJam = hargaPerJam;
        this.hargaPerHari = hargaPerHari;
    }

    public String getId() { return id; }
    public String getMerk() { return merk; }
    public String getModel() { return model; }
    public double getHargaPerJam() { return hargaPerJam; }
    public double getHargaPerHari() { return hargaPerHari; }

    @Override
    public String toString() {
        return id + " - " + merk + " " + model + 
               " (Rp" + hargaPerJam + "/jam, Rp" + hargaPerHari + "/hari)";
    }
}
