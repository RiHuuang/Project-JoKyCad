package User;


public class User {


    private String kelas;
    private String nama;
    private String username;
    private String password;
    private String role;
    private String status;



    public User(String nama, String kelas, String status, String username, String password, String role) {
        this.kelas = kelas;
        this.nama = nama;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }
}
