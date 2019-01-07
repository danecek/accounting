package dan.accounting8.model;

import java.time.LocalDate;
import java.util.Optional;

public class Document {

    private final DocumentId id;
    private final DocumentType type;
    private final String name;
    private final Optional<Company> optCompany;
    private final Optional<AnalAcc> optAccount;
    private final Optional<LocalDate> optDate;
    private final String description;

    public Document(DocumentId id, DocumentType type, String name, Optional<Company> optCompany, Optional<AnalAcc> optAccount, Optional<LocalDate> optDate, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.optCompany = optCompany;
        this.optAccount = optAccount;
        this.optDate = optDate;
        this.description = description;
    }

    /**
     * @return the id
     */
    public DocumentId getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public DocumentType getType() {
        return type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the optCompany
     */
    public Optional<Company> getOptCompany() {
        return optCompany;
    }

    /**
     * @return the optAccount
     */
    public Optional<AnalAcc> getOptAccount() {
        return optAccount;
    }

    /**
     * @return the optDate
     */
    public Optional<LocalDate> getOptDate() {
        return optDate;
    }

}
