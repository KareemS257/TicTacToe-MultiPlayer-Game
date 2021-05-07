package gameserverswing;

import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;

public class DataBaseManagment {

    static final String url = "jdbc:postgresql:tictactoe";
    static final String user = "postgres";
    static final String password = "new";
    static Connection connection;
    static PreparedStatement preparedStatment = null;
    static ResultSet result = null;
    static String sqlCommand;
    static String sqlCommand1;
    static char[] ch = null;
    static Statement state;
    static String nme = "";
    static boolean checkname = true;

    static void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            state = connection.createStatement();

            System.err.println("Connection is made successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static boolean signin(String pname) {
        try {
            connect();
            sqlCommand = "SELECT name from playersinfo ";
            Statement stat = connection.createStatement();
            ResultSet rs1 = stat.executeQuery(sqlCommand);

            while (rs1.next()) {
                if (rs1.getString(1).equals(pname)) {
                    nme = rs1.getString(1);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (nme.equals(pname)) {
            // System.out.println(nme);
            System.out.println("Login checked has done to user " + pname);
            return true;
        } else {
            return false;
        }
    }

    static boolean signup(String pname) {
        try {
            //checkname = true;
            connect();
            sqlCommand = "SELECT name from playersinfo ";
            Statement stat = connection.createStatement();
            ResultSet rs1 = stat.executeQuery(sqlCommand);

            while (rs1.next()) {
                if (rs1.getString(1).equals(pname)) {
                    checkname = false;
                    break;
                } else {
                    checkname = true;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (checkname) {

            try {
                PreparedStatement pstat = connection.prepareStatement("insert into playersinfo values(?,?,?)");
                pstat.setString(1, pname);
                pstat.setInt(2, 0);
                pstat.setInt(3, 0);
                int rows = pstat.executeUpdate();

//            Statement stat = connection.createStatement();
//            ResultSet rs1 = pstat.executeQuery(sqlCommand);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return true;

        } else {
            System.out.println("failed");
            return false;
        }
    }

    static String maxscore() {
        try {
            connect();
            sqlCommand = "SELECT max(Wins) from playersinfo ";
            state = connection.createStatement();
            ResultSet rs1 = state.executeQuery(sqlCommand);

            while (rs1.next()) {
                nme = rs1.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // System.out.println(nme);
        return nme;
    }

    static void increaseScore(String pname) {
        connect();
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE playersinfo set Wins=Wins+1 WHERE name=?");
            pstat.setString(1, pname);
            int rows = pstat.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static void increaseLoses(String pname) {
        connect();
        try {
            PreparedStatement pstat = connection.prepareStatement("UPDATE playersinfo set losess=losess+1 WHERE name=?");
            pstat.setString(1, pname);
            int rows = pstat.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static boolean saveReplay(Object replay, String playerName) { //Code added here by Kareem
        connect();

        try {
            Statement st = connection.createStatement();

            String query = new String("insert into replay values(?,?,?,?);");
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setObject(4, replay);
            pst.setString(2, playerName);
            pst.setObject(3, LocalDate.now());
            pst.setInt(1, GameServer.totalRecords);

            int status = pst.executeUpdate();
            if (status == 1) {
                GameServer.totalRecords++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static Vector<String> fetchReplays(String playerName) {
        connect();
        int i = 0;
        Vector<String> replays = new Vector<String>();
        String pName = playerName;
        Vector<String> fetchedIds = new Vector<String>();
        System.out.print(pName);
        try {
            String query = new String("select p_date,id from replay where name = ?;");
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, pName);
            ResultSet result = pst.executeQuery();
     

            while (result.next()) {
                replays.add(result.getString(1));
                fetchedIds.add (result.getString(2));
            }
            playerInput.listOfIds = new String[replays.size()];
            for(int k=0; k<fetchedIds.size(); k++ ){
             playerInput.listOfIds[k] = fetchedIds.get(k);
            }


            System.out.print(fetchedIds.size());
            pst.close();
            connection.close();
            if (replays.size() == 0) {
                System.out.print("I failed");
                replays.add("empty");
                return replays;
            } else {

                //System.out.print(replays.size());
                return replays;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
System.out.println(replays.size());
        return replays;
    }

    static Vector<String> selectReplays(String date, String id) {
        connect();
        Vector<String> replays = new Vector<String>();
        LocalDate d = LocalDate.parse(date);

        try {
            String query = new String("select moves from replay where p_date = ? AND id=?");
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setObject(1, d);
            pst.setInt(2, Integer.parseInt(id));
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                replays.add(result.getString(1));

            }

            System.out.println(replays.get(0));
            pst.close();
            connection.close();

            if (replays.size() == 0) {
                System.out.print("I failed");
                return replays;
            } else {

                //System.out.print(replays.size());
                return replays;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return replays;
    }

    static int getTotalrecords() {
        connect();
        int totalRecords = 0;
        try {
            String query = new String("select COUNT(name) from replay;");
            PreparedStatement pst = connection.prepareStatement(query);

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                totalRecords = result.getInt(1);
            }
            pst.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecords;

    }
}
