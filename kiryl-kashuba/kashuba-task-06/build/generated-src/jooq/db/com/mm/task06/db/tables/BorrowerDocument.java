/*
 * This file is generated by jOOQ.
 */
package com.mm.task06.db.tables;


import com.mm.platform.jooq.datatype.LocalDateTimeConverter;
import com.mm.task06.db.IdfOnboarding;
import com.mm.task06.db.Keys;
import com.mm.task06.db.tables.records.BorrowerDocumentRecord;

import java.time.LocalDateTime;
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
public class BorrowerDocument extends TableImpl<BorrowerDocumentRecord> {

    private static final long serialVersionUID = -778300801;

    /**
     * The reference instance of <code>idf_onboarding.borrower_document</code>
     */
    public static final BorrowerDocument BORROWER_DOCUMENT = new BorrowerDocument();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BorrowerDocumentRecord> getRecordType() {
        return BorrowerDocumentRecord.class;
    }

    /**
     * The column <code>idf_onboarding.borrower_document.id</code>.
     */
    public final TableField<BorrowerDocumentRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>idf_onboarding.borrower_document.document_id</code>.
     */
    public final TableField<BorrowerDocumentRecord, String> DOCUMENT_ID = createField(DSL.name("document_id"), org.jooq.impl.SQLDataType.VARCHAR(24).nullable(false), this, "");

    /**
     * The column <code>idf_onboarding.borrower_document.borrower_id</code>.
     */
    public final TableField<BorrowerDocumentRecord, Long> BORROWER_ID = createField(DSL.name("borrower_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>idf_onboarding.borrower_document.uploaded_date</code>.
     */
    public final TableField<BorrowerDocumentRecord, LocalDateTime> UPLOADED_DATE = createField(DSL.name("uploaded_date"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "", new LocalDateTimeConverter());

    /**
     * Create a <code>idf_onboarding.borrower_document</code> table reference
     */
    public BorrowerDocument() {
        this(DSL.name("borrower_document"), null);
    }

    /**
     * Create an aliased <code>idf_onboarding.borrower_document</code> table reference
     */
    public BorrowerDocument(String alias) {
        this(DSL.name(alias), BORROWER_DOCUMENT);
    }

    /**
     * Create an aliased <code>idf_onboarding.borrower_document</code> table reference
     */
    public BorrowerDocument(Name alias) {
        this(alias, BORROWER_DOCUMENT);
    }

    private BorrowerDocument(Name alias, Table<BorrowerDocumentRecord> aliased) {
        this(alias, aliased, null);
    }

    private BorrowerDocument(Name alias, Table<BorrowerDocumentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> BorrowerDocument(Table<O> child, ForeignKey<O, BorrowerDocumentRecord> key) {
        super(child, key, BORROWER_DOCUMENT);
    }

    @Override
    public Schema getSchema() {
        return IdfOnboarding.IDF_ONBOARDING;
    }

    @Override
    public Identity<BorrowerDocumentRecord, Long> getIdentity() {
        return Keys.IDENTITY_BORROWER_DOCUMENT;
    }

    @Override
    public UniqueKey<BorrowerDocumentRecord> getPrimaryKey() {
        return Keys.KEY_BORROWER_DOCUMENT_PRIMARY;
    }

    @Override
    public List<UniqueKey<BorrowerDocumentRecord>> getKeys() {
        return Arrays.<UniqueKey<BorrowerDocumentRecord>>asList(Keys.KEY_BORROWER_DOCUMENT_PRIMARY, Keys.KEY_BORROWER_DOCUMENT_DOCUMENT_ID);
    }

    @Override
    public List<ForeignKey<BorrowerDocumentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BorrowerDocumentRecord, ?>>asList(Keys.FK_BORROWER_DOCUMENT_BORROWER_ID);
    }

    public Borrower borrower() {
        return new Borrower(this, Keys.FK_BORROWER_DOCUMENT_BORROWER_ID);
    }

    @Override
    public BorrowerDocument as(String alias) {
        return new BorrowerDocument(DSL.name(alias), this);
    }

    @Override
    public BorrowerDocument as(Name alias) {
        return new BorrowerDocument(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BorrowerDocument rename(String name) {
        return new BorrowerDocument(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BorrowerDocument rename(Name name) {
        return new BorrowerDocument(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, Long, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}