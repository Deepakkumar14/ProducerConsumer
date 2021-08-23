package producerConsumer;


import java.sql.*;

public class Database {

        private static Connection conn = null;
        private static PreparedStatement prepStmt =null;
        private static ResultSet resultSet =null;
        private static String query="";

        public  Database() throws Exception {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/writer", "root", "1234");

            } catch (ClassNotFoundException| SQLException e) {
                System.out.println("Not connected to the Database!!!Connection error");
            }
        }

    public boolean insertLines(String str){
        try{
            conn.setAutoCommit(false);
            query = "INSERT INTO writer(file_line)values(?)";
            prepStmt = conn.prepareStatement(query);
            prepStmt.setString(1,str);
            prepStmt.executeUpdate();
            conn.commit();
        }catch(Exception e) {
            System.out.println(e);
            try {
                conn.rollback();
                return false;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }finally {
            if (prepStmt !=null)
                try {
                    prepStmt.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return true;
    }


}

