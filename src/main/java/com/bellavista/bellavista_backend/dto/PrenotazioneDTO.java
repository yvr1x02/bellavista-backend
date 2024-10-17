package com.bellavista.bellavista_backend.dto;

import java.time.LocalDate;
import java.util.UUID;

public class PrenotazioneDTO {

    private UUID id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private boolean confermata;
    private String note;
    private OspiteDTO ospite;



    public PrenotazioneDTO() {}

    public PrenotazioneDTO(UUID id, LocalDate dataInizio, LocalDate dataFine, boolean confermata, String note, OspiteDTO ospite) {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.confermata = confermata;
        this.note = note;
        this.ospite = ospite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isConfermata() {
        return confermata;
    }

    public void setConfermata(boolean confermata) {
        this.confermata = confermata;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OspiteDTO getOspite() {
        return ospite;
    }

    public void setOspite(OspiteDTO ospite) {
        this.ospite = ospite;
    }
}
