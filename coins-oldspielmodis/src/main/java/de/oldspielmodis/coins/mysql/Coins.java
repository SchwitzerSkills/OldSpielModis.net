package de.oldspielmodis.coins.mysql;

import de.oldspielmodis.coins.Coinssystem;
import de.oldspielmodis.coins.event.PlayerCoinsChangeEvent;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Coins {

    private MySQL mySQL;

    public void createTable(){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS Coins (UUID VARCHAR(100), COINS VARCHAR(100))");
    }

    public boolean isUserExists(UUID uuid){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT COUNT(*) AS count FROM Coins WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(UUID uuid){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("INSERT INTO Coins (UUID, COINS) VALUES ('"+ uuid + "', '" + 0 + "')");
    }

    public void setCoins(UUID uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", uuid, coins);
        Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(uuid, coins));
    }

    public void addCoins(UUID uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        int amount = getCoins(uuid) + coins;
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", uuid, amount);
        Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(uuid, amount));
    }

    public void removeCoins(UUID uuid, int coins){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        int amount = getCoins(uuid) - coins;
        mySQL.update("UPDATE Coins SET COINS=? WHERE UUID=?", uuid, amount);
        Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(uuid, amount));
    }

    public int getCoins(UUID uuid){
        this.mySQL = Coinssystem.getInstance().getMySQL();
        try (ResultSet rs = mySQL.query("SELECT * FROM Coins WHERE UUID=?", uuid)){
            if(rs.next()){
                return rs.getInt("COINS");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
