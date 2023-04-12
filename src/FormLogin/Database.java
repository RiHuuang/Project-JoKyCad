package FormLogin;

public class Database {
    private static String[] usernames = {"admin1","admin2","B30401"};
    private static String[] namas = {"Richard","Franky","Joshua"};
    private static String[] passwords = {"admin1","admin2","xc73gt"};
    private static String[] roles = {"admin","siswa"};

    public static int validate(String username, String nama, String password, String role){
        for (int i = 0; i < username.length(); i++) {
            if(username.equals(usernames[i]) && password.equals(passwords[i])&& nama.equals(namas[i])){
                if (username.contains("admin") &&role.equals("admin")){
                    return 1;
                } else if (username.contains("B")&&role.equals("siswa")) {
                    return 2;
                }
            }else {
                return 0;
            }
        }
        return 0;
    }

}


