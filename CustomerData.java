public class CustomerData {
    private String id;
    private String nama;
    private String noHp;

    public CustomerData(String id, String nama, String noHp) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNoHp() { return noHp; }

    @Override
    public String toString() {
        return id + " - " + nama + " (" + noHp + ")";
    }
}
