package com.example.auth_catalog.util.clean

/**
 * Use case special case, when it's impossible to fails the current flow.
 */
abstract class InfallibleUseCase<out Type, in Params> : UseCase<Type, Params, Failure>() {

    /**
     * Execute the use case with the parameters received. The response its represented as a Either.
     * @see Either
     */
    final override suspend fun run(params: Params): Either<Failure, Type> {
        return Either.Right(runInfallible(params))
    }

    /**
     * Execute the use case with the parameters received. The response its represented as a simple
     * instance ot type value.
     */
    abstract suspend fun runInfallible(params: Params): Type

}