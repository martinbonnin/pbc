package pbc.graphql

import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component
import pbc.datastore.DataStore


@Component
class RootQuery : Query {
    fun hello(): String = "Hello there \uD83D\uDC4B \uD83C\uDF84"
//    fun addresses(): List<String> {
//        return DataStore().getAddresses()
//    }
}

@Component
class RootMutation: Mutation {
    fun addAddress(address: String): Boolean {
        DataStore().writeAddress(address)
        return true
    }
}
