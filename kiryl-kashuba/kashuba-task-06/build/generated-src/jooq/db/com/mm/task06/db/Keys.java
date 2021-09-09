/*
 * This file is generated by jOOQ.
 */
package com.mm.task06.db;


import com.mm.task06.db.tables.Aud;
import com.mm.task06.db.tables.AudUserAccount;
import com.mm.task06.db.tables.Borrower;
import com.mm.task06.db.tables.BorrowerDocument;
import com.mm.task06.db.tables.Credit;
import com.mm.task06.db.tables.PersonalData;
import com.mm.task06.db.tables.UserAccount;
import com.mm.task06.db.tables.Work;
import com.mm.task06.db.tables.records.AudRecord;
import com.mm.task06.db.tables.records.AudUserAccountRecord;
import com.mm.task06.db.tables.records.BorrowerDocumentRecord;
import com.mm.task06.db.tables.records.BorrowerRecord;
import com.mm.task06.db.tables.records.CreditRecord;
import com.mm.task06.db.tables.records.PersonalDataRecord;
import com.mm.task06.db.tables.records.UserAccountRecord;
import com.mm.task06.db.tables.records.WorkRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>idf_onboarding</code> schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AudUserAccountRecord, Long> IDENTITY_AUD_USER_ACCOUNT = Identities0.IDENTITY_AUD_USER_ACCOUNT;
    public static final Identity<BorrowerRecord, Long> IDENTITY_BORROWER = Identities0.IDENTITY_BORROWER;
    public static final Identity<BorrowerDocumentRecord, Long> IDENTITY_BORROWER_DOCUMENT = Identities0.IDENTITY_BORROWER_DOCUMENT;
    public static final Identity<CreditRecord, Long> IDENTITY_CREDIT = Identities0.IDENTITY_CREDIT;
    public static final Identity<PersonalDataRecord, Long> IDENTITY_PERSONAL_DATA = Identities0.IDENTITY_PERSONAL_DATA;
    public static final Identity<UserAccountRecord, Long> IDENTITY_USER_ACCOUNT = Identities0.IDENTITY_USER_ACCOUNT;
    public static final Identity<WorkRecord, Long> IDENTITY_WORK = Identities0.IDENTITY_WORK;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AudRecord> KEY_AUD_PRIMARY = UniqueKeys0.KEY_AUD_PRIMARY;
    public static final UniqueKey<AudUserAccountRecord> KEY_AUD_USER_ACCOUNT_PRIMARY = UniqueKeys0.KEY_AUD_USER_ACCOUNT_PRIMARY;
    public static final UniqueKey<BorrowerRecord> KEY_BORROWER_PRIMARY = UniqueKeys0.KEY_BORROWER_PRIMARY;
    public static final UniqueKey<BorrowerRecord> KEY_BORROWER_BORROWER_USER_ACCOUNT_ID_UINDEX = UniqueKeys0.KEY_BORROWER_BORROWER_USER_ACCOUNT_ID_UINDEX;
    public static final UniqueKey<BorrowerDocumentRecord> KEY_BORROWER_DOCUMENT_PRIMARY = UniqueKeys0.KEY_BORROWER_DOCUMENT_PRIMARY;
    public static final UniqueKey<BorrowerDocumentRecord> KEY_BORROWER_DOCUMENT_DOCUMENT_ID = UniqueKeys0.KEY_BORROWER_DOCUMENT_DOCUMENT_ID;
    public static final UniqueKey<CreditRecord> KEY_CREDIT_PRIMARY = UniqueKeys0.KEY_CREDIT_PRIMARY;
    public static final UniqueKey<PersonalDataRecord> KEY_PERSONAL_DATA_PRIMARY = UniqueKeys0.KEY_PERSONAL_DATA_PRIMARY;
    public static final UniqueKey<UserAccountRecord> KEY_USER_ACCOUNT_PRIMARY = UniqueKeys0.KEY_USER_ACCOUNT_PRIMARY;
    public static final UniqueKey<WorkRecord> KEY_WORK_PRIMARY = UniqueKeys0.KEY_WORK_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AudUserAccountRecord, AudRecord> FK_AUD_USER_ACCOUNT_REV_AUD_ID = ForeignKeys0.FK_AUD_USER_ACCOUNT_REV_AUD_ID;
    public static final ForeignKey<AudUserAccountRecord, AudRecord> FK_AUD_USER_ACCOUNT_REVEND_AUD_ID = ForeignKeys0.FK_AUD_USER_ACCOUNT_REVEND_AUD_ID;
    public static final ForeignKey<BorrowerRecord, PersonalDataRecord> FK_BORROWER_PERS_DATA_ID_PERS_DATA_ID = ForeignKeys0.FK_BORROWER_PERS_DATA_ID_PERS_DATA_ID;
    public static final ForeignKey<BorrowerRecord, UserAccountRecord> FK_BORROWER_USER_ACCOUNT_ID = ForeignKeys0.FK_BORROWER_USER_ACCOUNT_ID;
    public static final ForeignKey<BorrowerRecord, WorkRecord> FK_BORROWER_WORK_ID_WORK_ID = ForeignKeys0.FK_BORROWER_WORK_ID_WORK_ID;
    public static final ForeignKey<BorrowerDocumentRecord, BorrowerRecord> FK_BORROWER_DOCUMENT_BORROWER_ID = ForeignKeys0.FK_BORROWER_DOCUMENT_BORROWER_ID;
    public static final ForeignKey<CreditRecord, BorrowerRecord> FK_CREDIT_BORROWER_ID = ForeignKeys0.FK_CREDIT_BORROWER_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AudUserAccountRecord, Long> IDENTITY_AUD_USER_ACCOUNT = Internal.createIdentity(AudUserAccount.AUD_USER_ACCOUNT, AudUserAccount.AUD_USER_ACCOUNT.ID_PRIMARY);
        public static Identity<BorrowerRecord, Long> IDENTITY_BORROWER = Internal.createIdentity(Borrower.BORROWER, Borrower.BORROWER.ID);
        public static Identity<BorrowerDocumentRecord, Long> IDENTITY_BORROWER_DOCUMENT = Internal.createIdentity(BorrowerDocument.BORROWER_DOCUMENT, BorrowerDocument.BORROWER_DOCUMENT.ID);
        public static Identity<CreditRecord, Long> IDENTITY_CREDIT = Internal.createIdentity(Credit.CREDIT, Credit.CREDIT.ID);
        public static Identity<PersonalDataRecord, Long> IDENTITY_PERSONAL_DATA = Internal.createIdentity(PersonalData.PERSONAL_DATA, PersonalData.PERSONAL_DATA.ID);
        public static Identity<UserAccountRecord, Long> IDENTITY_USER_ACCOUNT = Internal.createIdentity(UserAccount.USER_ACCOUNT, UserAccount.USER_ACCOUNT.ID);
        public static Identity<WorkRecord, Long> IDENTITY_WORK = Internal.createIdentity(Work.WORK, Work.WORK.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AudRecord> KEY_AUD_PRIMARY = Internal.createUniqueKey(Aud.AUD, "KEY_aud_PRIMARY", new TableField[] { Aud.AUD.ID }, true);
        public static final UniqueKey<AudUserAccountRecord> KEY_AUD_USER_ACCOUNT_PRIMARY = Internal.createUniqueKey(AudUserAccount.AUD_USER_ACCOUNT, "KEY_aud_user_account_PRIMARY", new TableField[] { AudUserAccount.AUD_USER_ACCOUNT.ID_PRIMARY }, true);
        public static final UniqueKey<BorrowerRecord> KEY_BORROWER_PRIMARY = Internal.createUniqueKey(Borrower.BORROWER, "KEY_borrower_PRIMARY", new TableField[] { Borrower.BORROWER.ID }, true);
        public static final UniqueKey<BorrowerRecord> KEY_BORROWER_BORROWER_USER_ACCOUNT_ID_UINDEX = Internal.createUniqueKey(Borrower.BORROWER, "KEY_borrower_BORROWER_USER_ACCOUNT_ID_UINDEX", new TableField[] { Borrower.BORROWER.USER_ACCOUNT_ID }, true);
        public static final UniqueKey<BorrowerDocumentRecord> KEY_BORROWER_DOCUMENT_PRIMARY = Internal.createUniqueKey(BorrowerDocument.BORROWER_DOCUMENT, "KEY_borrower_document_PRIMARY", new TableField[] { BorrowerDocument.BORROWER_DOCUMENT.ID }, true);
        public static final UniqueKey<BorrowerDocumentRecord> KEY_BORROWER_DOCUMENT_DOCUMENT_ID = Internal.createUniqueKey(BorrowerDocument.BORROWER_DOCUMENT, "KEY_borrower_document_document_id", new TableField[] { BorrowerDocument.BORROWER_DOCUMENT.DOCUMENT_ID }, true);
        public static final UniqueKey<CreditRecord> KEY_CREDIT_PRIMARY = Internal.createUniqueKey(Credit.CREDIT, "KEY_credit_PRIMARY", new TableField[] { Credit.CREDIT.ID }, true);
        public static final UniqueKey<PersonalDataRecord> KEY_PERSONAL_DATA_PRIMARY = Internal.createUniqueKey(PersonalData.PERSONAL_DATA, "KEY_personal_data_PRIMARY", new TableField[] { PersonalData.PERSONAL_DATA.ID }, true);
        public static final UniqueKey<UserAccountRecord> KEY_USER_ACCOUNT_PRIMARY = Internal.createUniqueKey(UserAccount.USER_ACCOUNT, "KEY_user_account_PRIMARY", new TableField[] { UserAccount.USER_ACCOUNT.ID }, true);
        public static final UniqueKey<WorkRecord> KEY_WORK_PRIMARY = Internal.createUniqueKey(Work.WORK, "KEY_work_PRIMARY", new TableField[] { Work.WORK.ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AudUserAccountRecord, AudRecord> FK_AUD_USER_ACCOUNT_REV_AUD_ID = Internal.createForeignKey(Keys.KEY_AUD_PRIMARY, AudUserAccount.AUD_USER_ACCOUNT, "fk_aud_user_account_rev_aud_id", new TableField[] { AudUserAccount.AUD_USER_ACCOUNT.REV }, true);
        public static final ForeignKey<AudUserAccountRecord, AudRecord> FK_AUD_USER_ACCOUNT_REVEND_AUD_ID = Internal.createForeignKey(Keys.KEY_AUD_PRIMARY, AudUserAccount.AUD_USER_ACCOUNT, "fk_aud_user_account_revend_aud_id", new TableField[] { AudUserAccount.AUD_USER_ACCOUNT.REVEND }, true);
        public static final ForeignKey<BorrowerRecord, PersonalDataRecord> FK_BORROWER_PERS_DATA_ID_PERS_DATA_ID = Internal.createForeignKey(Keys.KEY_PERSONAL_DATA_PRIMARY, Borrower.BORROWER, "fk_borrower_pers_data_id_pers_data_id", new TableField[] { Borrower.BORROWER.PERSONAL_DATA_ID }, true);
        public static final ForeignKey<BorrowerRecord, UserAccountRecord> FK_BORROWER_USER_ACCOUNT_ID = Internal.createForeignKey(Keys.KEY_USER_ACCOUNT_PRIMARY, Borrower.BORROWER, "fk_borrower_user_account_id", new TableField[] { Borrower.BORROWER.USER_ACCOUNT_ID }, true);
        public static final ForeignKey<BorrowerRecord, WorkRecord> FK_BORROWER_WORK_ID_WORK_ID = Internal.createForeignKey(Keys.KEY_WORK_PRIMARY, Borrower.BORROWER, "fk_borrower_work_id_work_id", new TableField[] { Borrower.BORROWER.WORK_ID }, true);
        public static final ForeignKey<BorrowerDocumentRecord, BorrowerRecord> FK_BORROWER_DOCUMENT_BORROWER_ID = Internal.createForeignKey(Keys.KEY_BORROWER_PRIMARY, BorrowerDocument.BORROWER_DOCUMENT, "fk_borrower_document_borrower_id", new TableField[] { BorrowerDocument.BORROWER_DOCUMENT.BORROWER_ID }, true);
        public static final ForeignKey<CreditRecord, BorrowerRecord> FK_CREDIT_BORROWER_ID = Internal.createForeignKey(Keys.KEY_BORROWER_PRIMARY, Credit.CREDIT, "fk_credit_borrower_id", new TableField[] { Credit.CREDIT.BORROWER_ID }, true);
    }
}
