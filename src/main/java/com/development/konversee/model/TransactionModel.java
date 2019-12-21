package com.development.konversee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @NotNull
    @Column(name = "tanggalDibuka", nullable = false)
    private LocalDate tanggalDibuka;

    @JsonIgnore
    @Column(name = "tanggalDitutup")
    private LocalDate tanggalDitutup;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "noHpTujuan", nullable = false)
    private BigInteger noHpTujuan;

    @NotNull
    @Column(name = "userNameTujuan", nullable = false)
    private BigInteger userNameTujuan;

    @NotNull
    @Column(name = "nominal", nullable = false)
    private BigInteger nominal;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipeAkunTujuan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AccountTypeModel tipeAkunTujuan;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuidUser", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersModel user;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuidBroker", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersModel broker;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "accountOriginId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AccountModel origin;

    public AccountModel getOrigin() {
        return origin;
    }

    public void setOrigin(AccountModel origin) {
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalDibuka() {
        return tanggalDibuka;
    }

    public void setTanggalDibuka(LocalDate tanggalDibuka) {
        this.tanggalDibuka = tanggalDibuka;
    }

    public LocalDate getTanggalDitutup() {
        return tanggalDitutup;
    }

    public void setTanggalDitutup(LocalDate tanggalDitutup) {
        this.tanggalDitutup = tanggalDitutup;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigInteger getNoHpTujuan() {
        return noHpTujuan;
    }

    public void setNoHpTujuan(BigInteger noHpTujuan) {
        this.noHpTujuan = noHpTujuan;
    }

    public BigInteger getUserNameTujuan() {
        return userNameTujuan;
    }

    public void setUserNameTujuan(BigInteger userNameTujuan) {
        this.userNameTujuan = userNameTujuan;
    }

    public AccountTypeModel getTipeAkunTujuan() {
        return tipeAkunTujuan;
    }

    public void setTipeAkunTujuan(AccountTypeModel tipeAkunTujuan) {
        this.tipeAkunTujuan = tipeAkunTujuan;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public UsersModel getBroker() {
        return broker;
    }

    public void setBroker(UsersModel broker) {
        this.broker = broker;
    }
}