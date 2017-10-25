package br.com.concrete.sdk.data.remote.exception

import br.com.concrete.sdk.model.SdkError
import java.lang.Exception

open class RemoteException internal constructor(message: String, val code: Int, val requestedPath: String, val error: SdkError) :
        Exception(message)

class UnauthorizedException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class ServerException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class NotFoundException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class ForbiddenException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)

class BadRequestException internal constructor(message: String, code: Int, requestedPath: String, error: SdkError) :
        RemoteException(message, code, requestedPath, error)