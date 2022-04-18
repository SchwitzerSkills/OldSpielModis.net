package de.oldspielmodies.mysql;

import de.oldspielmodies.lobby.Lobbysystem;
import de.oldspielmodies.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gadget {

    private MySQL mySQL;

    public void createTable() {
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Gadget (UUID VARCHAR(100), GADGET VARCHAR(100), SELECTED VARCHAR(100))");
    }

    public void addGadget(String uuid, String gadget, boolean selected) {
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO Gadget (UUID, GADGET, SELECTED) VALUES ('" + uuid + "', '" + gadget + "', '" + selected + "')");
    }

    public void updateSelectedGadget(String uuid, String gadget, String selected) {
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("UPDATE Gadget SET SELECTED=? WHERE UUID=? AND GADGET=?", selected, uuid, gadget);
    }

    public void updateSelectedGadgetAll(String uuid, String selected) {
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        mySQL.update("UPDATE Gadget SET SELECTED=? WHERE UUID=?", selected, uuid);
    }

    public boolean hasGadget(String uuid, String gadget) {
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Gadget WHERE UUID=? AND GADGET=?", uuid, gadget)) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasGadgetSelected(String uuid, String gadget){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Gadget WHERE UUID=? AND GADGET=?", uuid, gadget)){
            if(rs.next()){
                return rs.getBoolean("SELECTED");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasGadgetSelectedAll(String uuid){
        this.mySQL = Lobbysystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Gadget WHERE UUID=? AND SELECTED='true'", uuid)){
            if(rs.next()){
                return rs.getBoolean("SELECTED");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
