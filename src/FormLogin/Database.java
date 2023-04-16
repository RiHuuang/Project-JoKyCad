package FormLogin;

import User.Siswa;
import User.User;
import User.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Database {
//    bagian database login
//    private static String[] usernames = {"admin1","admin2","B30401"};
//    private static String[] namas = {"Richard","Franky","Joshua"};
//    private static String[] passwords = {"admin1","admin2","xc73gt"};
//    private static String[] roles = {"admin","siswa"};
//    ini buat bagian database dataSiswa

    private static ArrayList<User> users = new ArrayList<>();
    private static String[] status = new String[]{"Lunas", "Belum Bayar"};

    //Nama,kelas,status bayar,username,password
    public static int validate(String username, String nama, String password, String role){
//        System.out.printf("%s %s %s",username,nama,password);\

//        System.out.println("Jumlah dari user "+ users.size());
//        System.out.printf("sout validate luar : %s %s %s\n", username, nama, password);


        for (int i = 0; i < users.size(); i++) {
            System.out.println(i);
//            System.out.printf("sout validate dalem for : %s %s %s", users.get(i).getUsername(), users.get(i).getNama(),users.get(i).getPassword());
            if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword()) && nama.equals(users.get(i).getNama())){
                if (users.get(i) instanceof Admin){
                    return 1;
                } else if (users.get(i) instanceof Siswa) {
                    return 2;
                }else return 0;
            }
        }
        return 0;
    }

    public static void initDataSiswa(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("FileDataSiswa.txt"));
            String s="";

            while ((s=br.readLine()) !=null){
                String data[] = new String[6];
                data = s.split(",");
                String namaSiswa = data[0];
                String kelasSiswa = data[1];
                int status = Integer.parseInt(data[2]);
                String username = data[3];
                String password = data[4];
                String role = data[5];
//                System.out.printf("%s %s %d %s %s %s", namaSiswa,kelasSiswa,status,username,password,role);

                if(role.equalsIgnoreCase("admin"))users.add(new Admin(namaSiswa,kelasSiswa,getStatus(status),username,password,role));
                else if (role.equalsIgnoreCase("siswa"))users.add(new Siswa(namaSiswa,kelasSiswa,getStatus(status),username,password,role));
                else throw(new IllegalArgumentException("No "));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Database.users = users;
    }

    public static String getStatus(int i) {
        return status[i];
    }

    public static void main(String[] args) {
        initDataSiswa();
//        System.out.println(users.size());
//        validate("ZanyZachary", "Zachary", "password_admin","admin");
    }
}


