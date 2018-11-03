package vadooss1_homework.user_service;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static vadooss1_homework.user_service.ConfigLoader.*;


public class MySQLconnector {
    final static Logger logger = Logger.getLogger(MySQLconnector.class);

    public MySQLconnector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            logger.error("Invalid driver", e);
        }
    }

    public Connection getConnection(){
        Connection connection=null;
        try{
         connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        }catch(SQLException e){
            logger.error("Cannot plug in...", e);
        }
        return connection;
    }

    public void resetDB(Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA IF EXISTS `user_service`");
            statement.execute("CREATE SCHEMA IF NOT EXISTS `user_service` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin");
            statement.execute("USE `user_service`");
            statement.execute("CREATE TABLE IF NOT EXISTS `user_service`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `status` INT NULL,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `password` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  `sex` TINYINT NULL,\n" +
                    "  `message_result` VARCHAR(45) NULL DEFAULT 'nomessage',\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8\n" +
                    "COLLATE = utf8_bin");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
