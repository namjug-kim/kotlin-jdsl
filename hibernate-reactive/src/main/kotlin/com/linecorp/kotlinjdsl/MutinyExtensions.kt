package com.linecorp.kotlinjdsl

import io.smallrye.mutiny.Uni
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * [org.hibernate.reactive.mutiny.impl.MutinySessionFactoryImpl] session doesn't support cancel operation.
 *
 * FIXME when hibernate-reactive support cancel operation. This function should be replaced by Mutiny awaitSuspended.
 */
suspend fun <T> Uni<T>.awaitSession() = suspendCoroutine<T> { continuation ->
    subscribe().with(
        { item -> continuation.resume(item) },
        { failure -> continuation.resumeWithException(failure) }
    )
}
