package com.example.auth_catalog.util.clean

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params, out Failure> {

    /**
     * Execute the use case with the parameters received. The response its represented as a Either.
     * @see Either
     */
    abstract suspend fun run(params: Params): Either<Failure, Type>

}