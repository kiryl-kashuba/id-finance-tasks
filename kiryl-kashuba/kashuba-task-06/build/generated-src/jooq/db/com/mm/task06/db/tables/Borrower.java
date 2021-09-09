/*
 * This file is generated by jOOQ.
 */
package com.mm.task06.db.tables;


import com.mm.task06.db.IdfOnboarding;
import com.mm.task06.db.Keys;
import com.mm.task06.db.tables.records.BorrowerRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Borrower extends TableImpl<BorrowerRecord> {

    private static final long serialVersionUID = 1252355808;

    /**
     * The reference instance of <code>idf_onboarding.borrower</code>
     */
    public static final Borrower BORROWER = new Borrower();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BorrowerRecord> getRecordType() {
        return BorrowerRecord.class;
    }

    /**
     * The column <code>idf_onboarding.borrower.id</code>.
     */
    public final TableField<BorrowerRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>idf_onboarding.borrower.personal_data_id</code>.
     */
    public final TableField<BorrowerRecord, Long> PERSONAL_DATA_ID = createField(DSL.name("personal_data_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>idf_onboarding.borrower.user_account_id</code>.
     */
    public final TableField<BorrowerRecord, Long> USER_ACCOUNT_ID = createField(DSL.name("user_account_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>idf_onboarding.borrower.work_id</code>.
     */
    public final TableField<BorrowerRecord, Long> WORK_ID = createField(DSL.name("work_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>idf_onboarding.borrower</code> table reference
     */
    public Borrower() {
        this(DSL.name("borrower"), null);
    }

    /**
     * Create an aliased <code>idf_onboarding.borrower</code> table reference
     */
    public Borrower(String alias) {
        this(DSL.name(alias), BORROWER);
    }

    /**
     * Create an aliased <code>idf_onboarding.borrower</code> table reference
     */
    public Borrower(Name alias) {
        this(alias, BORROWER);
    }

    private Borrower(Name alias, Table<BorrowerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Borrower(Name alias, Table<BorrowerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Borrower(Table<O> child, ForeignKey<O, BorrowerRecord> key) {
        super(child, key, BORROWER);
    }

    @Override
    public Schema getSchema() {
        return IdfOnboarding.IDF_ONBOARDING;
    }

    @Override
    public Identity<BorrowerRecord, Long> getIdentity() {
        return Keys.IDENTITY_BORROWER;
    }

    @Override
    public UniqueKey<BorrowerRecord> getPrimaryKey() {
        return Keys.KEY_BORROWER_PRIMARY;
    }

    @Override
    public List<UniqueKey<BorrowerRecord>> getKeys() {
        return Arrays.<UniqueKey<BorrowerRecord>>asList(Keys.KEY_BORROWER_PRIMARY, Keys.KEY_BORROWER_BORROWER_USER_ACCOUNT_ID_UINDEX);
    }

    @Override
    public List<ForeignKey<BorrowerRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BorrowerRecord, ?>>asList(Keys.FK_BORROWER_PERS_DATA_ID_PERS_DATA_ID, Keys.FK_BORROWER_USER_ACCOUNT_ID, Keys.FK_BORROWER_WORK_ID_WORK_ID);
    }

    public PersonalData personalData() {
        return new PersonalData(this, Keys.FK_BORROWER_PERS_DATA_ID_PERS_DATA_ID);
    }

    public UserAccount userAccount() {
        return new UserAccount(this, Keys.FK_BORROWER_USER_ACCOUNT_ID);
    }

    public Work work() {
        return new Work(this, Keys.FK_BORROWER_WORK_ID_WORK_ID);
    }

    @Override
    public Borrower as(String alias) {
        return new Borrower(DSL.name(alias), this);
    }

    @Override
    public Borrower as(Name alias) {
        return new Borrower(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Borrower rename(String name) {
        return new Borrower(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Borrower rename(Name name) {
        return new Borrower(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Long, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
