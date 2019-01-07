package dan.accounting8.integration.impl;

import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.Company;
import dan.accounting8.model.Document;
import dan.accounting8.model.DocumentId;
import dan.accounting8.model.DocumentType;
import dan.accounting8.util.AccException;
import java.time.LocalDate;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class DocumentDAO {

    public static final DocumentDAO instance = new DocumentDAO();

    static int keyC = 1;

    private final NavigableMap<DocumentId, dan.accounting8.model.Document> documentMapByID = new TreeMap<>();

    private DocumentDAO() {
        try {
            create(DocumentType.INVOICE, "F1", CompanyDAO.instance.getByName("CEZ"),
                    Optional.empty(), Optional.of(LocalDate.now()), "bla");
            create(DocumentType.BANK_STATEMENT, "V1", CompanyDAO.instance.getByName("FIO"),
                    Optional.empty(), Optional.of(LocalDate.now()), "bla");

        } catch (AccException ex) {
            Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(DocumentType type, String name, Optional<Company> optCompany,
            Optional<AnalAcc> optAccount, Optional<LocalDate> optDate, String description) {
        Document d = new Document(new DocumentId(keyC++), type, name, optCompany, optAccount,
                optDate, description);
        documentMapByID.put(d.getId(), d);
    }

    public void update(DocumentId id, DocumentType type, String name, Optional<Company> optCompany,
            Optional<AnalAcc> optAccount, Optional<LocalDate> optDate, String description) {
        Document d = new Document(id, type, name, optCompany, optAccount,
                optDate, description);
        documentMapByID.replace(d.getId(), d);
    }

//    public void createInvoice(String name, String description, Optional<Company> company, Optional<LocalDate> dueDate) throws AccException {
//        Document d = new Invoice(new DocumentId(keyC++), name, description, company, dueDate);
//        documentMapByID.put(d.getId(), d);
//    }
//    public void updateInvoice(DocumentId id, String name, String description, Optional<Company> company, Optional<LocalDate> dueDate) throws AccException {
//        Document d = new Invoice(id, name, description, company, dueDate);
//        documentMapByID.replace(d.getId(), d);
//    }
//    public void createBankStatement(String name, String description) throws AccException {
////        Document d = new Document(new DocumentId(keyC++), name, description, DocumentType.BANK_STATEMENT);
////        documentMapByID.put(d.getId(), d);
//    }
    public List<Document> getAll() throws AccException {
        return documentMapByID.values().stream().collect(Collectors.toList());
    }

    public List<Document> getByRegexp(String regexp) throws AccException {
        return documentMapByID.values().stream()
                .filter(d -> d.getName().matches(regexp))
                .collect(Collectors.toList());
    }

    public Document getById(DocumentId id) throws AccException {
        return documentMapByID.values().stream()
                .filter((d) -> d.getId().equals(id)).findFirst().get();
    }

    public void delete(DocumentId id) throws AccException {
        documentMapByID.remove(id);
    }

}
