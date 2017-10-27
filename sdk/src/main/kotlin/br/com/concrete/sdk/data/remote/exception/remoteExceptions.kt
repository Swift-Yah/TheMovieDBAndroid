package br.com.concrete.sdk.data.remote.exception

import br.com.concrete.sdk.model.SdkError
import java.lang.Exception

open class RemoteException internal constructor(message: String,
                                                val code: Int,
                                                val requestedPath: String,
                                                val error: SdkError) : Exception(message) {
    override fun toString(): String {
        return "code: $code\npath: $requestedPath\nerror: $error"
    }
}

class UnauthorizedException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class ServerException internal constructor(message: String, code: Int, requestedPath: String) :
        RemoteException(message, code, requestedPath, SdkError("Server Exception"))

class NotFoundException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class ForbiddenException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class BadRequestException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)