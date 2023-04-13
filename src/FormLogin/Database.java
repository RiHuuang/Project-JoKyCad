package FormLogin;

import User.SiswaForm;

import java.util.ArrayList;

public class Database {
//    bagian database login
    private static String[] usernames = {"admin1","admin2","B30401"};
    private static String[] namas = {"Richard","Franky","Joshua"};
    private static String[] passwords = {"admin1","admin2","xc73gt"};
    private static String[] roles = {"admin","siswa"};
//    ini buat bagian database dataSiswa
    private ArrayList<SiswaForm> siswaForms = new ArrayList<>();
    private String[] status = new String[]{"Lunas", "Belum Bayar"};


    public static int validate(String username, String nama, String password, String role){
//        System.out.printf("%s %s %s",username,nama,password);
        for (int i = 0; i < username.length(); i++) {
//            System.out.printf("Username: %s , Password: %s, Nama: %s%n", usernames[i], passwords[i], namas[i]);
            if(username.equals(usernames[i]) && password.equals(passwords[i]) && nama.equals(namas[i])){
                if (username.contains("admin") &&role.equalsIgnoreCase("admin")){
                    return 1;
                } else if (username.contains("siswa")&&role.equalsIgnoreCase("siswa")) {
                    return 2;
                }
            }else {
                return 0;
            }
        }
        return 0;
    }

    public void initDataSiswa(){
        getSiswaForms().add(new SiswaForm("Richard", "PPTI15", getStatus(0))); //ini dummy data
        getSiswaForms().add(new SiswaForm("Richard", "PPTI15", getStatus(0)));
        getSiswaForms().add(new SiswaForm("Richard", "PPTI15", getStatus(0)));
        getSiswaForms().add(new SiswaForm("Richard", "PPTI15", getStatus(0)));
        getSiswaForms().add(new SiswaForm("Richard", "PPTI15", getStatus(0)));
    }

    public ArrayList<SiswaForm> getSiswaForms() {
        return siswaForms;
    }

    //    public void addSiswaForm();
    public void setSiswaForms(ArrayList<SiswaForm> siswaForms) {
        this.siswaForms = siswaForms;
    }
    public String getStatus(int i) {
        return status[i];
    }
}


