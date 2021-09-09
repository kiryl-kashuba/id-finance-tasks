# Task 04: JOOQ

## Part 1

Create service that works with users and provides CRUD operations.  \
Use `MySQL` as storage.  \
Use `JOOQ` as tool to access to database.

Use info from `idf-config/task-04.yaml` to obtain access to database.

To generate `JOOQ` sources write next config for `Gradle`:

```groovy
dependencies {
  //I have deleted information that potentially falls under "trade secrets"//
}

apply from: "${rootDir}/_gradle/jooq.gradle"
jooq {
  db(sourceSets.main) {
    generator {
      database {
        includes = 'user_account'
        customTypes {
          customType {
            name = 'LocalDateTimeConverter'
            type = 'java.time.LocalDateTime'
            converter = 'com.mm.platform.jooq.datatype.LocalDateTimeConverter'
          }
        }
        forcedTypes {
          forcedType {
            name = 'LocalDateTimeConverter'
            expression = '.*'
            types = 'datetime|timestamp'
          }
        }
      }
      target {
        packageName = 'com.mm.task04.db'
      }
    }
  }
}
```

Then execute `Gradle` task `generateDbJooqSchemaSource` in the `task-04` project.  \
After task completion you will see generated classes in `build/generated-src/jooq/db` directory.

To obtain `org.jooq.DSLContext` add the next config:

```java
@Bean
public DSLContext dslContext(){
final JooqSettings settings=configurationProvider.bind("onboarding.jooq",JooqSettings.class);
        return DefaultJooqBuilder.of(settings)
        .withDefaultProviders()
        .build();
        }
```

## Part 2

In this part we will work with `aud` tables and `crypted` data in database.

`user_account.mobile_phone` is crypted data in database.

Add these dependencies to your project:

```groovy
//I have deleted information that potentially falls under "trade secrets"//
```

Change config for `JOOQ Generator`:

1. add more tables

```
aud, aud_user_account, borrower, personal_data, work, credit
```

2. use `com.mm.platform.jooq.audit.AuditableJavaGenerator` and configure supertypes for records
3. add converters for custom types:

```
credit.status, work.education, personal_data.maritalStatus
```

All converters are placed in module `:db:db-types`.

4. for crypted data use converter `com.mm.platform.jooq.datatype.CryptStringConverter`.

Finally config should be like:

```groovy
apply from: "${rootDir}/_gradle/jooq.gradle"
jooq {
   db(sourceSets.main) {
      generator {
         name = 'com.mm.platform.jooq.audit.AuditableJavaGenerator'
         database {
            includes = 'user_account|aud_user_account|aud|borrower|personal_data|work|credit'
            customTypes {
               customType {
                  name = 'LocalDateTimeConverter'
                  type = 'java.time.LocalDateTime'
                  converter = 'com.mm.platform.jooq.datatype.LocalDateTimeConverter'
               }
               customType {
                  name = 'CreditStatusConverter'
                  type = 'com.mm.types.CreditStatus'
                  converter = 'com.mm.db.jooq.datatype.CreditStatusConverter'
               }
               customType {
                  name = 'ChipherConverter'
                  type = 'java.lang.String'
                  converter = 'com.mm.platform.jooq.datatype.CryptStringConverter'
               }
               // other converters
            }
            forcedTypes {
               forcedType {
                  name = 'LocalDateTimeConverter'
                  expression = '.*'
                  types = 'datetime|timestamp'
               }
               forcedType {
                  name = 'CreditStatusConverter'
                  expression = '.*credit\\.status'
                  types = '.*'
               }
               forcedType {
                  name = 'ChipherConverter'
                  expression = '.*(user_account|aud_user_account).(mobile_phone)'
                  types = '.*'
               }
               // other forced types
            }
         }
         strategy {
            name = null
            matchers {
               tables {
                  table {
                     expression = 'user_account'
                     recordImplements = 'com.mm.platform.jooq.audit.AuditableRecord'
                  }
                  table {
                     expression = 'aud_user_account'
                     recordImplements = 'com.mm.platform.jooq.audit.AuditRecord'
                  }
               }
            }
         }
         target {
            packageName = 'com.mm.task04.db'
         }
      }
   }
}
```

Change config for `org.jooq.DSLContext`. It should look like:

```java
@Bean
public DSLContext dslContext(){
final JooqSettings settings=configurationProvider.bind("onboarding.jooq",JooqSettings.class);

        CryptStringConverter.cipherEngine=new CipherEngineAES(
        configurationProvider.getProperty("secret.key",String[].class));

        return DefaultJooqBuilder.of(settings)
        .withDefaultProviders()
        .withRecordListeners(new JooqConverterAwareAuditRecordListener())
        .build();
        }
```

Try to store new data to `user_account.mobile_phone`. Data should be crypted in database.

To automatically store/update data in `aud` tables use write `JOOQ`
operations using `Record`.

## Part 3

Generate more data in all tables.  \
Write next queries and provide REST enpoints to obtain data:

- create new `borrower`
- create new `credit` assigned to `borrower`
- return all credits for selected `borrower`
- return all data about `borrower` (withot his credits)
- return `id`, `next_income_date`, `maritalStatus`, `birthplace` for selected borrower
- for each `eductation` return count of `credits`
- return `birthplace` of the borrower who has the highest salary
- return borrowers who have `HIGHER` education and credits in `CANCELLED` status

## Documentation

* https://www.jooq.org/doc/3.13/manual
* https://blog.jooq.org
* https://www.baeldung.com/jooq-intro
* https://programming.vip/docs/2-qooq-series-tutorial-basic-curd.html
* https://stackoverflow.com/questions/tagged/jooq