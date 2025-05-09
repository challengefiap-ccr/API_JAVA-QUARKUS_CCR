package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.UserEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@ApplicationScoped
public class UseRepository implements PanacheRepositoryBase<UserEntity, String> {
    public UserEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }

    @ConfigProperty(name = "quarkus.datasource.username")
    private String usuario;

    @ConfigProperty(name = "quarkus.datasource.password")
    private String senha;

    public Connection getConexao() {
        System.out.println(usuario);
        System.out.println(senha);
        try {
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    usuario,
                    senha
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


