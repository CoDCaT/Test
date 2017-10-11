package com.google.developer.bugmaster.data.specification;


import com.google.developer.bugmaster.data.BugsDbContract;

public class InsectsSqlSpecification implements SQLiteSpecification {
    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s ORDER BY `%2$s` DESC;",
                BugsDbContract.bugsEntry.TABLE_NAME,
                BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME
        );
    }
}
