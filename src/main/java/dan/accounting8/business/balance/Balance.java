package dan.accounting8.business.balance;

import dan.accounting8.business.Facade;
import dan.accounting8.business.Global;
import dan.accounting8.model.AnalAcc;
import dan.accounting8.model.AccGroup;
import dan.accounting8.model.Transaction;
import dan.accounting8.util.AccException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Balance {

    public static final Balance instance = new Balance();

    private Map<AccGroup, BalanceItem> bitems;
    private BalanceItem root;

    public List<BalanceItem> createBalance(Month month) throws AccException {
        bitems = new TreeMap<>();
        root = new BalanceItem();
        Facade.instance.getBalanceAccounts()
                .forEach(((ba) -> insertToTree(Optional.of(ba))));
        sumTransactions(month);
        root.sum();
        return root.appendItemsTo(new ArrayList<>());
    }

    private BalanceItem insertToTree(Optional<AccGroup> insertedItemGroup) {
        if (!insertedItemGroup.isPresent()) {
            return root;
        }
        AccGroup iGroup = insertedItemGroup.get();
        BalanceItem parentItem = insertToTree(iGroup.getOptParent());
        Map<String, BalanceItem> siblings = parentItem.getChildren();
        BalanceItem newItem = siblings.get(iGroup.getNumber());
        if (newItem == null) {
            newItem = new BalanceItem(iGroup);
            bitems.put(iGroup, newItem);
            siblings.put(iGroup.getNumber(), newItem);
        }
        return newItem;
    }

    void add(AnalAcc acc, boolean inMonth, boolean isInit, long amount) {
        if (!acc.isBalanced()) {
            return;
        }
        BalanceItem item = bitems.get(acc);
        if (acc.isActive()) {
            if (isInit) {
                item.addInitAssets(amount);
                item.addFinalAssets(amount);
            } else {
                item.addAssetsSum(amount);
                item.addFinalAssets(amount);
                if (inMonth) {
                    item.addAssets(amount);
                }
            }
        } else {
            if (isInit) {
                item.addInitLiabilities(amount);
                item.addFinalLiabilities(amount);
            } else {
                item.addLiabilitiesSum(amount);
                item.addFinalLiabilities(amount);
                if (inMonth) {
                    item.addLiabilities(amount);
                }
            }
        }
    }

    private void sumTransactions(Month month) throws AccException {
        for (Transaction trans : Facade.instance.getAllTransactions()) {
            LocalDate begin = LocalDate.of(Global.instance.getYear(), month, 1).minusDays(1);
            LocalDate end = begin.plusMonths(1);
            boolean isInit = !trans.getDate().isPresent();
            boolean inMonth = !isInit && trans.getDate().get().isAfter(begin)
                    && trans.getDate().get().isBefore(end);

            add(trans.getDal(), inMonth, isInit, -trans.getAmount());
            add(trans.getMaDati(), inMonth, isInit, +trans.getAmount());

        }

    }

}
