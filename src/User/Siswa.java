package User;

public class Siswa extends User{
//    protected SiswaForm siswaForm = new SiswaForm();
    private String status;

    public Siswa(String nama, String kelas, String status, String username, String password, String role) {
        super(nama, kelas, username, password, role);
        this.status=status;
    }


    public static void main(String[] args) {

    }
}
