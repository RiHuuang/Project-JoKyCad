package FormLogin;

import UangKas.Pembukuan;
import UangKas.Transaksi;
import User.Siswa;
import User.User;
import User.Admin;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Database {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaksi> histories = new ArrayList<>();
    private static ArrayList<Pembukuan> pembukuans = new ArrayList<>();
    private static String[] status = new String[]{"Lunas", "Belum Bayar", "Admin"};
    private static Siswa siswaTemp;
    private static User userTemp;
    private static Admin adminTemp;

    public static int initCount = 0;



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

                userTemp = users.get(i);

                if (users.get(i) instanceof Admin){
                    adminTemp = (Admin) adminTemp;
                    return 1;
                } else if (users.get(i) instanceof Siswa) {
                    siswaTemp = (Siswa) userTemp;
                    Database.initHistorySiswa();
                    return 2;
                }else return 0;
            }
        }
        return 0;
    }

    public static void reValidate() {
        validate(siswaTemp.getUsername(), siswaTemp.getNama(), siswaTemp.getPassword(), siswaTemp.getRole());

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
                    siswaTemp.setNama(data[0]);
                    siswaTemp.setKelas(data[1]);
                    siswaTemp.setUsername(data[3]);
                    siswaTemp.setPassword(data[4]);
                    siswaTemp.setRole(data[5]);
                    siswaTemp.setDaysPassed(updatedDaysPassed);
                    siswaTemp.setStatus(String.valueOf(updatedStatus));
                    String update = siswaTemp.getNama()+","+ siswaTemp.getKelas()+","+ siswaTemp.getStatus()+","+ siswaTemp.getUsername()+","+ siswaTemp.getPassword()+","+ siswaTemp.getRole()+","+ siswaTemp.getDaysPassed();
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
        siswaTemp.setStatus(getStatus(updatedStatus));
    }
    public static void resetStatusSiswa(){

        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader br = new BufferedReader(new FileReader("FileDataSiswa.txt"));

            String s="";

            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");

                if(data[2].equals("2")){
                    sb.append(s);
                }else{
                    String update = data[0]+","+ data[1]+",1,"+ data[3]+","+ data[4]+","+ data[5]+","+ data[6];
                    sb.append(update);
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
    }
    public static void insertHistorySiswa(double jumlahBayar, LocalDate tanggalBayar){
        String loggedUsername = getSiswaTemp().getUsername();
        String loggedClass = getSiswaTemp().getKelas();
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

    public static void initHistorySiswa(){
        if(initCount == 1) return;
        try{
            BufferedReader br = new BufferedReader(new FileReader("FileHistorySiswa.txt"));
            String s="";

            while ((s=br.readLine()) !=null){
                String[] data;
                data = s.split(",");
//                date, jumlah duid awal, pemasukan bulan ini, pengeluaran bulan ini, jumlah duid akhir
                LocalDate tanggalInput = LocalDate.parse(data[0]);
//                System.out.println(TanggalInput);
                String username = data[1];
                String kelasUser = data[2];
                double uangMasuk = Double.parseDouble(data[3]);
                if(siswaTemp.getUsername().equals(username)){
                    siswaTemp.getHistoryTransaksi().add(new Transaksi(tanggalInput,uangMasuk));
                    System.out.println("jumlah dari siswatemp sekarang transaksinya adalah : "+ siswaTemp.getHistoryTransaksi().size());
                }
            }
            initCount++;
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

    public static Siswa getSiswaTemp() {
        return siswaTemp;
    }

    public static User getUserTemp() {
        return userTemp;
    }

    public static Admin getAdminTemp() {
        return adminTemp;
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

}


