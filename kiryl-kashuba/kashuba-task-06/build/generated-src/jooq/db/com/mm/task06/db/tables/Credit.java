/*
 * This file is generated by jOOQ.
 */
package com.mm.task06.db.tables;


import com.mm.db.jooq.datatype.CreditStatusConverter;
import com.mm.platform.jooq.datatype.LocalDateTimeConverter;
import com.mm.task06.db.IdfOnboarding;
import com.mm.task06.db.Keys;
import com.mm.task06.db.tables.records.CreditRecord;
import com.mm.types.CreditStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class Credit extends TableImpl<CreditRecord> {

    private static final long serialVersionUID = -2095875964;

    /**
     * The reference instance of <code>idf_onboarding.credit</code>
     */
    public static final Credit CREDIT = new Credit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CreditRecord> getRecordType() {
        return CreditRecord.class;
    }

    /**
     * The column <code>idf_onboarding.credit.id</code>.
     */
    public final TableField<CreditRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>idf_onboarding.credit.amount_to_pay</code>.
     */
    public final TableField<CreditRecord, Double> AMOUNT_TO_PAY = createField(DSL.name("amount_to_pay"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>idf_onboarding.credit.credit_count_days</code>.
     */
    public final TableField<CreditRecord, Integer> CREDIT_COUNT_DAYS = createField(DSL.name("credit_count_days"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>idf_onboarding.credit.date_requested</code>.
     */
    public final TableField<CreditRecord, LocalDateTime> DATE_REQUESTED = createField(DSL.name("date_requested"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new LocalDateTimeConverter());

    /**
     * The column <code>idf_onboarding.credit.initial_amount</code>.
     */
    public final TableField<CreditRecord, Double> INITIAL_AMOUNT = createField(DSL.name("initial_amount"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>idf_onboarding.credit.percent_per_day</code>.
     */
    public final TableField<CreditRecord, Double> PERCENT_PER_DAY = createField(DSL.name("percent_per_day"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>idf_onboarding.credit.status</code>.
     */
    public final TableField<CreditRecord, CreditStatus> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "", new CreditStatusConverter());

    /**
     * The column <code>idf_onboarding.credit.borrower_id</code>.
     */
    public final TableField<CreditRecord, Long> BORROWER_ID = createField(DSL.name("borrower_id"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>idf_onboarding.credit</code> table reference
     */
    public Credit() {
        this(DSL.name("credit"), null);
    }

    /**
     * Create an aliased <code>idf_onboarding.credit</code> table reference
     */
    public Credit(String alias) {
        this(DSL.name(alias), CREDIT);
    }

    /**
     * Create an aliased <code>idf_onboarding.credit</code> table reference
     */
    public Credit(Name alias) {
        this(alias, CREDIT);
    }

    private Credit(Name alias, Table<CreditRecord> aliased) {
        this(alias, aliased, null);
    }

    private Credit(Name alias, Table<CreditRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Credit(Table<O> child, ForeignKey<O, CreditRecord> key) {
        super(child, key, CREDIT);
    }

    @Override
    public Schema getSchema() {
        return IdfOnboarding.IDF_ONBOARDING;
    }

    @Override
    public Identity<CreditRecord, Long> getIdentity() {
        return Keys.IDENTITY_CREDIT;
    }

    @Override
    public UniqueKey<CreditRecord> getPrimaryKey() {
        return Keys.KEY_CREDIT_PRIMARY;
    }

    @Override
    public List<UniqueKey<CreditRecord>> getKeys() {
        return Arrays.<UniqueKey<CreditRecord>>asList(Keys.KEY_CREDIT_PRIMARY);
    }

    @Override
    public List<ForeignKey<CreditRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CreditRecord, ?>>asList(Keys.FK_CREDIT_BORROWER_ID);
    }

    public Borrower borrower() {
        return new Borrower(this, Keys.FK_CREDIT_BORROWER_ID);
    }

    @Override
    public Credit as(String alias) {
        return new Credit(DSL.name(alias), this);
    }

    @Override
    public Credit as(Name alias) {
        return new Credit(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Credit rename(String name) {
        return new Credit(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Credit rename(Name name) {
        return new Credit(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Double, Integer, LocalDateTime, Double, Double, CreditStatus, Long> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}