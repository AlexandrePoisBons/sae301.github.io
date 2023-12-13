package back;

import back.db.*;

public class ControleurBack {

    private DB db;

    public ControleurBack() {
        this.db = DB.getInstance();
    }

    public DB getDb() {
        return this.db;
    }


}
