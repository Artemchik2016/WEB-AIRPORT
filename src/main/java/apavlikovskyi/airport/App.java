package apavlikovskyi.airport;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;

public class App {

    public static void main(String[] args) {
        DataBaseConnection.migrate();

    }
}
