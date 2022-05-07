package de.oldspielmodis.bungee.mysql;

import de.oldspielmodis.bungee.Bungee;
import de.oldspielmodis.bungee.utils.BanType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanReason {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS BanReasons (ID VARCHAR(100), REASON VARCHAR(100), DURATION VARCHAR(100), TYPE VARCHAR(100))");
    }

    public void addReason(int id, String reason, String duration, BanType banType){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("INSERT INTO BanReasons (ID, REASON, DURATION, TYPE) VALUES ('" + id + "', '" + reason + "', '" + duration + "', '" + banType + "')");
    }

    public void removeReason(int id){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("DELETE FROM BanReasons WHERE ID=?", id);
    }

    public void updateReason(int id, String reason, String duration, BanType banType){
        this.mySQL = Bungee.getInstance().getMySQL();
        mySQL.update("UPDATE BanReasons SET REASON='" + reason + "', DURATION='" + duration + "', TYPE='" + banType + "' WHERE ID='" + id + "'");
    }

    public boolean existsID(int id){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM BanReasons WHERE ID=?", id)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM BanReasons")){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getReasons(){
        this.mySQL = Bungee.getInstance().getMySQL();
        List<String> list = new ArrayList<>();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons")){
            while (rs.next()){
                list.add(rs.getString("REASON"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String getDurations(String reason){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE REASON=?", reason)){
            if (rs.next()){
                return rs.getString("DURATION");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getTypes(String reason){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE REASON=?", reason)){
            if (rs.next()){
                return rs.getString("TYPE");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getIDS(String reason){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE REASON=?", reason)){
            if (rs.next()){
                return rs.getString("ID");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getReason(int id){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE ID=?", id)){
            if (rs.next()){
                return rs.getString("REASON");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getDuration(int id){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE ID=?", id)){
            if (rs.next()){
                return rs.getString("DURATION");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String getType(int id){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE ID=?", id)){
            if (rs.next()){
                return rs.getString("TYPE");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean getType(int id, BanType banType){
        this.mySQL = Bungee.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM BanReasons WHERE ID='" + id + "' AND TYPE='" + banType + "'")){
            if (rs.next()){
                return rs.getString("TYPE") != null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
