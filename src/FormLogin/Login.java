package FormLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JDialog{
//    EnterAdmin enterAdmin;
//    EnterSiswa enterSiswa;
    private JTextField txtUsername;
    private JTextField txtName;
    private JPasswordField txtPass;
    private JComboBox txtRole;
    private JButton loginButton;
    private JPanel loginPanel;
    private JButton cancelButton;

    public Login(JFrame parent){
        super(parent);
        setTitle("Login");
//        enterAdmin = new EnterAdmin(null);
//        enterSiswa = new EnterSiswa(null);
//        enterSiswa.setVisible(false);
//        enterAdmin.setVisible(false);

        setContentPane(loginPanel);
        setSize(500,500);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String name = txtName.getText();
                String password = String.valueOf(txtPass.getPassword());
                String role = String.valueOf(txtRole.getModel());

                user = getAuthenticatedUser(username,name,password,role);

                if (user != null){
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(Login.this,"Invalid Login Please ask Admission","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public UserLogin user;
    private UserLogin getAuthenticatedUser(String username, String name, String password, String role) {
        UserLogin user = null;

        final String DB_URL = "jdbc:mysql://localhost/kasrtb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "admin2";

        try{
            Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stat = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,role);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new UserLogin();
                user.username = resultSet.getString("username");
                user.name = resultSet.getString("nama");
                user.password = resultSet.getString("password");
                user.role = resultSet.getString("role");
            }
            stat.close();
            connection.close();


        }catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }
    public static void main(String[] args) {
        Login login = new Login(null);
        UserLogin user = login.user;
        if (user != null){
            System.out.println("Succes Login as "+user.role);
            System.out.println(" Welcome " + user.name);
//            if (user.role.equals("admin")){
//                login.enterAdmin.setVisible(true);
//            } else if (user.role.equals("siswa")) {
//                login.enterSiswa.setVisible(true);
//            }
        }
        else {
            System.out.println("Authentication Cancel");
        }
    }
}
