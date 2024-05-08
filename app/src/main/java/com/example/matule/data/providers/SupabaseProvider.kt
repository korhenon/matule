package com.example.matule.data.providers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

@Module
@InstallIn(SingletonComponent::class)
class SupabaseProvider {
    @Provides
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://vkityhpwclzmbdmmdpck.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZraXR5aHB3Y2x6bWJkbW1kcGNrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUwODQ4ODksImV4cCI6MjAzMDY2MDg4OX0.u18Y2eQhrFVToTMmPEmlsoiapRYjUbgyBp0ELrPEVAU",
        ) {
            install(Postgrest)
            install(Auth)
            install(ComposeAuth) {
                googleNativeLogin("259557030279-8p2t673le74reedi238p710vr8fhaho8.apps.googleusercontent.com") //Use the Web Client ID, not the Android one!
            }
        }
    }

    @Provides
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    fun provideAuth(client: SupabaseClient): Auth {
        return client.auth
    }
}