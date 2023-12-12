package back;

import back.db.*;

public class ControlerBack {

    private DB db;

    public ControlerBack() {
        this.db = DB.getInstance();
    }

    public DB getDb() {
        return this.db;
    }


}
