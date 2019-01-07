package dan.accounting8.integration;

import dan.accounting8.integration.impl.AcountDAODefault;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.AccGroup;
import dan.accounting8.model.AccId;
import dan.accounting8.util.AccException;
import java.util.List;
import java.util.Optional;

public abstract class AccountDAO {

    public static AccountDAO instance = new AcountDAODefault();

    public abstract void create(AccGroup skupina, String no, String name) throws AccException;

    public abstract List<AnalAcc> getAll() throws AccException;

    public abstract Optional<AnalAcc> getByNumber(String name) throws AccException;

    public abstract AnalAcc getPocatecniUcetRozvazny() throws AccException;

    public abstract void delete(AccId id) throws AccException;

    public abstract List<AnalAcc> getBalancing() throws AccException;

    public abstract List<AnalAcc> getByGroup(AccGroup accg) throws AccException;

    public abstract List<AnalAcc> getByClass(AccGroup accg) throws AccException;

}
