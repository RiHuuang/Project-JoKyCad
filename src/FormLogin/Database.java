package FormLogin;

import User.Siswa;
import User.User;
import User.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Database {


    private static ArrayList<User> users = new ArrayList<>();
    private static String[] status = new String[]{"Lunas", "Belum Bayar"};
    private static Siswa userTemp;



    //Nama,kelas,status bayar,username,password
    public static int validate(String username, String nama, String password, String role){
//        System.out.printf("%s %s %s",username,nama,password);\

//        System.out.println("Jumlah dari user "+ users.size());
//        System.out.printf("sout validate luar : %s %s %s\n", username, nama, password);


        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.size());
            System.out.println(i);
            System.out.printf("sout validate dalem for : %s %s %s\n", users.get(i).getUsername(), users.get(i).getNama(),users.get(i).getPassword());
            if(username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword()) && nama.equals(users.get(i).getNama())){
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
//    public static User getSpecificUser(String username, String nama, String password){
//        for(User user: users){
//            if(username.equals(user.getUsername()) && password.equals(user.getPassword()) && nama.equals(user.getNama())){
//                return user;
//            }
//        }
//        System.out.println("not found");
//        return null;
//    }

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
                int denda = Integer.parseInt(data[6]);
                int daysPassed = Integer.parseInt(data[7]);
//                System.out.printf("%s %s %d %s %s %s %d %d", namaSiswa,kelasSiswa,status,username,password,role,denda,daysPassed);

                if(role.equalsIgnoreCase("admin"))users.add(new Admin(namaSiswa,kelasSiswa,getStatus(status),username,password,role));
                else if (role.equalsIgnoreCase("siswa"))users.add(new Siswa(namaSiswa,kelasSiswa,getStatus(status),username,password,role,denda,daysPassed));
                else throw(new IllegalArgumentException("No user"));
            }
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

    public static String getStatus(int i) {
        return status[i];
    }


    public static void main(String[] args) {
        initDataSiswa();

//        System.out.println(users.size());
//        validate("ZanyZachary", "Zachary", "password_admin","admin");
    }
}


