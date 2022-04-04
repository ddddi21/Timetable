package com.technokratos.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrlString

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthTokenInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpUnauthorizedClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpAuthorizedClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UnauthorizedRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthorizedRetrofit
