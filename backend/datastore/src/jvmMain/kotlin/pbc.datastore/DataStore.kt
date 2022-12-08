package pbc.datastore

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.datastore.*
import java.io.File

class DataStore {
    private val datastore: Datastore
    private val keyFactory: KeyFactory
        get() {
            return datastore.newKeyFactory()
        }

    init {
        val serviceAccountKeyFile = File("/Users/mbonnin/git/pbc/backend/service_account_key.json")
        datastore = if (serviceAccountKeyFile.exists()) {
            val credentials = serviceAccountKeyFile.inputStream().use {
                GoogleCredentials.fromStream(it)
            }
            DatastoreOptions.newBuilder().setCredentials(credentials).build().service
        } else {
            DatastoreOptions.getDefaultInstance().service
        }
    }

    fun writeAddress(
        address: String,
    ) {
        val entity = Entity.newBuilder(keyFactory.setKind("User").newKey())
            .set("address", address)
            .build()

        datastore.put(entity)
    }

    fun getAddresses(): List<String> {
        val query: EntityQuery? = Query.newEntityQueryBuilder()
            .setKind("User")
            .setLimit(100)
            .build()
        val result = datastore.run(query)

        val items = mutableListOf<String>()
        result.forEach {
            items.add(it.getString("address"))
        }

        return items
    }
}