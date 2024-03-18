package org.opensingular.dbuserprovider.model;

import org.opensingular.dbuserprovider.persistence.RDBMS;

import java.security.*;

public class QueryConfigurations {

    private String count;
    private String listAll;
    private String findById;
    private String findByUsername;
    private String findBySearchTerm;
    private String findPasswordHash;
    private String hashFunction;
    private PrivateKey findRsaPrivateKey;
    private RDBMS  RDBMS;
    private boolean allowKeycloakDelete;
    private boolean allowDatabaseToOverwriteKeycloak;

    public QueryConfigurations(String count, String listAll, String findById, String findByUsername, String findBySearchTerm, String findPasswordHash, String hashFunction, PrivateKey findRsaPrivateKey, RDBMS RDBMS, boolean allowKeycloakDelete, boolean allowDatabaseToOverwriteKeycloak) {
        this.count = count;
        this.listAll = listAll;
        this.findById = findById;
        this.findByUsername = findByUsername;
        this.findBySearchTerm = findBySearchTerm;
        this.findPasswordHash = findPasswordHash;
        this.hashFunction = hashFunction;
        this.findRsaPrivateKey = findRsaPrivateKey;
        this.RDBMS = RDBMS;
        this.allowKeycloakDelete = allowKeycloakDelete;
        this.allowDatabaseToOverwriteKeycloak = allowDatabaseToOverwriteKeycloak;
    }

    public RDBMS getRDBMS() {
        return RDBMS;
    }

    public String getCount() {
        return count;
    }

    public String getListAll() {
        return listAll;
    }

    public String getFindById() {
        return findById;
    }

    public String getFindByUsername() {
        return findByUsername;
    }

    public String getFindBySearchTerm() {
        return findBySearchTerm;
    }

    public String getFindPasswordHash() {
        return findPasswordHash;
    }

    public String getHashFunction() {
        return hashFunction;
    }

    public PrivateKey getFindRsaPrivateKey() {
        return findRsaPrivateKey;
    }

    public boolean isBlowfish() {
        return hashFunction.toLowerCase().contains("blowfish");
    }

    public boolean getAllowKeycloakDelete() {
        return allowKeycloakDelete;
    }

    public boolean getAllowDatabaseToOverwriteKeycloak() {
        return allowDatabaseToOverwriteKeycloak;
    }
}
