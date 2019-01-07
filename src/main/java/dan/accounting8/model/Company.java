package dan.accounting8.model;

import java.util.Objects;
import java.util.Optional;

public class Company implements Comparable<Company> {

    /**
     * @return the account
     */
    public Optional<AnalAcc> getAccount() {
        return account;
    }

    private final CompanyId id;
    private final String name;
    private final String description;
    private Optional<AnalAcc> account;
    private Optional<Address> address;

    public Company(CompanyId id, String name, String description, Optional<Address> address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    /**
     * @return the id
     */
    public CompanyId getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public String getAddressText() {
        return address.isPresent() ? address.get().toString() : "";
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (!(that instanceof Company)) {
            return false;
        }
        return compareTo((Company) that) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public int compareTo(Company that) {
        return id.compareTo(that.getId());
    }

}
