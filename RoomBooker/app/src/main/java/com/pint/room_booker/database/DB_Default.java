package com.pint.room_booker.database;

public class DB_Default {
    protected String _message;
    protected boolean _status;

    public DB_Default()
    {
        this._status = true;
        this._message = "Está tudo OK!";
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public boolean get_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }
}
