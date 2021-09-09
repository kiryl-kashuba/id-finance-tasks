package com.mm.task04.repository;

import static com.mm.task04.db.tables.UserAccount.USER_ACCOUNT;

import com.mm.task04.converter.UserAccountConverter;
import com.mm.task04.db.tables.pojos.UserAccount;
import com.mm.task04.db.tables.records.UserAccountRecord;
import com.mm.task04.model.UserAccountModel;
import java.time.LocalDateTime;
import org.jooq.DSLContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JooqUserAccountRepository implements UserAccountRepository {

  private final DSLContext dslContext;
  private final UserAccountConverter userAccountConverter;

  public JooqUserAccountRepository(DSLContext dslContext, UserAccountConverter userAccountConverter) {
    this.dslContext = dslContext;
    this.userAccountConverter = userAccountConverter;
  }

  @Override
  public Mono<UserAccountModel> get(long id) {
    return Mono.fromSupplier(() -> dslContext
        .select()
        .from(USER_ACCOUNT)
        .where(USER_ACCOUNT.ID.eq(id))
        .fetchOne()
        .into(UserAccount.class))
        .map(userAccountConverter::convertToModel);
  }

  @Override
  public Flux<UserAccountModel> getList() {
    return Flux.defer(() -> Flux.fromIterable(dslContext
        .select()
        .from(USER_ACCOUNT)
        .fetch()
        .into(UserAccount.class))
        .map(userAccountConverter::convertToModel));
  }

  @Override
  public Mono<Long> create(UserAccountModel userAccountModel) {
    return Mono.fromSupplier(() -> {
      UserAccountRecord userRecord =
          dslContext.newRecord(USER_ACCOUNT, userAccountConverter.convertFromModel(userAccountModel))
              .setDateCreated(LocalDateTime.now());
      userRecord.store();
      return userRecord.getId();
    });
  }

  @Override
  public Mono<Long> update(UserAccountModel userAccountModel) {
    return Mono.fromSupplier(() -> {
      UserAccountRecord userRecord =
          dslContext.newRecord(USER_ACCOUNT, userAccountConverter.convertFromModel(userAccountModel));
      userRecord.update();
      return userRecord.getId();
    });
  }
}
