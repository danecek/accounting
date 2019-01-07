package dan.accounting8.model;

import dan.accounting8.business.Global;
import java.time.LocalDate;
import java.util.Optional;

public class Transaction {

    public Transaction(TransactionId id, Optional<LocalDate> date, long amount,
            AnalAcc maDati, AnalAcc dal, Optional<Document> document,
            Optional<Document> bindingDocument) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.maDati = maDati;
        this.dal = dal;
        this.document = document;
        this.relatedDocument = bindingDocument;
    }

    public final TransactionId id;
    protected final Optional<LocalDate> date;
    public final long amount;
    public final AnalAcc maDati;
    protected final AnalAcc dal;
    protected final Optional<Document> document;
    protected final Optional<Document> relatedDocument;

    /**
     * @return the id
     */
    public TransactionId getId() {
        return id;
    }

    /**
     * @return the date
     */
    public Optional<LocalDate> getDate() {
        return date;
    }

    /**
     * @return the amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @return the maDati
     */
    public AnalAcc getMaDati() {
        return maDati;
    }

    /**
     * @return the dal
     */
    public AnalAcc getDal() {
        return dal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction a = (Transaction) obj;
        return getId().equals(a.getId());

    }

    public Optional<Document> getDocument() {
        return document;
    }

    /**
     * @return the bindingDocument
     */
    public Optional<Document> getRelatedDocument() {
        return relatedDocument;
    }

    public boolean isInit() {
        return !date.isPresent();
    }

}
