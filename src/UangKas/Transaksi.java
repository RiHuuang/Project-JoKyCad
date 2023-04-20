package UangKas;

import FormLogin.Database;
import FormLogin.Loginn;
import User.Siswa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Transaksi {
    Siswa siswa;
    protected Double jumlahPembayaran;
    protected LocalDate tanggalBayar;
    protected Double jumlahDenda;
    protected LocalDate deadlineBayar;
    protected boolean pembayaran;

    public Transaksi(LocalDate tanggalHariIni){
        tanggalBayar = tanggalHariIni;

    }

    public Transaksi(Double jumlahPembayaran, LocalDate tanggalBayar, Double jumlahDenda, LocalDate deadlineBayar) {
        //disini nulis di history, history nya di campur aja semua user nanti di linear search dari data nya yg namanya sesuai ama username
//        terus dia juga ngubah status dari 1 jadi 0, (cara yang baru kepikiran sih ngerewrite semuanya dengan cara masukin dulu ke variable
//        baru dimasukin lagi kedalem si txt nya.



    }

    protected static String getDeadline(LocalDate currentDate){

        LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu");
        String lastDayOfMonthString = lastDayOfMonth.format(formatter);
        return lastDayOfMonthString;
    }
    protected long countDaysPassed(){
        LocalDate currentDate = LocalDate.now();

        // Get the last day of the previous month
        LocalDate lastDayOfPreviousMonth = currentDate.withDayOfMonth(1).minusDays(1);

        // Calculate the number of days between the last day of the previous month and today's date
        long daysSinceDeadline = ChronoUnit.DAYS.between(lastDayOfPreviousMonth, currentDate);


        return daysSinceDeadline;
    }
    protected double hitungPembayaran(Double jumlahPembayaran, Double jumlahDenda){
        double total = jumlahPembayaran + jumlahDenda;
        return total;
    }

    protected double hitungDenda(int daysPassed){
//        Database.getUserTemp();
//        System.out.println(Database.getUserTemp());
        return daysPassed * 5000.00;
    }


    public static void main(String[] args) {
//        System.out.println(countDaysPassed());

    }
}
