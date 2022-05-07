package de.oldspielmodis.nick.mysql;

import de.oldspielmodis.nick.Nicksystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Nick {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Nick (UUID VARCHAR(100), NAME VARCHAR(100), NICKED VARCHAR(100), ID VARCHAR(100), ISNICKED VARCHAR(100))");
    }

    public boolean isRegistered(String uuid){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Nick WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void register(String uuid, String name, String nicked, String id, boolean isnicked){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO Nick (UUID, NAME, NICKED, ID, ISNICKED) VALUES ('" + uuid + "', '" + name + "', '" + nicked + "', '" + id + "', '" + isnicked + "')");
    }

    public void updateNicked(String uuid, String nicked){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        mySQL.update("UPDATE Nick SET NICKED=? WHERE UUID=?", nicked, uuid);
    }

    public void updateID(String uuid, String id){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        mySQL.update("UPDATE Nick SET ID=? WHERE UUID=?", id, uuid);
    }

    public void updateIsNicked(String uuid, boolean isnicked){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        mySQL.update("UPDATE Nick SET ISNICKED=? WHERE UUID=?", isnicked, uuid);
    }

    public boolean isNicked(String uuid){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Nick WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getBoolean("ISNICKED");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public String getNick(String uuid){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Nick WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("NICKED");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getName(String uuid){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Nick WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("NAME");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean getNick(String uuid, String nicked){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Nick WHERE UUID=? AND NICKED=?", uuid, nicked)){
            if(rs.next()){
                return rs.getString("NICKED") != null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public String getID(String uuid){
        this.mySQL = Nicksystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Nick WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getString("ID");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
}
