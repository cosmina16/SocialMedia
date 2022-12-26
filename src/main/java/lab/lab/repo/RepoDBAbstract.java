package lab.lab.repo;

import lab.lab.domain.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public abstract class RepoDBAbstract<T extends Entity> implements RepoGeneric<T>{

    String url;
    String username;
    String password;
    public RepoDBAbstract(String url, String username, String password)  {
        this.password = password;
        this.url = url;
        this.username = username;
    }

}
