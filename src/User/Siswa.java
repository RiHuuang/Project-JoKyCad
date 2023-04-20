package User;

import UangKas.Transaksi;

import java.util.ArrayList;

public class Siswa extends User{

    private ArrayList<Transaksi> historyTransaksi= new ArrayList<>();


    private int denda;
    private int daysPassed;
    public Siswa(String nama, String kelas, String status, String username, String password, String role, int denda, int daysPassed) {
        super(nama, kelas,status, username, password, role);
        this.denda=denda;
        this.daysPassed=daysPassed;
    }

    public ArrayList<Transaksi> getHistoryTransaksi() {
        return historyTransaksi;
    }

    public int getDenda() {
        return denda;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public static void main(String[] args) {

    }
}
