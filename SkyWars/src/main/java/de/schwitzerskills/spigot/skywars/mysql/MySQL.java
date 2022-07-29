package de.schwitzerskills.spigot.skywars.mysql;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class MySQL {
    private final LoadingCache<Integer, Connection> cache;

    private String connectionUrl;

    private String database;

    private String user;

    private String password;

    private Integer port;

    private MySQL(String connectionUrl, String database, String user, String password, Integer port) {
        this

                .cache = CacheBuilder.newBuilder().expireAfterAccess(10L, TimeUnit.SECONDS).removalListener(removalNotification -> {
            try {
                if (removalNotification.getValue() != null)
                    ((Connection) removalNotification.getValue()).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).build(new CacheLoader<Integer, Connection>() {
            public Connection load(Integer integer) throws Exception {
                return MySQL.this.createConnection();
            }
        });
        this.connectionUrl = connectionUrl;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public void update(String update, Object... objs) {
        try {
            Connection connection = (Connection) this.cache.get(Integer.valueOf(1));
            PreparedStatement p = connection.prepareStatement(update);
            setArguments(objs, p);
            p.execute();
            p.close();
        } catch (SQLException | java.util.concurrent.ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String query, Object... objs) {
        try {
            Connection connection = (Connection) this.cache.get(Integer.valueOf(1));
            PreparedStatement p = connection.prepareStatement(query);
            setArguments(objs, p);
            return p.executeQuery();
        } catch (SQLException | java.util.concurrent.ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setArguments(Object[] objs, PreparedStatement p) throws SQLException {
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            if (obj instanceof String) {
                p.setString(i + 1, (String) obj);
            } else if (obj instanceof Integer) {
                p.setInt(i + 1, ((Integer) obj).intValue());
            } else if (obj instanceof java.util.Date) {
                p.setDate(i + 1, (Date) obj);
            } else if (obj instanceof Timestamp) {
                p.setTimestamp(i + 1, (Timestamp) obj);
            } else if (obj instanceof Boolean) {
                p.setBoolean(i + 1, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Float) {
                p.setFloat(i + 1, ((Float) obj).floatValue());
            } else if (obj instanceof Double) {
                p.setDouble(i + 1, ((Double) obj).doubleValue());
            } else if (obj instanceof Long) {
                p.setLong(i + 1, ((Long) obj).longValue());
            }
        }
    }

    private Connection createConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://" + this.connectionUrl + ":" + this.port + "/" + this.database, this.user, this.password);
        return con;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String connectionUrl;

        private String database;

        private String user;

        private String password;

        private Integer port;

        public Builder withUrl(String url) {
            this.connectionUrl = url;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public MySQL create() {
            Preconditions.checkNotNull(this.connectionUrl, "Connection URL ist falsch");
            Preconditions.checkNotNull(this.database, "Database ist falsch");
            Preconditions.checkNotNull(this.user, "Username ist falsch");
            Preconditions.checkNotNull(this.password, "Password ist falsch");
            Preconditions.checkNotNull(this.port, "Port ist falsch");
            return new MySQL(this.connectionUrl, this.database, this.user, this.password, this.port);
        }
    }
}
