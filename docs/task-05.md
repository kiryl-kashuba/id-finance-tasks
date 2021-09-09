# Task 05: MongoDB

The task is to create a module uses Mongo DB for CRUD operations. Use module:

```Gradle
//I have deleted information that potentially falls under "trade secrets"//
```

Mongo config in Kotlin should look as follows:

```Kotlin
@Bean
open fun mongoDatabase(): MongoDatabase {
  val mongoSettings = configProvider.bind("mongo.db", MongoSettings::class.java)
  return MongoClientBuilder.of(mongoSettings).withJackson(jacksonConfig.objectMapper()).build()
}
```

```Java
@Bean
public MongoDatabase mongoTemplate(){
    return MongoClientBuilder.of(baseMongoSettings)
    .withJackson(jacksonConfig.objectMapper())
    .build();
    }
```

Get some collection

```Java
mongoConfig.mongoTemplate().getCollection("<collection-name>",<your-object>.class);
```

File storage config example in java

```Java
@Bean
public MongoDatabase mongoDatabase(){
    return MongoClientBuilder
    .of(configProvider.bind("file-storage.db",MongoSettings.class))
    .withJackson(objectMapper())
    .build();
    }
```

You also may have to use following dependencies to map objects from/into DB:

```Gradle
implementation lib('com.fasterxml.jackson.core:jackson-annotations')
implementation lib('com.fasterxml.jackson.core:jackson-databind')
implementation lib('com.fasterxml.jackson.datatype:jackson-datatype-jsr310')
implementation lib('com.fasterxml.jackson.module:jackson-module-kotlin')
```

The config-file in mm-config module looks just about:

```yaml
mongo.db:
  maxWaitTime: 120000
  maxSize: 16
  minSize: 2
  maxWaitQueueSize: 200
  hosts:
   //I have deleted information that potentially falls under "trade secrets"//
  idleTime: 60000
  credentials: []
  name: contact-hub
``` 

## Part 1

Create service `contact hub` which will manage contact and addresses related to borrower
from [Task 04: JOOQ](task-04.md). Need to create separate collections for contacts and addresses. Format of
contact/address could be various. Implement CRUD operations for new entities.

Additionally add filter operations by credit status (ex. find all contacts who related to expired credits with N days
after expiration). You would need adjust service and db scheme from previous exercise, after that need to get list of
borrowers id and get all contacts from MongoDB by list of borrowers id.

## Part 2

Add to service feature to manage files related to borrower in MongoDB GridFS. Create table `borrower_document` in MySQL
and save here `borrowerId`, `ObjectId` of document from MongoDB GridFS, `uploadedDate`. Service should be able to save
file and find it by `ObjectId` in GridFS.

Don't implement yourself interactions with GridFS. Use dedicated service
`document-storage` that can store/read data into GridFS.

Service `document-storage` is deployed on test environment and pointed to:

```
mongo:      //I have deleted information that potentially falls under "trade secrets"//
database:   //I have deleted information that potentially falls under "trade secrets"//
endpoint:   //I have deleted information that potentially falls under "trade secrets"//
```

Service `document-storage` has the next REST API:

```
POST /upload-document/{ownerId}/{ownerIdType}/{docType}
Content-Type: multipart/form-data
Response: <DocInfo>

GET /get-documents-info/{ownerId}/{ownerIdType}/{docType}
Response: <List<DocInfo>>

GET /download-document/{fileId}
Response: <DocFile>

GET /delete-document/{fileId}
Response: <Boolean>
```

Types `DocInfo`, `DocFile` placed in dependency:

```groovy
//I have deleted information that potentially falls under "trade secrets"//
```

## Notes

For graphical representation you can use Robo 3T or DataGrip:

- https://robomongo.org
- https://nosqlbooster.com

To see uploaded files for GridFS use `NoSQL Booster`.

## Documentation

- https://www.mongodb.com/what-is-mongodb
- https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
- https://habr.com/ru/post/322532/
