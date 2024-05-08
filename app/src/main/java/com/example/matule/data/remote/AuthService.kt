package com.example.matule.data.remote

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.matule.data.model.ProfileDto
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.NativeSignInState
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthService @Inject constructor(
    private val auth: Auth
) {
    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            Log.e("[AUTH]", "${e.message}")
            false
        }
    }

    suspend fun signUp(name: String, email: String, password: String): Boolean {
        return try {
            auth.signUpWith(Email) {
                this.data = JsonObject(mapOf("full_name" to JsonPrimitive(name)))
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            Log.e("[AUTH]", "${e.message}")
            false
        }
    }

    fun getUserInfo(): UserInfo? {
        return auth.currentUserOrNull()
    }

    suspend fun sendOtp(email: String) {
        auth.resetPasswordForEmail(email)
    }

    suspend fun checkOtp(email: String, code: String): Boolean {
        return true
    }
}