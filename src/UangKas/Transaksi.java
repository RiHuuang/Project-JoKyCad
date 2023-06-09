package UangKas;

import FormLogin.Database;
import FormLogin.Loginn;
import User.SiswaForm;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Transaksi {
    public Double jumlahPembayaran;
    public LocalDate tanggalBayar;


    public Transaksi(LocalDate tanggalHariIni){
        tanggalBayar = tanggalHariIni;
        //cuman buat init doang biar bisa manggil yg ada di transaksi (?)
    }

    public Transaksi(LocalDate tanggalBayar, double jumlahPembayaran){
        this.tanggalBayar = tanggalBayar;
        this.jumlahPembayaran = jumlahPembayaran;
    }
    public Transaksi(double jumlahPengeluaran, LocalDate tanggalBayar){
        Database.initPembukuanBulanan();

        System.out.println("Tanggal Bayar get month value -> "+tanggalBayar.getMonthValue());
//        System.out.println("\n"+Database.getPembukuans().getClass());
        LocalDate transactionTime = LocalDate.now();
        System.out.println("Database get pembukuans size terakhir getvalue -> "+Database.getPembukuans().get(Database.getPembukuans().size()-1).getTanggal().getMonthValue());
        if (tanggalBayar.getMonthValue()==(Database.getPembukuans().get(Database.getPembukuans().size()-1).getTanggal().getMonthValue())){
            Database.updatePembukuanBulanan(transactionTime,0,jumlahPengeluaran);
        }else { // Kalau Bulan nya beda kek di data terakhir (bulan baru)
            double awal = Database.getPembukuans().get(Database.getPembukuans().size()-1).getJumlahAkhir();
            double akhir = awal + Database.getPembukuans().get(Database.getPembukuans().size()-1).getPemasukanBulanan() - jumlahPengeluaran;
            Database.updatePembukuanBulanan(transactionTime,awal,0,jumlahPengeluaran, akhir);
        }
        int dialog = JOptionPane.showConfirmDialog(null,"Changes Successful, Data has been recorded!\nDo you want to continue?", "Payment Successful", JOptionPane.YES_NO_OPTION);

        return;

    }
    public Transaksi(Double jumlahPembayaran, LocalDate tanggalBayar, double jumlahPengeluaran) {
        int count = 1;
        Database.initPembukuanBulanan();
        Database.initDataSiswa();
        Database.reValidate();
        System.out.println();

        //disini nulis di history, history nya di campur aja semua user nanti di linear search dari data nya yg namanya sesuai ama username
//        terus dia juga ngubah status dari 1 jadi 0 sama daysPassed nya jadi 0, (cara yang baru kepikiran sih ngerewrite semuanya dengan cara masukin dulu ke variable
//        baru dimasukin lagi kedalem si txt nya.
        System.out.println("Status database userTemp: "+Database.getSiswaTemp().getStatus());
//        ini ngecek dia udah bayar apa belomnya, trus update data di siswanya
        if(Database.getSiswaTemp().getStatus().equalsIgnoreCase("lunas")){
            JOptionPane.showMessageDialog(null,"You have already paid this month's cash","Paid",JOptionPane.ERROR_MESSAGE);
            new SiswaForm();

        }else if(Database.getSiswaTemp().getStatus().equalsIgnoreCase("belum bayar")){
            Database.updateDataSiswa(Database.getSiswaTemp().getUsername(),Database.getSiswaTemp().getPassword(),0,0);

            //        INSERT HISTORY SISWA
            LocalDate transactionTime = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu");
            Database.insertHistorySiswa(jumlahPembayaran, transactionTime);
            Database.initCount = 0; //agar mengulang initialisasi data.
            //disini abis nge 0 in, ngereset si arraylist nya
            Database.getSiswaTemp().getHistoryTransaksi().clear();

            System.out.println("Pembayaran"+Database.getSiswaTemp().getUsername() + " " + Database.getSiswaTemp().getStatus());

//        NAMBAHIN KE PEMBUKUAN BULANAN
//            Kalau Bulan nya sama kek di data terakhir
            System.out.println("Tanggal Bayar get month value -> "+tanggalBayar.getMonthValue());
//        System.out.println("\n"+Database.getPembukuans().getClass());
            System.out.println("Database get pembukuans size terakhir getvalue -> "+Database.getPembukuans().get(Database.getPembukuans().size()-1).getTanggal().getMonthValue());
            if (tanggalBayar.getMonthValue()==(Database.getPembukuans().get(Database.getPembukuans().size()-1).getTanggal().getMonthValue())){
                Database.updatePembukuanBulanan(transactionTime,jumlahPembayaran,0);
            }else { // Kalau Bulan nya beda kek di data terakhir (bulan baru)
                double awal = Database.getPembukuans().get(Database.getPembukuans().size()-1).getJumlahAkhir();
                double akhir = awal + jumlahPembayaran - jumlahPengeluaran;
                Database.updatePembukuanBulanan(transactionTime,awal,jumlahPembayaran,jumlahPengeluaran, akhir);
            }

            int dialog = JOptionPane.showConfirmDialog(null,"Payment Successful, Data has been recorded!\nDo you want to continue?", "Payment Successful", JOptionPane.YES_NO_OPTION);



            if(dialog==JOptionPane.YES_OPTION) {
                new SiswaForm();
                Database.initDataSiswa();
                Database.reValidate();
            }
            else {
                new Loginn();
                Database.initDataSiswa();
                Database.reValidate();
            }

            count++;
        }else throw new RuntimeException();


    }

    protected static String getDeadline(LocalDate currentDate){

        LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu");
        return lastDayOfMonth.format(formatter);
    }
    protected long countDaysPassed(){
        LocalDate currentDate = LocalDate.now();

        LocalDate lastDayOfPreviousMonth = currentDate.withDayOfMonth(1).minusDays(1);

        long daysSinceDeadline = ChronoUnit.DAYS.between(lastDayOfPreviousMonth, currentDate);


        if(Database.getSiswaTemp().getStatus().equalsIgnoreCase("belum bayar")){
            return daysSinceDeadline;
        }else if (Database.getSiswaTemp().getStatus().equalsIgnoreCase("LUNAS")) return 0;
        return -1;
    }
    protected double hitungPembayaran(Double jumlahPembayaran, Double jumlahDenda){
        double total = jumlahPembayaran + jumlahDenda;
        if(Database.getSiswaTemp().getStatus().equalsIgnoreCase("belum bayar") && (Database.getSiswaTemp().getDaysPassed()>0 &&Database.getSiswaTemp().getDaysPassed()<6)){//ini buat kalo bayar 2 bulan, bayar bulan lalu yg nunggak sama bayar bulan ini.
            total += 100000.00;
        }
        return total;
    }

    protected double hitungDenda(int daysPassed){
//        Database.getUserTemp();
//        System.out.println(Database.getUserTemp());
        if (daysPassed < 6 && daysPassed >=0) return daysPassed * 5000.00;
        else return 25000.00;
    }

    public Double getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public LocalDate getTanggalBayar() {
        return tanggalBayar;
    }

}
