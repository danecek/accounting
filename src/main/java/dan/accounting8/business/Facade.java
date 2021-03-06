package dan.accounting8.business;

import java.util.List;
import dan.accounting8.business.balance.BalanceItem;
import dan.accounting8.business.balance.Balance;
import dan.accounting8.integration.AccountDAO;
import dan.accounting8.integration.TransactionDAO;
import dan.accounting8.integration.impl.DocumentDAO;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.AccGroup;
import dan.accounting8.model.AccId;
import dan.accounting8.model.Document;
import dan.accounting8.model.DocumentId;
import dan.accounting8.model.DocumentType;
import dan.accounting8.model.Transaction;
import dan.accounting8.model.TransactionId;
import dan.accounting8.util.AccException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import dan.accounting8.model.Address;

public class Facade {

    public static Facade instance = new Facade();

    public static final String POCATECNI_STAV = "pocatecni stav";

    public void createAccount(AccGroup skupina, String no, String name) throws AccException {
        AccountDAO.instance.create(skupina, no, name);
    }

    public List<AnalAcc> getAllAccounts() throws AccException {
        List<AnalAcc> result = AccountDAO.instance.getAll();
        return result;
    }

    public AnalAcc pur() throws AccException {
        return AccountDAO.instance.getPocatecniUcetRozvazny();
    }

    public void deleteAccount(AccId id) throws AccException {
        AccountDAO.instance.delete(id);
    }

    public List<AnalAcc> getBalanceAccounts() throws AccException {
        return AccountDAO.instance.getBalancing();
    }

    public Optional<AnalAcc> getAccountByNumber(String number) throws AccException {
        return AccountDAO.instance.getByNumber(number);
    }

    public List<AnalAcc> getAccountsByGroup(AccGroup accg) throws AccException {
        return AccountDAO.instance.getByGroup(accg);
    }

    public List<AnalAcc> getAccountsByClass(AccGroup accg) throws AccException {
        return AccountDAO.instance.getByClass(accg);
    }

    public void createTransaction(long amount, AnalAcc madati, AnalAcc dal, Optional<Document> document, Optional<Document> bindingDocument) throws AccException {
        TransactionDAO.instance.create(amount, madati,
                dal, document, bindingDocument);
    }

    public List<Transaction> getAllTransactions() throws AccException {
        return TransactionDAO.instance.getAll();
    }

    public void updateTransaction(TransactionId id, long amount,
            AnalAcc madati, AnalAcc dal, Optional<Document> document,
            Optional<Document> bindingDocument) throws AccException {
        TransactionDAO.instance.update(id, amount, madati, dal,
                document, bindingDocument);
    }

    public void deleteTransaction(TransactionId id) throws AccException {
        TransactionDAO.instance.delete(id);
    }

    public List<Transaction> getTransactions(Optional<LocalDate> optOd, Optional<LocalDate> optDo,
            Optional<AnalAcc> acc, Optional<Document> optDocument, Optional<Document> optRelatedDocument) throws AccException {
        return TransactionDAO.instance.get(optOd, optDo, acc, optDocument, optRelatedDocument);
    }

    public List<BalanceItem> getAllBalanceItems(Month month) throws AccException {
        return Balance.instance.createBalance(month);
    }

    public void createInit(long amount, AnalAcc acc, MadatiDal madatiDal) throws AccException {
        switch (madatiDal) {
            case MA_DATI: {
                TransactionDAO.instance
                        .create(amount, acc, pur(), Optional.empty(), Optional.empty());
                break;
            }
            case DAL: {
                TransactionDAO.instance
                        .create(amount, pur(), acc, Optional.empty(), Optional.empty());
            }
        }
    }

    public List<Transaction> getInits(Optional<AnalAcc> acc) throws AccException {
        return TransactionDAO.instance.getInits(acc);
    }

    // Document ***************************************************************
    public void createDocument(DocumentType type, String name,
            LocalDate date, String description) {
        DocumentDAO.instance.create(type, name, date, description);
    }

    public void updateDocument(DocumentId id, DocumentType type, String name,
            LocalDate date, String description) {
        DocumentDAO.instance.update(id, type, name, date, 
                description);
    }

    public void deleteDocument(DocumentId id) throws AccException {
        DocumentDAO.instance.delete(id);
    }

    public List<Document> getAllDocuments() throws AccException {
        return DocumentDAO.instance.getAll();
    }

    public List<Document> getDocumentByName(String name) throws AccException {
        return DocumentDAO.instance.getByRegexp(name);
    }

}
