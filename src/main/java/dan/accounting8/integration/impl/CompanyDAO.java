package dan.accounting8.integration.impl;

import dan.accounting8.model.Address;
import dan.accounting8.model.Company;
import dan.accounting8.model.CompanyId;
import dan.accounting8.model.Document;
import dan.accounting8.model.DocumentId;
import dan.accounting8.model.DocumentType;
import dan.accounting8.util.AccException;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class CompanyDAO {

    static int keyC = 1;

    private final NavigableMap<CompanyId, Company> documentMapById = new TreeMap<>();
    public static final CompanyDAO instance = new CompanyDAO();

    private CompanyDAO() {
        try {
            create("CEZ", "elektro", Optional.empty());
        } catch (AccException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(String name, String description, Optional<Address> address) throws AccException {
        Company d = new Company(new CompanyId(keyC++), name, description, address);
        documentMapById.put(d.getId(), d);
    }

    public List<Company> getAll() throws AccException {
        return documentMapById.values().stream().collect(Collectors.toList());
    }

    public Optional<Company> getByName(String name) throws AccException {
        return documentMapById.values().stream()
                .filter((d) -> d.getName().equals(name)).findFirst();
    }

    public Company getById(CompanyId id) throws AccException {
        return documentMapById.values().stream()
                .filter((d) -> d.getId().equals(id)).findFirst().get();
    }

    public void update(CompanyId id, String name, String description,
            Optional<Address> address) throws AccException {
        Company d = new Company(id, name, description, address);
        documentMapById.replace(id, d);
    }

    public void delete(CompanyId id) throws AccException {
        documentMapById.remove(id);
    }

}
