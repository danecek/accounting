package dan.accounting8.integration;

import dan.accounting8.integration.impl.TransactionDAODefault;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.Document;
import dan.accounting8.model.Transaction;
import dan.accounting8.model.TransactionId;
import dan.accounting8.util.AccException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public abstract class TransactionDAO {

    public static TransactionDAO instance = new TransactionDAODefault();

    public abstract void create(Optional<LocalDate> date, long amount, AnalAcc madati,
            AnalAcc dal, Optional<Document> document, Optional<Document> bindingDocument) throws AccException;

    public abstract void update(TransactionId id, LocalDate date, long amount,
            AnalAcc madati, AnalAcc dal, Optional<Document> document,
            Optional<Document> bindingDocument) throws AccException;

    public abstract void delete(TransactionId id) throws AccException;

    public abstract List<Transaction> getAll() throws AccException;

    public abstract List<Transaction> get(Optional<LocalDate> optOd, Optional<LocalDate> optDo,
            Optional<AnalAcc> acc, Optional<Document> optDocument, Optional<Document> optRelatedDocument) throws AccException;

    public abstract List<Transaction> getInits(Optional<AnalAcc> acc) throws AccException;
}
