package com.example.matule.data.remote

import android.util.Log
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
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
        auth.signInWith(OTP) {
            this.email = email
        }
    }

    suspend fun checkOtp(email: String, code: String): Boolean {
        return try {
            auth.verifyEmailOtp(OtpType.Email.EMAIL, email, code)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun changePassword(password: String) {
        auth.updateUser {
            this.password = password
        }
    }
}