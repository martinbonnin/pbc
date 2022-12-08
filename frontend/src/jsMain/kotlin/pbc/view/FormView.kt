package pbc.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.apollographql.apollo3.ApolloClient
import io.ktor.util.reflect.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import pbc.graphql.AddAddressMutation

val apolloClient = ApolloClient.Builder().serverUrl("https://graphql-dot-pbc-app-371010.ew.r.appspot.com/graphql").build()

data class State(
    val input: String,
    val error: String?,
    val loading: String?,
    val success: Boolean
)

@Composable
fun FormView() {
    val state = remember { mutableStateOf(State(input = "", error = null, loading = null, success = false)) }
    if (state.value.success) {
        Div {
            Text("Thank you \uD83C\uDF84 \uD83D\uDE0A !")
        }
        return
    }

    Div {
        Text("The address where Martin should send a card:")
    }
    Div {
        TextArea(
            value = state.value.input,
        ) {
            style {
                width(300.px)
                height(150.px)
            }
            onInput { event -> state.value = state.value.copy(input = event.value) }
        }
    }

    Div({
        style {
            this.color(Color.red)
        }
    }) {
        Text(state.value.error ?: "")
    }

    if (state.value.loading != null) {
        Div {
            Text(state.value.loading!!)
        }
    }
    val coroutineScope = rememberCoroutineScope()

    Div {
        Button(
            attrs = {
                if (state.value.loading != null) {
                    disabled()
                }

                onClick {
                    if (state.value.input.isBlank()) {
                        state.value  = state.value.copy(error = "address is mandatory")
                    } else {
                        state.value = state.value.copy(error = null, loading = "")
                        val job = coroutineScope.launch {
                            delay(2000)
                            state.value = state.value.copy(
                                error = null,
                                loading = "This is taking longer than usual, looks like the Google leprechauns needed to spawn a new docker instance. Stick around, it'll go through..."
                            )
                        }
                        coroutineScope.launch {
                            try {
                                val response = apolloClient.mutation(AddAddressMutation(state.value.input)).execute()
                                check(response.data?.addAddress == true)
                                state.value = state.value.copy(success = true, loading = null)
                            } catch (e: Exception) {
                                state.value = state.value.copy(error = e.message, loading = null)
                            } finally {
                                job.cancel()
                            }
                        }
                    }
                }
            }
        ) { Text("Submit") }
    }
}
