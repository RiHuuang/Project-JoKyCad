package FormLogin;

import UangKas.Pembukuan;
import UangKas.PembukuanBulananFrame;
import UangKas.Transaksi;
import User.Siswa;
import User.User;
import User.Admin;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaksi> histories = new ArrayList<>();
    private static ArrayList<Pembukuan> pembukuans = new ArrayList<>();
    private static String[] status = new String[]{"Lunas", "Belum Bayar"};
    private static Siswa userTemp;




    //Nama,kelas,status bayar,username,password
    public static int validate(String username, String nama, String password, String role){
        initDataSiswa();
//        System.out.printf("%s %s %s",username,nama,password);\

//        System.out.println("Jumlah dari user "+ users.size());
//        System.out.printf("sout validate luar : %s %s %s\n", username, nama, password);


        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.size());
//            System.out.println(i);
//            System.out.printf("sout validate dalem for : %s %s %s\n", users.get(i).getUsername(), users.get(i).getNama(),users.get(i).getPassword());
            if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword()) && nama.equals(users.get(i).getNama()) && role.equalsIgnoreCase(users.get(i).getRole())){
                userTemp = (Siswa)users.get(i);
                if (users.get(i) instanceof Admin){
                    return 1;
                } else if (users.get(i) instanceof Siswa) {
                    return 2;
                }else return 0;
            }
        }
        return 0;
    }

    public static void reValidate() {
        validate(userTemp.getUsername(), userTemp.getNama(), userTemp.getPassword(), userTemp.getRole());
    }


    public static void initDataSiswa(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("FileDataSiswa.txt"));
            String s="";

            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");
                String namaSiswa = data[0];
                String kelasSiswa = data[1];
                int status = Integer.parseInt(data[2]);
                String username = data[3];
                String password = data[4];
                String role = data[5];
//                int denda = Integer.parseInt(data[6]);
                int daysPassed = Integer.parseInt(data[6]);
//                System.out.printf("%s %s %d %s %s %s %d %d", namaSiswa,kelasSiswa,status,username,password,role,denda,daysPassed);

                if(role.equalsIgnoreCase("admin"))users.add(new Admin(namaSiswa,kelasSiswa,getStatus(status),username,password,role));
                else if (role.equalsIgnoreCase("siswa"))users.add(new Siswa(namaSiswa,kelasSiswa,getStatus(status),username,password,role,daysPassed));
                else throw(new IllegalArgumentException("No user"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void updateDataSiswa(String username, String password, int updatedStatus, int updatedDaysPassed){

        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader br = new BufferedReader(new FileReader("FileDataSiswa.txt"));

            String s="";

            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");
                if(username.equals(data[3]) && password.equals(data[4])){
                    userTemp.setNama(data[0]);
                    userTemp.setKelas(data[1]);
                    userTemp.setUsername(data[3]);
                    userTemp.setPassword(data[4]);
                    userTemp.setRole(data[5]);
                    userTemp.setDaysPassed(updatedDaysPassed);
                    userTemp.setStatus(String.valueOf(updatedStatus));
                    String update = userTemp.getNama()+","+userTemp.getKelas()+","+userTemp.getStatus()+","+userTemp.getUsername()+","+userTemp.getPassword()+","+userTemp.getRole()+","+userTemp.getDaysPassed();
                    sb.append(update);
                } else {
                    sb.append(s);
                }
                sb.append("\n");
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("FileDataSiswa.txt"));
            bw.write(sb.toString());
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        userTemp.setStatus(getStatus(updatedStatus));
    }
    public static void insertHistorySiswa(double jumlahBayar, LocalDate tanggalBayar){
        String loggedUsername = getUserTemp().getUsername();
        String loggedClass = getUserTemp().getKelas();
        try{
            File f=new File("FileHistorySiswa.txt");
//            BufferedWriter bw = new BufferedWriter(new FileWriter("FileHistorySiswa.txt"));
            PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
            String temp = tanggalBayar +","+loggedUsername+","+loggedClass+","+ jumlahBayar+"\n";
            pw.append(temp);
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void initPembukuanBulanan(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("FilePembukuanBulanan.txt"));
            String s="";

            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");
//                date, jumlah duid awal, pemasukan bulan ini, pengeluaran bulan ini, jumlah duid akhir
                LocalDate tanggalInput = LocalDate.parse(data[0]);
//                System.out.println(TanggalInput);
                double jumlahAwal = Double.parseDouble(data[1]);
                double pemasukanBulanan = Double.parseDouble(data[2]);
                double pengeluaranBulanan = Double.parseDouble(data[3]);
                double jumlahAkhir = Double.parseDouble(data[4]);
                pembukuans.add(new Pembukuan(tanggalInput,jumlahAwal,pemasukanBulanan,pengeluaranBulanan,jumlahAkhir));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updatePembukuanBulanan(LocalDate tanggal,double pemasukan, double pengeluaran){
        try{
            BufferedReader br = new BufferedReader(new FileReader("FilePembukuanBulanan.txt"));
            StringBuffer sb = new StringBuffer();

            String s="";
            int i=0;
            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");
                Pembukuan bulanIni = pembukuans.get(i);
                if(tanggal.getMonthValue()==(bulanIni.getTanggal().getMonthValue())){

                    bulanIni.setPemasukanBulanan(bulanIni.getPemasukanBulanan()+pemasukan);
                    bulanIni.setPengeluaranBulanan(bulanIni.getPengeluaranBulanan()+pengeluaran);

                    double jumlahAkhir = bulanIni.getJumlahAwal() + bulanIni.getPemasukanBulanan() - bulanIni.getPengeluaranBulanan();

                    String update = bulanIni.getTanggal()+","+bulanIni.getJumlahAwal()+","+(bulanIni.getPemasukanBulanan())+","+ (bulanIni.getPengeluaranBulanan())+","+ jumlahAkhir;
                    sb.append(update);
                }else {
                    sb.append(s);
                }
                sb.append("\n");
                i++;
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("FilePembukuanBulanan.txt"));
            bw.write(sb.toString());
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //ini buat nambahin kalo beda bulan
    public static void updatePembukuanBulanan(LocalDate bulan, double jumlahAwal, double pemasukan, double pengeluaran, double jumlahAkhir){
        try{
            File f=new File("FilePembukuanBulanan.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f,true));
            String temp = bulan+","+jumlahAwal+","+pemasukan+","+pengeluaran+","+jumlahAkhir;
            pw.append(temp);
            pw.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Siswa getUserTemp() {
        return userTemp;
    }

    public static ArrayList<Transaksi> getHistories() {
        return histories;
    }

    public static ArrayList<Pembukuan> getPembukuans() {
        return pembukuans;
    }

    public static void setPembukuans(ArrayList<Pembukuan> pembukuans) {
        Database.pembukuans = pembukuans;
    }

    public static String getStatus(int i) {
        return status[i];
    }


    public static void main(String[] args) {
//        initDataSiswa();
        initPembukuanBulanan();
        System.out.println(Database.getPembukuans().get(0));

//        System.out.println(users.size());
//        validate("ZanyZachary", "Zachary", "password_admin","admin");
    }
}


