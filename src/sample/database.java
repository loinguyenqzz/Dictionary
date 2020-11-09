package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class database {
    private Connection conn;
    public database() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DataDictionary";
            String user="sa"; // your db username
            String password="123"; // your db password
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
//                    "databaseName=Tutorial;username=sa;password=123;";
            this.conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                //System.out.println("Connect success!");
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<String> suggestion() {
//        String sql = "SELECT * FROM tbl_etbl_edictt";
//        List<String> list = new ArrayList<String>();
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                String str = resultSet.getString(2);
//                list.add(str);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    public List<String> suggestion(String s) {
        String sql = "SELECT * FROM tbl_etbl_edictt where word like '" + s + "%'";
        List<String> list = new ArrayList<String>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String str = resultSet.getString(2);
                list.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String search(String s) {
        String sql = "SELECT * FROM tbl_etbl_edictt where word = '" + s + "'";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                var word = resultSet.getString("word");
                var detail = resultSet.getString("detail");
                if (word.equals(s)) {
                    return detail.replace('$', '\n');
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Not found";
    }

    public boolean loadWord(String s) {
        String sql = "SELECT * FROM tbl_etbl_edictt";
        try {
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(2).equals(s)) {
                return true;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return false;
    }

    public boolean addWord(Vocabulary word){
        String sql = "INSERT INTO tbl_etbl_edictt(idx, word, detail) VALUES(?,?,?)";
        if (!loadWord(word.English)) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setString(2, word.English);
                ps.setString(3, word.Vietnamese);
                return ps.executeUpdate() > 0;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteWord(String word){
        if (loadWord(word)) {
            String sql = "DELETE FROM tbl_etbl_edictt where word = '" + word + "'";
            try {
                Statement ps = conn.createStatement();
                return ps.executeUpdate(sql) > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateWord(String beforeWord, String afterWord, String detail){
        if (loadWord(beforeWord)) {
            String sql;
            if (detail.equals("")) sql = "update tbl_etbl_edictt set word = '" + afterWord + "'"
                    +" where word = '" + beforeWord + "'";
            else sql = "update tbl_etbl_edictt set word = '" + afterWord + "'" + ",detail = '" + detail
                    +"' where word = '" + beforeWord + "'";
            try {
                Statement ps = conn.createStatement();
                return ps.executeUpdate(sql) > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}