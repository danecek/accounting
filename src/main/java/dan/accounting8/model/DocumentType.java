package dan.accounting8.model;

import dan.accounting8.util.Messages;

public enum DocumentType {
    INVOICE(Messages.Faktura.cm()),
    BANK_STATEMENT(Messages.Vypis_z_uctu.cm()),
    ELSE(Messages.Ostatni.cm());

    private DocumentType(String text) {
        this.text = text;
    }

    private String text;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
}
