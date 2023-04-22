package UangKas;

import java.time.LocalDate;
import java.util.Date;

public class Pembukuan {
//    date, jumlah duid awal, pemasukan bulan ini, pengeluaran bulan ini, jumlah duid akhir
    private LocalDate tanggal;


    private double pemasukanBulanan;
    private double jumlahAwal;
    private double pengeluaranBulanan;
    private double jumlahAkhir;

    public Pembukuan(LocalDate tanggal, double jumlahAwal, double pemasukanBulanan, double pengeluaranBulanan, double jumlahAkhir) {
        this.tanggal = tanggal;
        this.pemasukanBulanan = pemasukanBulanan;
        this.jumlahAwal = jumlahAwal;
        this.pengeluaranBulanan = pengeluaranBulanan;
        this.jumlahAkhir = jumlahAkhir;
    }

    //kalo masih dalam bulan yang sama update, else(udah lewat) append new line terus reset semua variable
    public LocalDate getTanggal() {
        return tanggal;
    }


    public double getPemasukanBulanan() {
        return pemasukanBulanan;
    }

    public double getJumlahAwal() {
        return jumlahAwal;
    }

    public double getPengeluaranBulanan() {
        return pengeluaranBulanan;
    }

    public double getJumlahAkhir() {
        return jumlahAkhir;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setPemasukanBulanan(double pemasukanBulanan) {
        this.pemasukanBulanan = pemasukanBulanan;
    }

    public void setJumlahAwal(double jumlahAwal) {
        this.jumlahAwal = jumlahAwal;
    }

    public void setPengeluaranBulanan(double pengeluaranBulanan) {
        this.pengeluaranBulanan = pengeluaranBulanan;
    }

    public void setJumlahAkhir(double jumlahAkhir) {
        this.jumlahAkhir = jumlahAkhir;
    }
}
