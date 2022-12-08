package pbc.graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.GraphQLDirective
import graphql.schema.GraphQLSchema
import graphql.schema.idl.SchemaPrinter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource


@SpringBootApplication
class DefaultApplication {
  @Bean
  fun schema(
    query: Query,
    schemaConfig: SchemaGeneratorConfig
  ): GraphQLSchema {
    val schema = toSchema(
      config = schemaConfig,
      queries = listOf(TopLevelObject(query, RootQuery::class)),
      mutations = listOf(TopLevelObject(RootMutation(), RootMutation::class)),
      subscriptions = emptyList()
    )

    return schema
  }

  private fun GraphQLSchema.print(): String {
    return SchemaPrinter(
      SchemaPrinter.Options.defaultOptions()
        .includeIntrospectionTypes(false)
        .includeScalarTypes(true)
        .includeSchemaDefinition(false)
        .includeSchemaElement {
          when (it) {
            is GraphQLDirective -> !setOf("include", "skip", "specifiedBy", "deprecated").contains(
              it.name
            )
            else -> true
          }
        }
        .includeDirectiveDefinitions(true)
    ).print(this)
  }

  @Bean
  fun corsWebFilter(): CorsWebFilter {
    val corsConfig = CorsConfiguration().apply {
      addAllowedOrigin("*")
      addAllowedMethod("*")
      addAllowedHeader("*")
    }

    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", corsConfig)
    return CorsWebFilter(source)
  }
}

fun runServer(): ConfigurableApplicationContext {
  return runApplication<DefaultApplication>()
}
