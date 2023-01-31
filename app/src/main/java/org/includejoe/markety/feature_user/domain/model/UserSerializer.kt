package org.includejoe.markety.feature_user.domain.model

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.includejoe.markety.base.util.CryptoManager
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserSerializer @Inject constructor(
    private val cryptoManager: CryptoManager
): Serializer<User> {
    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = User.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch(e: SerializationException) {
            e.printStackTrace()
            User()
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = User.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}